package waffles.utils.sets.rooted.binary;

import waffles.utils.sets.rooted.Tree;
import waffles.utils.sets.utilities.rooted.iterators.binary.InOrder;
import waffles.utils.sets.utilities.rooted.iterators.binary.PostOrder;
import waffles.utils.sets.utilities.rooted.iterators.binary.PreOrder;

/**
 * The {@code BiTree} class defines a generic binary tree structure.
 *
 * @author Waffles
 * @since 03 Aug 2020
 * @version 1.0
 * 
 * 
 * @see Tree
 */
public class BiTree extends Tree
{
	/**
	 * Performs in-order iteration of the {@code BiTree}.
	 * 
	 * @param <B>  a node type
	 * @return  an in-order iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BiNodal
	 */
	public <B extends BiNodal> Iterable<B> inorder()
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
	 * @see BiNodal
	 */
	public <B extends BiNodal> Iterable<B> postorder()
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
	 * @see BiNodal
	 */
	public <B extends BiNodal> Iterable<B> preorder()
	{
		return () -> new PreOrder<>(Root());
	}

	
	@Override
	public BiNode createNode(Object... vals)
	{
		return new BiNode(this);
	}

	@Override
	public BiNodal Root()
	{
		return (BiNodal) super.Root();
	}
}