package waffles.utils.sets.arboreal;

import java.util.Iterator;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.utilities.arboreal.Nodal;
import waffles.utils.sets.utilities.arboreal.Node;
import waffles.utils.sets.utilities.arboreal.iterators.BreadthFirst;
import waffles.utils.sets.utilities.arboreal.iterators.DepthFirst;
import waffles.utils.sets.utilities.arboreal.iterators.LeafIterator;
import waffles.utils.tools.collections.Iterables;
import waffles.utils.tools.collections.iterators.EmptyIterator;
import waffles.utils.tools.patterns.Constructible;
import waffles.utils.tools.patterns.Constructible.Workshop;
import waffles.utils.tools.patterns.properties.Immutable;
import waffles.utils.tools.patterns.properties.Queryable;

/**
 * An {@code Arboreal} object defines a tree-like node structure.
 * Each tree requires at least a root {@code Node}, and optionally
 * allows the {@link #Factory()} method to be overwritten
 * to create custom nodes for the {@code Tree}.
 *
 * @author Waffles
 * @since May 13, 2026
 * @version 1.1
 *
 * 
 * @see Constructible
 * @see CountableSet
 * @see Immutable
 */
public interface Arboreal extends Constructible, CountableSet, Immutable
{
	/**
	 * An {@code Arboreal.Factory} generates {@code Node} objects.
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
		public abstract Arboreal Tree();
		
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
	 * An {@code Arboreal.Mutable} can change its own root nodal.
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
	 * An {@code Arboreal.Query} defines tree traversal queries.
	 *
	 * @author Waffles
	 * @since May 13, 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see Queryable
	 */
	public static interface Query<O> extends Queryable.Query<O>
	{		
		/**
		 * Iterates over the nodes of an {@code Arboreal} breadth-first.
		 * 
		 * @param <N>  a nodal type
		 * @param r    a root nodal
		 * @return  a node iterator
		 * 
		 * 
		 * @see Iterator
		 * @see Nodal
		 */
		public default <N extends Nodal> Iterator<N> BFSearch(N r)
		{
			return new BreadthFirst<>(r);
		}
		
		/**
		 * Iterates over the nodes of an {@code Arboreal} depth-first.
		 * 
		 * @param <N>  a nodal type
		 * @param r    a root nodal
		 * @return  a node iterator
		 * 
		 * 
		 * @see Iterator
		 * @see Nodal
		 */
		public default <N extends Nodal> Iterator<N> DFSearch(N r)
		{
			return new DepthFirst<>(r);
		}

		/**
		 * Iterates over the leaves of an {@code Arboreal}.
		 * 
		 * @param <N>  a nodal type
		 * @param r    a root nodal
		 * @return  a node iterator
		 * 
		 * 
		 * @see Iterator
		 * @see Nodal
		 */
		public default <N extends Nodal> Iterator<N> Leaves(N r)
		{
			return new LeafIterator<>(r);
		}
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
	 * Iterates over the nodes of the {@code Arboreal} breadth-first.
	 * 
	 * @param <N>  a nodal type
	 * @return  a node iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Nodal
	 */
	public default <N extends Nodal> Iterable<N> BFSearch()
	{
		if(Root() == null)
		{
			return Iterables.empty();
		}
		
		return () -> Query().BFSearch((N) Root());
	}
	
	/**
	 * Iterates over the nodes of the {@code Arboreal} depth-first.
	 * 
	 * @param <N>  a nodal type
	 * @return  a node iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Nodal
	 */
	public default <N extends Nodal> Iterable<N> DFSearch()
	{
		if(Root() == null)
		{
			return Iterables.empty();
		}
		
		return () -> Query().DFSearch((N) Root());
	}
	
	/**
	 * Iterates over the leaves of the {@code Arboreal}.
	 * 
	 * @param <N>  a nodal type
	 * @return  a node iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Nodal
	 */
	public default <N extends Nodal> Iterable<N> Leaves()
	{
		if(Root() == null)
		{
			return Iterables.empty();
		}
		
		return () -> Query().Leaves((N) Root());
	}
	
	/**
	 * Returns the {@code Query} of the {@code Arboreal}.
	 * 
	 * @return  a query
	 * 
	 * 
	 * @see Query
	 */
	public default Query<?> Query()
	{
		return () -> new EmptyIterator<>();
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
		Nodal r = Root();
		if(r != null)
		{
			Node n = (Node) r.Arch();
			return n.TreeSize();
		}
		
		return 0;
	}
}