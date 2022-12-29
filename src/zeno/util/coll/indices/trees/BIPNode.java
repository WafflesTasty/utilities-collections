package zeno.util.coll.indices.trees;

import zeno.util.coll.trees.binary.BiNode;
import zeno.util.tools.helper.Array;

/**
 * The {@code BIPNode} class defines a single node in a {@code BIPTree}.
 *
 * @author Waffles
 * @since 27 Dec 2022
 * @version 1.0
 * 
 * 
 * @see BiNode
 */
public class BIPNode extends BiNode
{	
	private int cDim;
	private int[] cMin, cMax;
	
	/**
	 * Creates a new {@code BIPNode}.
	 * 
	 * @param tree  a parent tree
	 * @param min   a minimum index
	 * @param max   a maximum index
	 * 
	 * 
	 * @see BIPTree
	 */
	public BIPNode(BIPTree<?> tree, int[] min, int[] max)
	{
		super(tree);
		
		cMin = Array.copy.of(min);
		cMax = Array.copy.of(max);
		cDim = -1;
	}
	
	/**
	 * Performs a split on the {@code BIPNode}.
	 * </br> 
	 * 
	 * @param min  an interval minimum
	 * @param max  an interval maximum
	 */
	public void split(int[] min, int[] max)
	{
		// Find the largest difference of minimums.
		int kMin = -1, dMin = 0;
		for(int k = 0; k < cMin.length; k++)
		{
			if(dMin < min[k] - cMin[k])
			{
				dMin = min[k] - cMin[k];
				kMin = k;
			}
		}

		// Find the largest difference of maximums.
		int kMax = -1, dMax = 0;
		for(int k = 0; k < cMax.length; k++)
		{
			if(dMax < cMax[k] - max[k])
			{
				dMax = cMax[k] - max[k];
				kMax = k;
			}
		}
		
		
		// Compute the new boundary indices.
		int[] lMax = Array.copy.of(cMax);
		int[] rMin = Array.copy.of(cMin);
		
		if(dMin < dMax)
		{
			lMax[kMax] = min[kMax] + 0;
			rMin[kMax] = min[kMax] + 1;
			cDim = kMax;
		}
		else
		{
			lMax[kMin] = min[kMin] - 1;
			rMin[kMin] = min[kMin] + 0;
			cDim = kMin;
		}

		
		// Generate the child nodes.
		BIPNode lChild = Tree().create(cMin, lMax);
		BIPNode rChild = Tree().create(rMin, cMax);
	
		setLChild(lChild);
		setRChild(rChild);
	}

	/**
	 * Performs a merge on the {@code BIPNode}.
	 */
	public void merge()
	{
		clearChildren();
		cDim = -1;
	}
	
	
	/**
	 * Returns a child of the {@code BIPNode}.
	 * 
	 * @param coords  a tile coordinate
	 * @return  a child node
	 * 
	 * 
	 * @see BIPNode
	 */
	public BIPNode Child(int... coords)
	{
		if(isLeaf()) return null;
		int cut = RChild().Minimum()[cDim];
		if(coords[cDim] < cut)
			return LChild();
		return RChild();
	}
		
	/**
	 * Checks the size of the {@code BIPNode}.
	 * </br> A node is a tile iff it contains exactly one coordinate.
	 * 
	 * @return  {@code true} if the node is a tile
	 */
	public boolean isTile()
	{
		return Array.equals.of(cMin, cMax);
	}

	/**
	 * Returns the split of the {@code BIPNode}.
	 * </br> This indicates which dimension the children are split over.
	 * 
	 * @return  a split dimension
	 */
	public int DimSplit()
	{
		return cDim;
	}
	
	
	/**
	 * Returns the minimum of the {@code BIPNode}.
	 * 
	 * @return  a minimum coordinate
	 */
	public int[] Minimum()
	{
		return cMin;
	}
	
	/**
	 * Returns the maximum of the {@code BIPNode}.
	 * 
	 * @return  a maximum coordinate
	 */
	public int[] Maximum()
	{
		return cMax;
	}
	
	
	@Override
	public BIPNode Parent()
	{
		return (BIPNode) super.Parent();
	}
	
	@Override
	public BIPNode LChild()
	{
		return (BIPNode) super.LChild();
	}
	
	@Override
	public BIPNode RChild()
	{
		return (BIPNode) super.RChild();
	}
	
	@Override
	public BIPNode Sibling()
	{
		return (BIPNode) super.Sibling();
	}
	
	@Override
	public BIPTree<?> Tree()
	{
		return (BIPTree<?>) super.Tree();
	}
}