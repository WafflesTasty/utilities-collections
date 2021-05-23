package zeno.util.coll.queues;

import java.util.Iterator;
import java.util.LinkedList;

import zeno.util.coll.Queue;

/**
 * The {@code FIFOQueue} class defines a single-ended FIFO queue.
 *
 * @author Waffles
 * @since 23 May 2021
 * @version 1.0
 * 
 *
 * @param <O>  an object type
 * @see Queue
 */
public class FIFOQueue<O> implements Queue<O>
{
	private LinkedList<O> data;
	
	/**
	 * Creates a new {@code FIFOQueue}.
	 */
	public FIFOQueue()
	{
		data = new LinkedList<>();
	}
	
	
	@Override
	public void push(O obj)
	{
		data.push(obj);
	}
	
	@Override
	public O peek()
	{
		return data.peek();
	}

	@Override
	public O pop()
	{
		return data.pop();
	}

	
	@Override
	public Iterator<O> iterator()
	{
		return data.iterator();
	}

	@Override
	public boolean isEmpty()
	{
		return data.isEmpty();
	}

	@Override
	public int Count()
	{
		return data.size();
	}
}