package waffles.utils.sets.trees.binary.search;

import waffles.utils.sets.trees.binary.BiNode;
import waffles.utils.tools.patterns.semantics.Valuable;

/**
 * The {@code IONode} class defines a single node in an in-order tree.
 *
 * @author Waffles
 * @since 01 Aug 2020
 * @version 1.0
 * 
 * 
 * @param <V>  a value type
 * @see Valuable
 * @see BiNode
 */
public class IONode<V> extends BiNode implements Valuable<V>
{
	private V value;

	/**
	 * Creates a new {@code IOTree}.
	 * 
	 * @param tree  a source tree
	 * @param val   a node value
	 * 
	 * 
	 * @see IOTree
	 */
	public IONode(IOTree<?, V> tree, V val)
	{
		super(tree);
		value = val;
	}


	@Override
	public IOTree<?, V> Set()
	{
		return (IOTree<?, V>) super.Set();
	}
	
	@Override
	public IONode<V> Delegate()
	{
		return this;
	}

	@Override
	public IONode<V> Sibling()
	{
		return (IONode<V>) super.Sibling();
	}
	
	@Override
	public IONode<V> LChild()
	{
		return (IONode<V>) super.LChild();
	}
	
	@Override
	public IONode<V> RChild()
	{
		return (IONode<V>) super.RChild();
	}

	@Override
	public IONode<V> Parent()
	{
		return (IONode<V>) super.Parent();
	}

	@Override
	public IONode<V> LLeaf()
	{
		return (IONode<V>) super.LLeaf();
	}
	
	@Override
	public IONode<V> RLeaf()
	{
		return (IONode<V>) super.RLeaf();
	}
	
	@Override
	public IONode<V> Root()
	{
		return (IONode<V>) super.Root();
	}
	
	@Override
	public IONode<V> Arch()
	{
		return this;
	}

	@Override
	public V Value()
	{
		return value;
	}
}