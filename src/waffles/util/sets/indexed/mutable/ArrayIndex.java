package waffles.util.sets.indexed.mutable;

import waffles.util.sets.indexed.MutableIndex;

/**
 * An {@code ArrayIndex} implements an {@code Index} by internally storing data in an array.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an index object type
 * @see MutableIndex
 * @see ArrayIndex
 */
public class ArrayIndex<O> implements MutableIndex<O>
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
			
	/**
	 * Returns the array that stores the data
	 * of the {@code ArrayIndex}.
	 * 
	 * @return  a grid array
	 */
	public Object[] Array()
	{
		return data;
	}
	
	
	@Override
	public void clear()
	{
		data = new Object[Count()];
	}
		
	@Override
	public O get(int... coords)
	{
		return (O) data[toIndex(order, coords)];
	}
	
	@Override
	public O put(O val, int... coords)
	{
		int index = toIndex(order, coords);
		O prev = (O) data[index];
		data[index] = val;
		return prev;
	}
	
	@Override
	public O remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}
}