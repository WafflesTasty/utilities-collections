package waffles.utils.sets;

import waffles.utils.tools.patterns.basic.Clearable;
import waffles.utils.tools.patterns.properties.counters.Countable;

/**
 * A {@code CountableSet} contains a countable number of objects.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.1
 * 
 * 
 * @see Clearable
 * @see Countable
 * @see Set
 */
@FunctionalInterface
public interface CountableSet extends Clearable, Countable, Set
{
	/**
	 * A {@code Wrapper} defines a wrapper around another {@code Set}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 * 
	 * 
	 * @see CountableSet
	 * @see Set
	 */
	public static interface Wrapper extends Set.Wrapper, CountableSet
	{
		@Override
		public default void clear()
		{
			Delegate().clear();
		}
		
		@Override
		public abstract CountableSet Delegate();
				
		@Override
		public default int Count()
		{
			return Delegate().Count();
		}
	}
	
	/**
	 * A {@code Java Set} defines a wrapper around a {@code Collection}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see CountableSet
	 * @see Set
	 */
	public static interface Java<O> extends Set.Java<O>, CountableSet
	{
		@Override
		public default void clear()
		{
			Delegate().clear();
		}
		
		@Override
		public default int Count()
		{
			return Delegate().size();
		}
	}
	
	
	/**
	 * Checks if the {@code CountableSet} is completely empty.
	 * 
	 * @return  {@code true} if the set is empty
	 */
	public default boolean isEmpty()
	{
		return Count() == 0;
	}
	
	
	@Override
	public default void clear()
	{
		// NOT APPLICABLE
	}
}