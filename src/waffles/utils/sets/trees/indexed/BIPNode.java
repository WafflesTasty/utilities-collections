package waffles.utils.sets.trees.indexed;

import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.indexed.nodes.IPQuery;
import waffles.utils.sets.indexed.nodes.IPQuery.Axis;
import waffles.utils.sets.trees.binary.BiNode;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code BIPNode} defines a single node in a binary index partition tree.
 * This node keeps track of its dimensions, and can be split along an integer axis.
 *
 * @author Waffles
 * @since 27 Dec 2022
 * @version 1.0
 * 
 * 
 * @see IndexedSet
 * @see BiNode
 */
public class BIPNode extends BiNode implements IndexedSet<BIPNode>
{	
	private int cDim;
	private int[] cMin, cMax;
	
	/**
	 * Creates a new {@code BIPNode}.
	 * 
	 * @param tree  a target tree
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
	 * Queries the {@code BIPNode} for a subindex.
	 * 
	 * @param min  a minimum index
	 * @param max  a maximum index
	 * @return  a bip query
	 * 
	 * 
	 * @see IPQuery
	 */
	public IPQuery<BIPNode> query(int[] min, int[] max)
	{
		return new IPQuery<>(this, min, max);
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
		// Query the node for an optimal split index.
		Axis axis = query(min, max).OptimalAxis();
		
		// Copy the dimensions of the node.
		int[] lMin = Array.copy.of(cMin);
		int[] lMax = Array.copy.of(cMax);
		int[] rMin = Array.copy.of(cMin);
		int[] rMax = Array.copy.of(cMax);
		
		// Insert the split into the dimensions.
		lMax[axis.Index()] = axis.Value() + 0;
		rMin[axis.Index()] = axis.Value() + 1;
				
		// Generate the corresponding child nodes.
		BIPNode lChild = Set().create(lMin, lMax);
		BIPNode rChild = Set().create(rMin, rMax);
		
		// Update the node.
		cDim = axis.Index();
		setLChild(lChild);
		setRChild(rChild);
	}

	/**
	 * Finds a child of the {@code BIPNode} which
	 * is the closest to a given index.
	 * 
	 * @param coords  an index coordinate
	 * @return  a child node
	 * 
	 * 
	 * @see BIPNode
	 */
	@Override
	public BIPNode get(int... coords)
	{
		// If it has no children...
		if(isLeaf())
		{
			// Bail.
			return null;
		}
		
		// Otherwise, find the cutoff value....
		int cut = RChild().Minimum()[cDim];
		// And return the correct child node.
		if(coords[cDim] < cut)
			return LChild();
		return RChild();
	}
		
	/**
	 * Checks the tile size of the {@code BIPNode}.
	 * A node is a tile if and only if it spans
	 * a single element in an indexed set.
	 * 
	 * @return  {@code true} if the node is a tile
	 */
	public boolean isTile()
	{
		int[] min = Minimum();
		int[] max = Maximum();
		
		return Array.equals.of(min, max);
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
	 * Performs a merge on the {@code BIPNode}.
	 */
	public void merge()
	{
		cDim = -1;
		clear();
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
	public BIPTree<?> Set()
	{
		return (BIPTree<?>) super.Set();
	}

	@Override
	public int[] Minimum()
	{
		return cMin;
	}

	@Override
	public int[] Maximum()
	{
		return cMax;
	}
}