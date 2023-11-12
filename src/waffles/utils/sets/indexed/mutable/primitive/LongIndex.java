package waffles.utils.sets.indexed.mutable.primitive;

import waffles.utils.sets.countable.LongArray;
import waffles.utils.tools.primitives.Array;

/**
 * An {@code LongIndex} provides a primitive-friendly alternative for an {@code ArrayIndex<Long>}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see LongArray
 */
public class LongIndex implements LongArray
{
	private Order order;
	private int[] dimension;
	private long[] data;
	
	/**
	 * Creates a new {@code LongIndex}.
	 * 
	 * @param ord  an index order
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public LongIndex(Order ord, long[] dat, int... dim)
	{
		dimension = dim;
		order = ord;
		data = dat;
	}
	
	/**
	 * Creates a new {@code LongIndex}.
	 * 
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public LongIndex(long[] dat, int... dim)
	{
		this(Order.COL_MAJOR, dat, dim);
	}
	
	/**
	 * Creates a new {@code LongIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public LongIndex(Order ord, int... dim)
	{
		order = ord;
		dimension = dim;
		data = new long[Count()];
	}
		
	/**
	 * Creates a new {@code LongIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public LongIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}


	@Override
	public long[] Array()
	{
		return data;
	}
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}
				
	@Override
	public LongIndex instance()
	{
		return new LongIndex(order, dimension);
	}
	
	@Override
	public LongIndex copy()
	{
		LongIndex copy = instance();
		copy.data = Array.copy.of(data);
		return copy;
	}
	
	@Override
	public void clear()
	{
		data = new long[Count()];
	}
}