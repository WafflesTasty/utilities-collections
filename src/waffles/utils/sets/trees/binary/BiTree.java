package waffles.utils.sets.trees.binary;

import waffles.utils.sets.trees.Tree;
import waffles.utils.sets.trees.traversal.binary.InOrder;
import waffles.utils.sets.trees.traversal.binary.PostOrder;
import waffles.utils.sets.trees.traversal.binary.PreOrder;

/**
 * The abstract {@code BiTree} class defines a generic binary tree.
 * In addition to the basic binary structure it provides the three
 * binary iterables {@link #inorder()}, {@link #preorder()},
 * {@link #postorder()}.
 *
 * @author Waffles
 * @since 03 Aug 2020
 * @version 1.0
 * 
 * 
 * @see Tree
 */
public abstract class BiTree extends Tree
{
	/**
	 * Performs in-order iteration of the {@code BiTree}.
	 * 
	 * @param <B>  a node type
	 * @return  an in-order iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public <B extends BiNode> Iterable<B> inorder()
	{
		return () -> new InOrder<>(Root());
	}
	
	/**
	 * Performs post-order iteration of the {@code BiTree}.
	 * 
	 * @param <B>  a node type
	 * @return  a post-order iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public <B extends BiNode> Iterable<B> postorder()
	{
		return () -> new PostOrder<>(Root());
	}
	
	/**
	 * Performs pre-order iteration of the {@code BiTree}.
	 * 
	 * @param <B>  a node type
	 * @return  a pre-order iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public <B extends BiNode> Iterable<B> preorder()
	{
		return () -> new PreOrder<>(Root());
	}

	
	@Override
	public BiNode create(Object... vals)
	{
		return new BiNode(this);
	}

	@Override
	public BiNodal Root()
	{
		return (BiNodal) super.Root();
	}
}