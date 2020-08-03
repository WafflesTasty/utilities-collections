package zeno.util.coll.trees.binary;

import zeno.util.tools.patterns.properties.Valuable;

/**
 * The {@code BSNode} class defines a single node in a binary search tree.
 *
 * @author Zeno
 * @since 01 Aug 2020
 * @version 1.0
 * 
 * 
 * @param <V>  a value type
 * @see Valuable
 * @see BiNode
 */
public class BSNode<V> extends BiNode implements Valuable<V>
{
	private V value;

	/**
	 * Creates a new {@code BSNode}.
	 * 
	 * @param tree  a source tree
	 * @param val   a node value
	 * 
	 * 
	 * @see BSTree
	 */
	public BSNode(BSTree<V> tree, V val)
	{
		super(tree);
		value = val;
	}

	
	@Override
	public Iterable<BSNode<V>> Children()
	{
		return super.Children();
	}
	
	
	@Override
	public BSNode<V> Sibling()
	{
		return (BSNode<V>) super.Sibling();
	}
	
	@Override
	public BSNode<V> LChild()
	{
		return (BSNode<V>) super.LChild();
	}
	
	@Override
	public BSNode<V> RChild()
	{
		return (BSNode<V>) super.RChild();
	}

	@Override
	public BSNode<V> Parent()
	{
		return (BSNode<V>) super.Parent();
	}

	@Override
	public BSNode<V> Root()
	{
		return (BSNode<V>) super.Root();
	}
	
	@Override
	public V Value()
	{
		return value;
	}
}