package waffles.utils.sets.indexed.mutable;

import waffles.utils.sets.arrays.ObjectArray;

/**
 * An {@code ArrayIndex} implements a {@code MutableIndex} by internally storing data in an array.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an index object type
 * @see ObjectArray
 */
public class ArrayIndex<O> implements ObjectArray<O>
{
	private Order order;
	private int[] dimension;
	private Object[] data;
	
	/**
	 * Creates a new {@code ArrayIndex}.
	 * 
	 * @param ord  an index order
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public ArrayIndex(Order ord, Object[] dat, int... dim)
	{
		dimension = dim;
		order = ord;
		data = dat;
	}
	
	/**
	 * Creates a new {@code ArrayIndex}.
	 * 
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public ArrayIndex(Object[] dat, int... dim)
	{
		this(Order.COL_MAJOR, dat, dim);
	}
	
	/**
	 * Creates a new {@code ArrayIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public ArrayIndex(Order ord, int... dim)
	{
		order = ord;
		dimension = dim;
		data = new Object[Count()];
	}
		
	/**
	 * Creates a new {@code ArrayIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public ArrayIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}

	
	@Override
	public Order Ordering()
	{
		return order;
	}
			
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}

	@Override
	public Object[] Array()
	{
		return data;
	}
	
	@Override
	public void clear()
	{
		data = new Object[Count()];
	}
}