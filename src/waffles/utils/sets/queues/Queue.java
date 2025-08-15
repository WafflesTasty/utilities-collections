package waffles.utils.sets.queues;

import java.util.Iterator;

import waffles.utils.sets.IterableSet;

/**
 * A {@code Queue} defines an iterable set of objects with one-way access to the head or tail.
 * The order of objects going in and out of the queue is left to the implementation.
 *
 * @author Waffles
 * @since 23 May 2021
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see IterableSet
 */
public interface Queue<O> extends IterableSet<O>
{
	/**
	 * A {@code Wrapper} defines a wrapper around another {@code Queue}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see IterableSet
	 * @see Queue
	 */
	public static interface Wrapper<O> extends IterableSet.Wrapper<O>, Queue<O>
	{
		@Override
		public abstract Queue<O> Delegate();
		
		
		@Override
		public default Iterator<O> iterator()
		{
			return Delegate().iterator();
		}
		
		@Override
		public default void push(O obj)
		{
			Delegate().push(obj);
		}
		
		@Override
		public default int Count()
		{
			return Delegate().Count();
		}

		@Override
		public default O peek()
		{
			return Delegate().peek();
		}

		@Override
		public default O pop()
		{
			return Delegate().pop();
		}
	}
	
	/**
	 * A {@code Java Queue} defines a wrapper around a {@code Queue}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see IterableSet
	 * @see Queue
	 */
	public static interface Java<O> extends IterableSet.Java<O>, Queue<O>
	{
		@Override
		public abstract java.util.Queue<O> Delegate();
		
		
		@Override
		public default Iterator<O> iterator()
		{
			return Delegate().iterator();
		}
		
		@Override
		public default void remove(O obj)
		{
			Delegate().remove(obj);
		}
		
		@Override
		public default void push(O obj)
		{
			Delegate().add(obj);
		}
		
		@Override
		public default int Count()
		{
			return Delegate().size();
		}

		@Override
		public default O peek()
		{
			return Delegate().peek();
		}

		@Override
		public default O pop()
		{
			return Delegate().poll();
		}
	}
	
	
	// REMOVE THE REMOVE METHOD OR NO?
	// Maybe just implement it where needed
	public static final int I = 5;
	
	/**
	 * Removes an object from the {@code Queue}.
	 * 
	 * @param obj  an object to remove
	 */
	public default void remove(O obj)
	{
		// NOT APPLICABLE
	}
	
	/**
	 * Pushes an object onto the {@code Queue}.
	 * 
	 * @param obj  an object to add
	 */
	public abstract void push(O obj);
	
	
	/**
	 * Peeks at the start of the {@code Queue}.
	 * 
	 * @return  the first object
	 */
	public abstract O peek();
			
	/**
	 * Pops from the start of the {@code Queue}.
	 * 
	 * @return  the first object
	 */
	public abstract O pop();
}