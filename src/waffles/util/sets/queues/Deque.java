package waffles.util.sets.queues;

import waffles.util.sets.AtomicSet;

/**
 * A {@code Deque} defines an atomic set of objects with two-way access to the head or tail.
 *
 * @author Waffles
 * @since 23 May 2021
 * @version 1.0
 * 
 * 
 * @param <O>  a queue object type
 * @see AtomicSet
 */
public interface Deque<O> extends AtomicSet<O>
{
	/**
	 * Pushes an object to the start of the {@code Deque}.
	 * 
	 * @param obj  a queue object
	 */
	public abstract void pushFirst(O obj);
	
	/**
	 * Pushes an object to the end of the {@code Deque}.
	 * 
	 * @param obj  a queue object
	 */
	public abstract void pushLast(O obj);
		
	
	/**
	 * Peeks at the start of the {@code Deque}.
	 * 
	 * @return  the first queue object
	 */
	public abstract O peekFirst();
	
	/**
	 * Peeks at the end of the {@code Deque}.
	 * 
	 * @return  the last queue object
	 */
	public abstract O peekLast();
		
	/**
	 * Pops from the start of the {@code Deque}.
	 * 
	 * @return  the first queue object
	 */
	public abstract O popFirst();
	
	/**
	 * Pops from the end of the {@code Deque}.
	 * 
	 * @return  the last queue object
	 */
	public abstract O popLast();
}