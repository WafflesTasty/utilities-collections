package waffles.utils.sets.utilities;

import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.trees.binary.BiNode;
import waffles.utils.sets.trees.traversal.binary.InOrder;
import waffles.utils.sets.trees.traversal.binary.PostOrder;
import waffles.utils.sets.trees.traversal.binary.PreOrder;
import waffles.utils.sets.utilities.iterators.IndexValues;

/**
 * The {@code Iterables} class provides static utility methods to generate {@code Iterable} objects.
 * The iterators generated by this class can be applied to the various set structures
 * found within the Collections library.
 * 
 * @author Waffles
 * @since Aug 15, 2015
 * @version 1.0
 */
public final class Iterables
{
	/**
	 * Returns an iterable over a generic {@code Index}.
	 * 
	 * @param <O>  an index object type
	 * @param index  an indexed set to iterate
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * @return  an index iterable
	 * 
	 * 
	 * @see IndexedSet
	 * @see Iterable
	 */
	public static <O> Iterable<O> index(IndexedSet<O> index, int[] min, int[] max)
	{
		return () -> new IndexValues<>(index, min, max);
	}
	
	/**
	 * Returns an in-order iterable over a {@code BiNode}.
	 * 
	 * @param <N>  a node type
	 * @param base  a base node
	 * @return  an in-order iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BiNode
	 */
	public static <N extends BiNode> Iterable<N> inorder(N base)
	{
		return () -> new InOrder<>(base);
	}
	
	/**
	 * Returns a post-order iterable over a {@code BiNode}.
	 * 
	 * @param <N>  a node type
	 * @param base  a base node
	 * @return  a post-order iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BiNode
	 */
	public static <N extends BiNode> Iterable<N> postorder(N base)
	{
		return () -> new PostOrder<>(base);
	}
	
	/**
	 * Returns a pre-order iterable over a {@code BiNode}.
	 * 
	 * @param <N>  a node type
	 * @param base  a base node
	 * @return  a pre-order iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BiNode
	 */
	public static <N extends BiNode> Iterable<N> preorder(N base)
	{
		return () -> new PreOrder<>(base);
	}
	
	
	private Iterables()
	{
		// NOT APPLICABLE
	}
}
