package waffles.utils.sets.arboreal.binary;

import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.utilities.rooted.iterators.binary.InOrder;
import waffles.utils.sets.utilities.rooted.iterators.binary.PostOrder;
import waffles.utils.sets.utilities.rooted.iterators.binary.PreOrder;

/**
 * A {@code BiArboreal} defines a generic binary arboreal structure.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 * 
 * @see Arboreal
 */
public interface BiArboreal extends Arboreal
{
	/**
	 * A {@code Mutable BiArboreal} allows changes to be made.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see BiArboreal
	 * @see Arboreal
	 */
	public static interface Mutable extends Arboreal.Mutable, BiArboreal
	{
		// NOT APPLICABLE
	}
	
	
	@Override
	public abstract BiNodal Root();

	
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
	public default <B extends BiNodal> Iterable<B> preorder()
	{
		return () -> new PreOrder<>(Root());
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
	public default <B extends BiNodal> Iterable<B> postorder()
	{
		return () -> new PostOrder<>(Root());
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
	public default <B extends BiNodal> Iterable<B> inorder()
	{
		return () -> new InOrder<>(Root());
	}
}