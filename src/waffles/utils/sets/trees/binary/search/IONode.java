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
 * @param <O>  a node object type
 * @see Valuable
 * @see BiNode
 */
public class IONode<O> extends BiNode implements Valuable<O>
{
	private O value;

	/**
	 * Creates a new {@code IOTree}.
	 * 
	 * @param tree  a source tree
	 * @param val   a node value
	 * 
	 * 
	 * @see IOTree
	 */
	public IONode(IOTree<?, O> tree, O val)
	{
		super(tree);
		value = val;
	}


	@Override
	public IOTree<?, O> Set()
	{
		return (IOTree<?, O>) super.Set();
	}
	
	@Override
	public IONode<O> Delegate()
	{
		return this;
	}

	@Override
	public IONode<O> Sibling()
	{
		return (IONode<O>) super.Sibling();
	}
	
	@Override
	public IONode<O> LChild()
	{
		return (IONode<O>) super.LChild();
	}
	
	@Override
	public IONode<O> RChild()
	{
		return (IONode<O>) super.RChild();
	}

	@Override
	public IONode<O> Parent()
	{
		return (IONode<O>) super.Parent();
	}

	@Override
	public IONode<O> LLeaf()
	{
		return (IONode<O>) super.LLeaf();
	}
	
	@Override
	public IONode<O> RLeaf()
	{
		return (IONode<O>) super.RLeaf();
	}
	
	@Override
	public IONode<O> Root()
	{
		return (IONode<O>) super.Root();
	}
	
	@Override
	public IONode<O> Arch()
	{
		return this;
	}

	@Override
	public O Value()
	{
		return value;
	}
}