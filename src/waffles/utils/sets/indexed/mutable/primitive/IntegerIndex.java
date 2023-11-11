package waffles.utils.sets.indexed.mutable.primitive;

import waffles.utils.sets.countable.IntegerArray;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.primitives.Array;

/**
 * An {@code IntegerIndex} provides a primitive-friendly alternative for an {@code ArrayIndex<Integer>}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see IntegerArray
 * @see Integer
 */
public class IntegerIndex implements IntegerArray, MutableIndex<Integer>
{
	private Order order;
	private int[] dimension;
	private int[] data;
	
	/**
	 * Creates a new {@code IntegerIndex}.
	 * 
	 * @param ord  an index order
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public IntegerIndex(Order ord, int[] dat, int... dim)
	{
		dimension = dim;
		order = ord;
		data = dat;
	}
	
	/**
	 * Creates a new {@code IntegerIndex}.
	 * 
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public IntegerIndex(int[] dat, int... dim)
	{
		this(Order.COL_MAJOR, dat, dim);
	}
	
	/**
	 * Creates a new {@code IntegerIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public IntegerIndex(Order ord, int... dim)
	{
		order = ord;
		dimension = dim;
		data = new int[Count()];
	}
		
	/**
	 * Creates a new {@code IntegerIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public IntegerIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}


	@Override
	public void clear()
	{
		data = new int[Count()];
	}
		
	@Override
	public Integer get(int... coords)
	{
		return data[toIndex(order, coords)];
	}
	
	@Override
	public Integer put(Integer val, int... coords)
	{
		int index = toIndex(order, coords);
		int prev = data[index];
		data[index] = val;
		return prev;
	}
	
	@Override
	public Integer remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public IntegerIndex instance()
	{
		return new IntegerIndex(order, dimension);
	}
	
	@Override
	public IntegerIndex copy()
	{
		IntegerIndex copy = instance();
		copy.data = Array.copy.of(data);
		return copy;
	}
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}
	
	@Override
	public int[] Array()
	{
		return data;
	}
	
	@Override
	public int Count()
	{
		return MutableIndex.super.Count();
	}
}