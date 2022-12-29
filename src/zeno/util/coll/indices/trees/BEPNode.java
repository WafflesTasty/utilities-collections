package zeno.util.coll.indices.trees;

import zeno.util.tools.helper.Array;

/**
 * The {@code BEPNode} class defines a single node in a {@code BEPTree}.
 *
 * @author Waffles
 * @since 27 Dec 2022
 * @version 1.0
 * 
 * 
 * @param <E>  an enum type
 * @see BIPNode
 */
public class BEPNode<E extends Enum<E>> extends BIPNode
{	
	private Object[] values;
	
	/**
	 * Creates a new {@code BEPNode}.
	 * 
	 * @param tree  a parent tree
	 * @param min   a minimum index
	 * @param max   a maximum index
	 * 
	 * 
	 * @see BEPTree
	 */
	public BEPNode(BEPTree<E> tree, int[] min, int[] max)
	{
		super(tree, min, max);
		values = new Object[0];
	}

	
		
	/**
	 * Checks a value in the {@code BEPNode}.
	 * 
	 * @param val  an enum value
	 * @return  {@code true} if the node contains the value
	 */
	public boolean hasValue(E val)
	{
		for(Object o : values)
		{
			if(val == o)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Adds a value to the {@code BEPNode}.
	 * 
	 * @param val  an enum value
	 */
	public void addValue(E val)
	{
		if(!hasValue(val))
		{
			values = Array.add.to(values, val);
		}
	}
	
	/**
	 * Changes the value of the {@code BEPNode}.
	 * 
	 * @param val  a node value
	 */
	public void setValue(E val)
	{
		if(val != null)
			values = new Object[]{val};
		values = new Object[0];
	}
	
	/**
	 * Returns the value of the {@code BEPNode}.
	 * 
	 * @return  a node value
	 */
	public E Value()
	{
		if(values.length > 0)
			return (E) values[0];
		return null;
	}
		
	
	@Override
	public void split(int[] min, int[] max)
	{
		super.split(min, max);
		LChild().setValue(Value());
		RChild().setValue(Value());
	}

	@Override
	public BEPNode<E> Child(int... coords)
	{
		return (BEPNode<E>) super.Child(coords);
	}
		
	@Override
	public BEPNode<E> Parent()
	{
		return (BEPNode<E>) super.Parent();
	}
	
	@Override
	public BEPNode<E> LChild()
	{
		return (BEPNode<E>) super.LChild();
	}
	
	@Override
	public BEPNode<E> RChild()
	{
		return (BEPNode<E>) super.RChild();
	}
	
	@Override
	public BEPNode<E> Sibling()
	{
		return (BEPNode<E>) super.Sibling();
	}
	
	@Override
	public BEPTree<E> Tree()
	{
		return (BEPTree<E>) super.Tree();
	}
}