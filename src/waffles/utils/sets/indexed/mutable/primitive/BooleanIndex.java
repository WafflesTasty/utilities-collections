package waffles.utils.sets.indexed.mutable.primitive;

import waffles.utils.sets.countable.BooleanArray;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code BooleanIndex} provides a primitive-friendly alternative for an {@code ArrayIndex<Boolean>}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see BooleanArray
 * @see Boolean
 */
public class BooleanIndex implements BooleanArray, MutableIndex<Boolean>
{
	private Order order;
	private int[] dimension;
	private boolean[] data;
	
	/**
	 * Creates a new {@code BooleanIndex}.
	 * 
	 * @param ord  an index order
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public BooleanIndex(Order ord, boolean[] dat, int... dim)
	{
		dimension = dim;
		order = ord;
		data = dat;
	}
	
	/**
	 * Creates a new {@code BooleanIndex}.
	 * 
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public BooleanIndex(boolean[] dat, int... dim)
	{
		this(Order.COL_MAJOR, dat, dim);
	}
	
	/**
	 * Creates a new {@code BooleanIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public BooleanIndex(Order ord, int... dim)
	{
		order = ord;
		dimension = dim;
		data = new boolean[Count()];
	}
		
	/**
	 * Creates a new {@code BooleanIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public BooleanIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}


	@Override
	public void clear()
	{
		data = new boolean[Count()];
	}
		
	@Override
	public Boolean get(int... coords)
	{
		return data[toIndex(order, coords)];
	}
	
	@Override
	public Boolean put(Boolean val, int... coords)
	{
		int index = toIndex(order, coords);
		boolean prev = data[index];
		data[index] = val;
		return prev;
	}
	
	@Override
	public Boolean remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public BooleanIndex instance()
	{
		return new BooleanIndex(order, dimension);
	}
	
	@Override
	public BooleanIndex copy()
	{
		BooleanIndex copy = instance();
		copy.data = Array.copy.of(data);
		return copy;
	}
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}
	
	@Override
	public boolean[] Data()
	{
		return data;
	}
	
	@Override
	public int Count()
	{
		return MutableIndex.super.Count();
	}
}