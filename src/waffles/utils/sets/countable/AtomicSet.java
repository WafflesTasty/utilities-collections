package waffles.utils.sets.countable;

import waffles.utils.sets.IterableSet;

/**
 * An {@code AtomicSet} is a mutable set which can iterate over its contents.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see IterableSet
 * @see MutableSet
 */
public interface AtomicSet<O> extends IterableSet<O>, MutableSet<O>
{
	/**
	 * A {@code Wrapper Set} defines a wrapper around another {@code AtomicSet}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see IterableSet
	 * @see MutableSet
	 * @see AtomicSet
	 */
	public static interface Wrapper<O> extends IterableSet.Wrapper<O>, MutableSet.Wrapper<O>, AtomicSet<O>
	{
		@Override
		public abstract AtomicSet<O> Delegate();
	}
	
	/**
	 * A {@code Java AtomicSet} defines a wrapper around a {@code Collection}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see IterableSet
	 * @see MutableSet
	 * @see AtomicSet
	 */
	public static interface Java<O> extends IterableSet.Java<O>, MutableSet.Java<O>, AtomicSet<O>
	{
		// NOT APPLICABLE
	}
}