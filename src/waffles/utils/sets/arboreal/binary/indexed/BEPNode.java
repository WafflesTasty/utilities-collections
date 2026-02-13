package waffles.utils.sets.arboreal.binary.indexed;

import waffles.utils.tools.primitives.Array;

/**
 * The {@code BEPNode} class defines a single node in a {@code BEPTree}.
 * Each node stores enum values in an array. If the node is a leaf, the
 * first array value equals the node's value. If the node has children,
 * the array contains all values in all child nodes.
 *
 * @author Waffles
 * @since 27 Dec 2022
 * @version 1.0
 * 
 * 
 * @param <E>  an enum type
 * @see BIPNode
 * @see Enum
 */
public class BEPNode<E extends Enum<E>> extends BIPNode
{	
	private Enum<E>[] values;
	
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
		values = new Enum[]{null};
	}
		
	
	/**
	 * Adds a new value to the {@code BEPNode}.
	 * 
	 * @param val  an enum value
	 */
	public void addValue(Enum<?> val)
	{
		if(!hasValue(val))
		{
			values = Array.add.to(values, (E) val);
		}
	}
			
	/**
	 * Checks if a value is in the {@code BEPNode}.
	 * 
	 * @param val  an enum value
	 * @return  {@code true} if it contains the value
	 */
	public boolean hasValue(Enum<?> val)
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
	 * Changes the value of the {@code BEPNode}.
	 * 
	 * @param val  a node value
	 */
	public void setValue(Enum<?> val)
	{
		values = new Enum[]{val};
	}
	
	/**
	 * Returns a {@code BEPNode} value.
	 * 
	 * @return  a node value
	 */
	public E Value()
	{
		return (E) values[0];
	}
	
	
	@Override
	public void split(int[] min, int[] max)
	{
		super.split(min, max);
		LChild().setValue(Value());
		RChild().setValue(Value());
	}
	
	@Override
	public Iterable<BEPNode<E>> Nodes(int... crds)
	{
		return super.Nodes(crds);
	}
		
	@Override
	public BEPNode<E> childAt(int... crds)
	{
		return (BEPNode<E>) super.childAt(crds);
	}
	
				
	@Override
	public BEPNode<E> Parent()
	{
		return (BEPNode<E>) super.Parent();
	}
	
	@Override
	public BEPNode<E> Sibling()
	{
		return (BEPNode<E>) super.Sibling();
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
	public BEPTree<E> Set()
	{
		return (BEPTree<E>) super.Set();
	}
}