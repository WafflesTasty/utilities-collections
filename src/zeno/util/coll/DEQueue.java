package zeno.util.coll;

/**
 * The {@code DEQueue} class defines a double-ended queue of objects.
 *
 * @author Waffles
 * @since 23 May 2021
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Iterable
 */
public interface DEQueue<O> extends Iterable<O>
{
	/**
	 * Pushes an object at the start of the {@code DEQueue}.
	 * 
	 * @param obj  an object to add
	 */
	public abstract void pushFirst(O obj);
	
	/**
	 * Pushes an object at the end of the {@code DEQueue}.
	 * 
	 * @param obj  an object to add
	 */
	public abstract void pushLast(O obj);
		
	/**
	 * Checks if there is contents in the {@code DEQueue}.
	 * 
	 * @return  {@code true} if the queue is empty
	 */
	public abstract boolean isEmpty();
	
	/**
	 * Returns the count of the {@code DEQueue}.
	 * 
	 * @return  an object count
	 */
	public abstract int Count();
	
	
	/**
	 * Peeks at the start of the {@code DEQueue}.
	 * 
	 * @return  the first object
	 */
	public abstract O peekFirst();
	
	/**
	 * Peeks at the end of the {@code DEQueue}.
	 * 
	 * @return  the last object
	 */
	public abstract O peekLast();
		
	/**
	 * Pops from the start of the {@code DEQueue}.
	 * 
	 * @return  the first object
	 */
	public abstract O popFirst();
	
	/**
	 * Pops from the end of the {@code DEQueue}.
	 * 
	 * @return  the last object
	 */
	public abstract O popLast();
}