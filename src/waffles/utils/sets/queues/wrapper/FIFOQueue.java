package waffles.utils.sets.queues.wrapper;

import java.util.LinkedList;

import waffles.utils.sets.queues.Queue;

/**
 * A {@code FIFOQueue} acts as a queue which always outputs
 * the oldest element of the queue first. This queue is
 * backed by a {@code java.util.LinkedList}.
 *
 * @author Waffles
 * @since 23 May 2021
 * @version 1.0
 * 
 *
 * @param <O>  an object type
 * @see Queue
 */
public class FIFOQueue<O> implements Queue.Java<O>
{
	private LinkedList<O> data;
		
	/**
	 * Creates a new {@code FIFOQueue}.
	 * 
	 * @param d  a data source
	 * 
	 * 
	 * @see LinkedList
	 */
	public FIFOQueue(LinkedList<O> d)
	{
		data = d;
	}

	/**
	 * Creates a new {@code FIFOQueue}.
	 */
	public FIFOQueue()
	{
		this(new LinkedList<>());
	}
	
	
	@Override
	public LinkedList<O> Delegate()
	{
		return data;
	}
	
	@Override
	public void push(O obj)
	{
		Delegate().addLast(obj);
	}
}