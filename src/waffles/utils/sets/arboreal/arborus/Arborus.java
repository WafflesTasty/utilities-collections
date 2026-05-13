package waffles.utils.sets.arboreal.arborus;

import java.util.Iterator;

import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.utilities.arboreal.Nodal;
import waffles.utils.tools.patterns.properties.Queryable;

/**
 * An {@code Arborus} is an {@code Arboreal} where each object occupies one node.
 *
 * @author Waffles
 * @since May 10, 2026
 * @version 1.1
 *
 *
 * @param <N>  a nodal type
 * @see Queryable
 * @see Arboreal
 * @see Nodal
 */
public interface Arborus<N extends Nodal> extends Arboreal, Queryable<N>
{
	/**
	 * An {@code Arborus.Query} defines queries for an {@code Arborus}.
	 *
	 * @author Waffles
	 * @since May 10, 2026
	 * @version 1.1
	 *
	 *
	 * @param <N>  a nodal type
	 * @see Arboreal
	 */
	@FunctionalInterface
	public static interface Query<N extends Nodal> extends Arboreal.Query<N>
	{
		/**
		 * Returns the tree of the {@code Query}.
		 * 
		 * @return  a parent tree
		 * 
		 * 
		 * @see Arborus
		 */
		public abstract Arborus<N> Tree();
		
		
		@Override
		public default Iterator<N> All()
		{
			return BFSearch((N) Tree().Root());
		}
	}
	
	
	@Override
	public default Query<N> Query()
	{
		return () -> this;
	}
}