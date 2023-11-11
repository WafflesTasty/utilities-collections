package waffles.utils.sets.indexed.mutable.primitive;

import waffles.utils.sets.countable.ShortArray;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.primitives.Array;

/**
 * An {@code ShortIndex} provides a primitive-friendly alternative for an {@code ArrayIndex<Short>}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see ShortArray
 * @see Short
 */
public class ShortIndex implements ShortArray, MutableIndex<Short>
{
	private Order order;
	private int[] dimension;
	private short[] data;
	
	/**
	 * Creates a new {@code ShortIndex}.
	 * 
	 * @param ord  an index order
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public ShortIndex(Order ord, short[] dat, int... dim)
	{
		dimension = dim;
		order = ord;
		data = dat;
	}
	
	/**
	 * Creates a new {@code ShortIndex}.
	 * 
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public ShortIndex(short[] dat, int... dim)
	{
		this(Order.COL_MAJOR, dat, dim);
	}
	
	/**
	 * Creates a new {@code ShortIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public ShortIndex(Order ord, int... dim)
	{
		order = ord;
		dimension = dim;
		data = new short[Count()];
	}
		
	/**
	 * Creates a new {@code ShortIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public ShortIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}


	@Override
	public void clear()
	{
		data = new short[Count()];
	}
		
	@Override
	public Short get(int... coords)
	{
		return data[toIndex(order, coords)];
	}
	
	@Override
	public Short put(Short val, int... coords)
	{
		int index = toIndex(order, coords);
		short prev = data[index];
		data[index] = val;
		return prev;
	}
	
	@Override
	public Short remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public ShortIndex instance()
	{
		return new ShortIndex(order, dimension);
	}
	
	@Override
	public ShortIndex copy()
	{
		ShortIndex copy = instance();
		copy.data = Array.copy.of(data);
		return copy;
	}
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}
	
	@Override
	public short[] Data()
	{
		return data;
	}
	
	@Override
	public int Count()
	{
		return MutableIndex.super.Count();
	}
}