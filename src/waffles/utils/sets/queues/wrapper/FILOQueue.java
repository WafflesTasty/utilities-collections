package waffles.utils.sets.queues.wrapper;

import java.util.LinkedList;

import waffles.utils.sets.queues.Queue;

/**
 * A {@code FILOQueue} acts as a queue which always outputs
 * the newest element of the queue first. This queue is
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
public class FILOQueue<O> implements Queue.Java<O>
{
	private LinkedList<O> data;
		
	/**
	 * Creates a new {@code FILOQueue}.
	 * 
	 * @param d  a data source
	 * 
	 * 
	 * @see LinkedList
	 */
	public FILOQueue(LinkedList<O> d)
	{
		data = d;
	}

	/**
	 * Creates a new {@code FILOQueue}.
	 */
	public FILOQueue()
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
		Delegate().addFirst(obj);
	}
}