package zeno.util.coll.queues;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import zeno.util.coll.DEQueue;

/**
 * The {@code LLQueue} class defines a double-ended queue as a {@code LinkedList} wrapper.
 *
 * @author Zeno
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see DEQueue
 */
public class LLQueue<O> implements DEQueue<O>
{
	private LinkedList<O> data;
	
	/**
	 * Creates a new {@code LLQueue}.
	 * 
	 * @param set  a data set
	 */
	public LLQueue(O... set)
	{
		data = new LinkedList<>(Arrays.asList(set));
	}
	
	/**
	 * Creates a new {@code LLQueue}.
	 */
	public LLQueue()
	{
		data = new LinkedList<>();
	}
	

	@Override
	public void pushFirst(O obj)
	{
		data.addFirst(obj);
	}

	@Override
	public void pushLast(O obj)
	{
		data.addLast(obj);
	}

	
	@Override
	public O peekFirst()
	{
		return data.peekFirst();
	}

	@Override
	public O peekLast()
	{
		return data.peekLast();
	}

	@Override
	public O popFirst()
	{
		return data.pollFirst();
	}

	@Override
	public O popLast()
	{
		return data.pollLast();
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