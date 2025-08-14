package waffles.utils.sets;

import java.util.Iterator;

import waffles.utils.tools.collections.Iterables;

/**
 * An {@code IterableSet} is a countable set which can iterate over its contents.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see CountableSet
 * @see Iterable
 */
public interface IterableSet<O> extends CountableSet, Iterable<O>
{
	/**
	 * A {@code Wrapper} defines a wrapper around another {@code IterableSet}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see CountableSet
	 * @see IterableSet
	 */
	public static interface Wrapper<O> extends CountableSet.Wrapper, IterableSet<O>
	{
		@Override
		public abstract IterableSet<O> Delegate();
				
		@Override
		public default Iterator<O> iterator()
		{
			return Delegate().iterator();
		}
		
		@Override
		public default int Count()
		{
			return Delegate().Count();
		}
	}
	
	/**
	 * A {@code Java IterableSet} defines a wrapper around a {@code Collection}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see CountableSet
	 * @see IterableSet
	 */
	public static interface Java<O> extends CountableSet.Java<O>, IterableSet<O>
	{
		@Override
		public default Iterator<O> iterator()
		{
			return Delegate().iterator();
		}
		
		@Override
		public default int Count()
		{
			return Delegate().size();
		}
	}
	
	
	/**
	 * Checks if the {@code IterableSet} contains an object.
	 * 
	 * @param obj  a potential set object
	 * @return  {@code true} if the object is in the set
	 */
	public default boolean contains(O obj)
	{
		for(O o : this)
		{
			if(obj.equals(o))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public default int Count()
	{
		return Iterables.count(this);
	}
}