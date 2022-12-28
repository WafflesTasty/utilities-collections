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
 * @param <V>  a value type
 * @see BiNode
 */
public class BIPNode<V> extends BiNode
{	
	private int cDim;
	private Object[] values;
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
	public BIPNode(BIPTree<V> tree, int[] min, int[] max)
	{
		super(tree);
		
		values = new Object[0];
		cMin = Array.copy.of(min);
		cMax = Array.copy.of(max);
		cDim = -1;
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
		BIPNode<V> lChild = Tree().create(cMin, lMax);
		BIPNode<V> rChild = Tree().create(rMin, cMax);
	
		setLChild(lChild);
		setRChild(rChild);
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
	public BIPNode<V> Child(int... coords)
	{
		if(isLeaf()) return null;
		int cut = RChild().Minimum()[cDim];
		if(coords[cDim] < cut)
			return LChild();
		return RChild();
	}
		
	/**
	 * Checks a value in the {@code BIPNode}.
	 * 
	 * @param val  an object value
	 * @return  {@code true} if the node contains the value
	 */
	public boolean hasValue(V val)
	{
		for(Object o : values)
		{
			if(val.equals(o))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Adds a value to the {@code BIPNode}.
	 * 
	 * @param val  an object value
	 */
	public void addValue(V val)
	{
		if(!hasValue(val))
		{
			values = Array.add.to(values, val);
		}
	}
	
	/**
	 * Changes the value of the {@code BIPNode}.
	 * 
	 * @param val  a node value
	 */
	public void setValue(V val)
	{
		if(val != null)
			values = new Object[]{val};
		values = new Object[0];
	}
	
	/**
	 * Returns the value of the {@code BIPNode}.
	 * 
	 * @return  a node value
	 */
	public V Value()
	{
		if(values.length > 0)
			return (V) values[0];
		return null;
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
	public BIPNode<V> Parent()
	{
		return (BIPNode<V>) super.Parent();
	}
	
	@Override
	public BIPNode<V> LChild()
	{
		return (BIPNode<V>) super.LChild();
	}
	
	@Override
	public BIPNode<V> RChild()
	{
		return (BIPNode<V>) super.RChild();
	}
	
	@Override
	public BIPNode<V> Sibling()
	{
		return (BIPNode<V>) super.Sibling();
	}
	
	@Override
	public BIPTree<V> Tree()
	{
		return (BIPTree<V>) super.Tree();
	}
}