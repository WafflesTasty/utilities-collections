package waffles.utils.sets.countable;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.Set;

/**
 * A {@code MutableSet} is a {@code CountableSet} which allows the adding and removing of objects.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see CountableSet
 */
public interface MutableSet<O> extends CountableSet
{	
	/**
	 * A {@code Wrapper} defines a wrapper around another {@code MutableSet}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see MutableSet
	 * @see Set
	 */
	public static interface Wrapper<O> extends Set.Wrapper, MutableSet<O>
	{
		@Override
		public abstract MutableSet<O> Delegate();
				
		@Override
		public default void remove(O obj)
		{
			Delegate().remove(obj);
		}
		
		@Override
		public default void add(O obj)
		{
			Delegate().add(obj);
		}
	}
	
	/**
	 * A {@code Java MutableSet} defines a wrapper around a {@code Collection}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see MutableSet
	 * @see Set
	 */
	public static interface Java<O> extends Set.Java<O>, MutableSet<O>
	{
		@Override
		public default void remove(O obj)
		{
			Delegate().remove(obj);
		}
		
		@Override
		public default void add(O obj)
		{
			Delegate().add(obj);
		}
	}
	
	
	/**
	 * Removes an object from the {@code MutableSet}.
	 * 
	 * @param obj  an object to remove
	 */
	public abstract void remove(O obj);
	
	/**
	 * Adds an object to the {@code MutableSet}.
	 * 
	 * @param obj  an object to add
	 */
	public abstract void add(O obj);
}