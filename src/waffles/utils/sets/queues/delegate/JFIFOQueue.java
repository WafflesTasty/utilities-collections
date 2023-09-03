package waffles.utils.sets.queues.delegate;

import java.util.LinkedList;

import waffles.utils.sets.mutable.DelegateSet;
import waffles.utils.sets.queues.Queue;

/**
 * A {@code JFIFOQueue} acts as a queue which always outputs
 * the oldest element of the queue first. This queue is
 * backed by a Java {@code LinkedList}.
 *
 * @author Waffles
 * @since 23 May 2021
 * @version 1.0
 * 
 *
 * @param <O>  a queue object type
 * @see DelegateSet
 * @see Queue
 */
public class JFIFOQueue<O> implements DelegateSet<O>, Queue<O>
{
	private LinkedList<O> data;
		
	/**
	 * Creates a new {@code JFIFOQueue}.
	 * 
	 * @param data  a data source
	 * 
	 * 
	 * @see LinkedList
	 */
	public JFIFOQueue(LinkedList<O> data)
	{
		this.data = data;
	}

	/**
	 * Creates a new {@code JFIFOQueue}.
	 */
	public JFIFOQueue()
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
	
	@Override
	public O peek()
	{
		return Delegate().peek();
	}
	
	@Override
	public O pop()
	{
		return Delegate().pop();
	}
}