package waffles.utils.sets.trees.binary.search;

/**
 * The {@code BSNode} class defines a single node in a binary search tree.
 *
 * @author Waffles
 * @since 01 Aug 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see IONode
 */
public class BSNode<O> extends IONode<O>
{
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
		super(tree, val);
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
}