package zeno.util.coll;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * The {@code Queue} class defines a queue of objects.
 *
 * @author Zeno
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Iterable
 */
public class Queue<O> implements Iterable<O>
{
	private LinkedList<O> data;
	
	/**
	 * Creates a new {@code Queue}.
	 * 
	 * @param set  a data set
	 */
	public Queue(O... set)
	{
		data = new LinkedList<>(Arrays.asList(set));
	}
	
	/**
	 * Creates a new {@code Queue}.
	 */
	public Queue()
	{
		data = new LinkedList<>();
	}
	
		
	/**
	 * Pushes an object at the start of the {@code Queue}.
	 * 
	 * @param obj  an object to add
	 */
	public void pushFirst(O obj)
	{
		data.addFirst(obj);
	}
	
	/**
	 * Pushes an object at the end of the {@code Queue}.
	 * 
	 * @param obj  an object to add
	 */
	public void pushLast(O obj)
	{
		data.addLast(obj);
	}
	
	/**
	 * Checks if there is contents in the {@code Queue}.
	 * 
	 * @return  {@code true} if the queue is empty
	 */
	public boolean isEmpty()
	{
		return data.isEmpty();
	}
	
	
	/**
	 * Peeks at the end of the {@code Queue}.
	 * 
	 * @return  the last object
	 */
	public O peekLast()
	{
		return data.peekLast();
	}
	
	/**
	 * Peeks at the start of the {@code Queue}.
	 * 
	 * @return  the first object
	 */
	public O peekFirst()
	{
		return data.peekFirst();
	}
	
	/**
	 * Pops from the start of the {@code Queue}.
	 * 
	 * @return  the first object
	 */
	public O popFirst()
	{
		return data.pollFirst();
	}
	
	/**
	 * Pops from the end of the {@code Queue}.
	 * 
	 * @return  the last object
	 */
	public O popLast()
	{
		return data.pollLast();
	}

	
	
	@Override
	public Iterator<O> iterator()
	{
		return data.iterator();
	}
}