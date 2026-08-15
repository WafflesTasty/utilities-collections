package waffles.utils.sets.queues;

import java.util.Iterator;

import waffles.utils.sets.IterableSet;

/**
 * A {@code Deque} defines an {@code IterableSet} with two-way access to the head or tail.
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
public interface Deque<O> extends IterableSet<O>
{
	/**
	 * A {@code Wrapper} defines a wrapper around another {@code Deque}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see IterableSet
	 * @see Deque
	 */
	public static interface Wrapper<O> extends IterableSet.Wrapper<O>, Deque<O>
	{
		@Override
		public abstract Deque<O> Delegate();
		
		
		@Override
		public default Iterator<O> iterator()
		{
			return Delegate().iterator();
		}
		
		@Override
		public default void pushHead(O obj)
		{
			Delegate().pushHead(obj);
		}
		
		@Override
		public default void pushTail(O obj)
		{
			Delegate().pushTail(obj);
		}
		
		@Override
		public default O Head()
		{
			return Delegate().Head();
		}
		
		@Override
		public default O Tail()
		{
			return Delegate().Tail();
		}
		
		@Override
		public default O popHead()
		{
			return Delegate().popHead();
		}
		
		@Override
		public default O popTail()
		{
			return Delegate().popTail();
		}
		
		@Override
		public default int Count()
		{
			return Delegate().Count();
		}
	}
	
	/**
	 * A {@code Java Deque} defines a wrapper around a {@code Deque}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see IterableSet
	 * @see Deque
	 */
	public static interface Java<O> extends IterableSet.Java<O>, Deque<O>
	{
		@Override
		public abstract java.util.Deque<O> Delegate();
		
		
		@Override
		public default Iterator<O> iterator()
		{
			return Delegate().iterator();
		}
		
		@Override
		public default void pushHead(O obj)
		{
			Delegate().addFirst(obj);
		}
		
		@Override
		public default void pushTail(O obj)
		{
			Delegate().addLast(obj);
		}
		
		@Override
		public default O Head()
		{
			return Delegate().peekFirst();
		}
		
		@Override
		public default O Tail()
		{
			return Delegate().peekLast();
		}
		
		@Override
		public default O popHead()
		{
			return Delegate().pollFirst();
		}
		
		@Override
		public default O popTail()
		{
			return Delegate().pollLast();
		}
		
		@Override
		public default int Count()
		{
			return Delegate().size();
		}
	}
	
	
	/**
	 * Pushes an object to the head of the {@code Deque}.
	 * 
	 * @param obj  a queue object
	 */
	public abstract void pushHead(O obj);
	
	/**
	 * Pushes an object to the tail of the {@code Deque}.
	 * 
	 * @param obj  a queue object
	 */
	public abstract void pushTail(O obj);
		
			
	/**
	 * Pops from the head of the {@code Deque}.
	 * 
	 * @return  the first queue object
	 */
	public abstract O popHead();
	
	/**
	 * Pops from the tail of the {@code Deque}.
	 * 
	 * @return  the last queue object
	 */
	public abstract O popTail();


	/**
	 * Returns the head of the {@code Deque}.
	 * 
	 * @return  the first queue object
	 */
	public abstract O Head();
	
	/**
	 * Returns the tail of the {@code Deque}.
	 * 
	 * @return  the last queue object
	 */
	public abstract O Tail();
}