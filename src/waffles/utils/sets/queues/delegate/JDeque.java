package waffles.utils.sets.queues.delegate;

import java.util.ArrayDeque;
import java.util.Arrays;

import waffles.utils.sets.mutable.DelegateSet;
import waffles.utils.sets.queues.Deque;

/**
 * A {@code JDeque} defines a double-ended queue
 * which is backed by a Java {@code ArrayDeque}.
 * 
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  a queue object type
 * @see DelegateSet
 * @see Deque
 */
public class JDeque<O> implements DelegateSet<O>, Deque<O>
{
	private ArrayDeque<O> data;
	
	/**
	 * Creates a new {@code JDeque}.
	 * 
	 * @param data  a data source
	 * 
	 * 
	 * @see ArrayDeque
	 */
	public JDeque(ArrayDeque<O> data)
	{
		this.data = data;
	}
	
	/**
	 * Creates a new {@code JDeque}.
	 * 
	 * @param set  a data set
	 */
	public JDeque(O... set)
	{
		this(new ArrayDeque<>(Arrays.asList(set)));
	}
	
	/**
	 * Creates a new {@code JDeque}.
	 */
	public JDeque()
	{
		data = new ArrayDeque<>();
	}
	
	
	@Override
	public ArrayDeque<O> Delegate()
	{
		return data;
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
}