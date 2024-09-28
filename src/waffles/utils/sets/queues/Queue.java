package waffles.utils.sets.queues;

import waffles.utils.sets.CountableSet;

/**
 * A {@code Queue} defines an atomic set of objects with one-way access to the head or tail.
 * </br> The specific order of the objects coming in and going out of the queue is left to the implementation.
 *
 * @author Waffles
 * @since 23 May 2021
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see CountableSet
 * @see Iterable
 */
public interface Queue<O> extends CountableSet, Iterable<O>
{
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