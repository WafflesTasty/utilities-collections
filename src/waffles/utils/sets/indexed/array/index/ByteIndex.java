package waffles.utils.sets.indexed.array.index;

import waffles.utils.sets.indexed.array.like.ByteArray;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code ByteIndex} provides a primitive-friendly alternative for an {@code ObjectIndex<Byte>}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see ByteArray
 */
public class ByteIndex implements ByteArray
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
	public byte[] Array()
	{
		return data;
	}
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}
	
	@Override
	public Order Ordering()
	{
		return order;
	}
	
	@Override
	public void clear()
	{
		data = new byte[Count()];
	}
}