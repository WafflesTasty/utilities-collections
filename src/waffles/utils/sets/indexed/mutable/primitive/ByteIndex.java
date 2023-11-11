package waffles.utils.sets.indexed.mutable.primitive;

import waffles.utils.sets.countable.ByteArray;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code ByteIndex} provides a primitive-friendly alternative for an {@code ArrayIndex<Byte>}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see ByteArray
 * @see Byte
 */
public class ByteIndex implements ByteArray, MutableIndex<Byte>
{
	private Order order;
	private int[] dimension;
	private byte[] data;
	
	/**
	 * Creates a new {@code ByteIndex}.
	 * 
	 * @param ord  an index order
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public ByteIndex(Order ord, byte[] dat, int... dim)
	{
		dimension = dim;
		order = ord;
		data = dat;
	}
	
	/**
	 * Creates a new {@code ByteIndex}.
	 * 
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public ByteIndex(byte[] dat, int... dim)
	{
		this(Order.COL_MAJOR, dat, dim);
	}
	
	/**
	 * Creates a new {@code ByteIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public ByteIndex(Order ord, int... dim)
	{
		order = ord;
		dimension = dim;
		data = new byte[Count()];
	}
		
	/**
	 * Creates a new {@code ByteIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public ByteIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}


	@Override
	public void clear()
	{
		data = new byte[Count()];
	}
		
	@Override
	public Byte get(int... coords)
	{
		return data[toIndex(order, coords)];
	}
	
	@Override
	public Byte put(Byte val, int... coords)
	{
		int index = toIndex(order, coords);
		byte prev = data[index];
		data[index] = val;
		return prev;
	}
	
	@Override
	public Byte remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public ByteIndex instance()
	{
		return new ByteIndex(order, dimension);
	}
	
	@Override
	public ByteIndex copy()
	{
		ByteIndex copy = instance();
		copy.data = Array.copy.of(data);
		return copy;
	}
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}
	
	@Override
	public byte[] Array()
	{
		return data;
	}
	
	@Override
	public int Count()
	{
		return MutableIndex.super.Count();
	}
}