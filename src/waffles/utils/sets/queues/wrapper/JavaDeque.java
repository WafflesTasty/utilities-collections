package waffles.utils.sets.queues.wrapper;

import java.util.ArrayDeque;
import java.util.Arrays;

import waffles.utils.sets.queues.Deque;

/**
 * A {@code JavaDeque} is a double-ended queue backed by as {@code java.util.ArrayDeque}.
 * 
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Deque
 */
public class JavaDeque<O> implements Deque.Java<O>
{
	private ArrayDeque<O> data;
	
	/**
	 * Creates a new {@code JavaDeque}.
	 * 
	 * @param d  a data source
	 * 
	 * 
	 * @see ArrayDeque
	 */
	public JavaDeque(ArrayDeque<O> d)
	{
		data = d;
	}
	
	/**
	 * Creates a new {@code JavaDeque}.
	 * 
	 * @param set  a data set
	 */
	public JavaDeque(O... set)
	{
		this(new ArrayDeque<>(Arrays.asList(set)));
	}
	
	/**
	 * Creates a new {@code JavaDeque}.
	 */
	public JavaDeque()
	{
		data = new ArrayDeque<>();
	}
	
	
	@Override
	public ArrayDeque<O> Delegate()
	{
		return data;
	}
}