package waffles.utils.sets.trees;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.trees.traversal.BreadthFirst;
import waffles.utils.sets.trees.traversal.DepthFirst;
import waffles.utils.sets.utilities.iterators.Leaves;
import waffles.utils.tools.collections.Iterables;

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
	public abstract Node createNode(Object... vals);
	
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
		if(Root() != null)
		{
			return () -> new DepthFirst<>((N) Root());
		}
		
		return Iterables.empty();
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
		if(Root() != null)
		{
			return () -> new BreadthFirst<>((N) Root());
		}
		
		return Iterables.empty();
	}

	/**
	 * Returns a leaf iterable for the {@code Rooted}.
	 * 
	 * @param <N>  a nodal type
	 * @return  a leaf iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default <N extends Nodal> Iterable<N> Leaves()
	{
		return () -> new Leaves<>(this);
	}
	
	
	@Override
	public default boolean isEmpty()
	{
		return Root() == null;
	}
	
	@Override
	public default int Count()
	{
		if(Root() != null)
		{
			Node n = Root().Arch();
			return n.TreeSize();
		}
		
		return 0;
	}
}