package zeno.util.coll;

/**
 * The {@code Queue} class defines a queue of objects.
 *
 * @author Waffles
 * @since 23 May 2021
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Iterable
 */
public interface Queue<O> extends Iterable<O>
{
	/**
	 * Pushes an object onto the {@code Queue}.
	 * 
	 * @param obj  an object to add
	 */
	public abstract void push(O obj);
		
	/**
	 * Checks if objects are in the {@code Queue}.
	 * 
	 * @return  {@code true} if the queue is empty
	 */
	public abstract boolean isEmpty();
	
	/**
	 * Returns the count of the {@code Queue}.
	 * 
	 * @return  an object count
	 */
	public abstract int Count();
	
	
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