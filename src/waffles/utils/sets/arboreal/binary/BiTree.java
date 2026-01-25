package waffles.utils.sets.arboreal.binary;

import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.arboreal.Tree;
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
	 * A {@code BiTree.Factory} generates {@code BiNode} objects.
	 *
	 * @author Waffles
	 * @since 25 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Arboreal
	 */
	public static interface Factory extends Arboreal.Factory
	{			
		@Override
		public default BiNode node(Object... data)
		{
			return new BiNode(Tree());
		}
		
		@Override
		public abstract BiTree Tree();
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
	
	
	@Override
	public Factory Factory()
	{
		return () -> this;
	}
	
	@Override
	public BiNodal Root()
	{
		return (BiNodal) super.Root();
	}
}