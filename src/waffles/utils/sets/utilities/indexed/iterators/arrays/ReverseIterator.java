package waffles.utils.sets.utilities.indexed.iterators.arrays;

import java.util.Iterator;

import waffles.utils.sets.indexed.array.ArrayLike;
import waffles.utils.sets.indexed.array.ArraySet;

/**
 * A {@code ReverseIterator} iterates over an {@code ArraySet} in reverse order.
 * 
 * @author Waffles
 * @since Nov 20, 2014
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Iterator
 */
public class ReverseIterator<O> implements Iterator<O>
{
	private int index;
	private ArrayLike<?, O> data;
		
	/**
	 * Creates a new {@code ReverseIterator}.
	 * 
	 * @param set  an arraylike set
	 * @param idx  an index start
	 * 
	 * 
	 * @see ArraySet
	 */
	public ReverseIterator(ArraySet<?, O> set, int idx)
	{
		index = idx;
		data = set;
	}
	
	/**
	 * Creates a new {@code ReverseIterator}.
	 * 
	 * @param set  an arraylike set
	 * 
	 * 
	 * @see ArraySet
	 */
	public ReverseIterator(ArraySet<?, O> set)
	{
		this(set, set.Count());
	}
	
	
	@Override
	public boolean hasNext()
	{
		return index > 0;
	}

	@Override
	public O next()
	{
		return (O) data.get(--index);
	}
}