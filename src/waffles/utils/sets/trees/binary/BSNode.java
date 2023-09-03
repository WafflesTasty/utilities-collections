package waffles.utils.sets.trees.binary;

import waffles.utils.tools.patterns.semantics.Valuable;

/**
 * The {@code BSNode} class defines a single node in a binary search tree.
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
public class BSNode<O> extends BiNode implements Valuable<O>
{
	private O value;

	/**
	 * Creates a new {@code BSNode}.
	 * 
	 * @param tree  a source tree
	 * @param val   a node value
	 * 
	 * 
	 * @see BSTree
	 */
	public BSNode(BSTree<O> tree, O val)
	{
		super(tree);
		value = val;
	}

	
	@Override
	public BSTree<O> Set()
	{
		return (BSTree<O>) super.Set();
	}
	
	@Override
	public BSNode<O> Delegate()
	{
		return this;
	}

	@Override
	public BSNode<O> Sibling()
	{
		return (BSNode<O>) super.Sibling();
	}
	
	@Override
	public BSNode<O> LChild()
	{
		return (BSNode<O>) super.LChild();
	}
	
	@Override
	public BSNode<O> RChild()
	{
		return (BSNode<O>) super.RChild();
	}

	@Override
	public BSNode<O> Parent()
	{
		return (BSNode<O>) super.Parent();
	}

	@Override
	public BSNode<O> LLeaf()
	{
		return (BSNode<O>) super.LLeaf();
	}
	
	@Override
	public BSNode<O> RLeaf()
	{
		return (BSNode<O>) super.RLeaf();
	}
	
	@Override
	public BSNode<O> Root()
	{
		return (BSNode<O>) super.Root();
	}
	
	@Override
	public BSNode<O> Arch()
	{
		return this;
	}
	
	@Override
	public O Value()
	{
		return value;
	}
}