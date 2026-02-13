package waffles.utils.sets.arboreal;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.utilities.rooted.Nodal;
import waffles.utils.sets.utilities.rooted.Node;
import waffles.utils.sets.utilities.rooted.iterators.BreadthFirst;
import waffles.utils.sets.utilities.rooted.iterators.DepthFirst;
import waffles.utils.sets.utilities.rooted.iterators.LeafIterator;
import waffles.utils.tools.collections.Iterables;
import waffles.utils.tools.patterns.Constructible;
import waffles.utils.tools.patterns.Constructible.Workshop;
import waffles.utils.tools.patterns.properties.Immutable;

/**
 * An {@code Arboreal} object defines a tree-like node structure.
 * Each tree requires at least a root {@code Node}, and optionally
 * allows the {@link #Factory()} method to be overwritten
 * to create custom nodes for the {@code Tree}.
 * 
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.1
 *
 * 
 * @see Constructible
 * @see CountableSet
 * @see Immutable
 */
@FunctionalInterface
public interface Arboreal extends Constructible, CountableSet, Immutable
{
	/**
	 * A {@code Rooted.Factory} generates {@code Node} objects.
	 *
	 * @author Waffles
	 * @since 25 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Workshop
	 * @see Rooted
	 */
	@FunctionalInterface
	public static interface Factory extends Rooted, Workshop<Object>
	{		
		@Override
		public default Arboreal create(Object... data)
		{
			return () -> null;
		}
		
		/**
		 * Constructs a {@code Node} in the {@code Factory}.
		 * 
		 * @param data  construction data
		 * @return  a constructed node
		 * 
		 * 
		 * @see Node
		 */
		public default Node node(Object... data)
		{
			return new Node(Tree());
		}
	}

	/**
	 * A {@code Mutable Arboreal} allows its root to be changed.
	 *
	 * @author Waffles
	 * @since 13 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see Immutable
	 * @see Arboreal
	 */
	public static interface Mutable extends Arboreal, Immutable.Mutable
	{
		/**
		 * Changes the root of the {@code Arboreal}.
		 * 
		 * @param r  a root node
		 * 
		 * 
		 * @see Nodal
		 */
		public abstract void setRoot(Nodal r);
	}
	

	/**
	 * Returns the root of the {@code Arboreal}.
	 * 
	 * @return  a root nodal
	 * 
	 * 
	 * @see Nodal
	 */
	public abstract Nodal Root();
	
	
	/**
	 * Returns a leaf iterable for the {@code Arboreal}.
	 * 
	 * @param <N>  a nodal type
	 * @return  a leaf iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default <N extends Nodal> Iterable<N> Leaves()
	{
		return () -> new LeafIterator<>(this);
	}
	
	/**
	 * Returns a depth-first iterable for the {@code Arboreal}.
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
	 * Returns a breadth-first iterable for the {@code Arboreal}.
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
	
	
	@Override
	public default Factory Factory()
	{
		return () -> this;
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