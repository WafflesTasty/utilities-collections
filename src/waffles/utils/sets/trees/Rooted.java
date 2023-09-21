package waffles.utils.sets.trees;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.trees.traversal.BreadthFirst;
import waffles.utils.sets.trees.traversal.DepthFirst;

/**
 * The {@code Rooted} interface defines a structure with a {@code Nodal} root.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.1
 *
 *
 * @see CountableSet
 */
public interface Rooted extends CountableSet
{
	/**
	 * Returns the root of the {@code Rooted}.
	 * 
	 * @return  a root nodal
	 * 
	 * 
	 * @see Nodal
	 */
	public abstract Nodal Root();
	
	/**
	 * Creates a new node for the {@code Rooted}.
	 * 
	 * @param vals  an array of values
	 * @return  a new node
	 * 
	 * 
	 * @see Node
	 */
	public abstract Node create(Object... vals);
	
	/**
	 * Returns a depth-first iterable for the {@code Rooted}.
	 * 
	 * @param <N>  a node type
	 * @return  a depth-first iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default <N extends Nodal> Iterable<N> DFSearch()
	{
		return () -> new DepthFirst<>((N) Root());
	}

	/**
	 * Returns a breadth-first iterable for the {@code Rooted}.
	 * 
	 * @param <N>  a node type
	 * @return  a breadth-first iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default <N extends Nodal> Iterable<N> BFSearch()
	{
		return () -> new BreadthFirst<>((N) Root());
	}

	
	@Override
	public default boolean isEmpty()
	{
		return Root() == null;
	}
}