package zeno.util.coll.utilities;

import zeno.util.coll.indices.arrays.Index;
import zeno.util.coll.trees.binary.BiNode;
import zeno.util.coll.trees.traversal.binary.InOrder;
import zeno.util.coll.trees.traversal.binary.PostOrder;
import zeno.util.coll.trees.traversal.binary.PreOrder;
import zeno.util.coll.utilities.iterators.IndexIterator;

/**
 * The {@code Iterables} class provides a few basic iterables over collections.
 *
 * @author Zeno
 * @since 28 Feb 2020
 * @version 1.0
 */
public final class Iterables
{
	/**
	 * Returns an iterable over a generic {@code Index}.
	 * 
	 * @param <V>  an index value type
	 * @param index  a target index
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * @return  an index iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public static <V> Iterable<V> index(Index<V> index, int[] min, int[] max)
	{
		return () -> new IndexIterator<>(index, min, max);
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