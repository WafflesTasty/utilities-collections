package waffles.utils.sets.indexed.mutable.primitive;

import waffles.utils.sets.arrays.FloatArray;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code FloatIndex} provides a primitive-friendly alternative for an {@code ArrayIndex<Float>}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see FloatArray
 */
public class FloatIndex implements FloatArray
{
	private Order order;
	private int[] dimension;
	private float[] data;
	
	/**
	 * Creates a new {@code FloatIndex}.
	 * 
	 * @param ord  an index order
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public FloatIndex(Order ord, float[] dat, int... dim)
	{
		order = ord;
		dimension = dim;
		data = dat;
	}
	
	/**
	 * Creates a new {@code FloatIndex}.
	 * 
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public FloatIndex(float[] dat, int... dim)
	{
		this(Order.COL_MAJOR, dat, dim);
	}
	
	/**
	 * Creates a new {@code FloatIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public FloatIndex(Order ord, int... dim)
	{
		order = ord;
		dimension = dim;
		data = new float[Count()];
	}
		
	/**
	 * Creates a new {@code FloatIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public FloatIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}


	@Override
	public FloatIndex instance()
	{
		return new FloatIndex(order, dimension);
	}
	
	@Override
	public FloatIndex copy()
	{
		FloatIndex copy = instance();
		copy.data = Array.copy.of(data);
		return copy;
	}
	
	
	@Override
	public float[] PArray()
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
		data = new float[Count()];
	}
}