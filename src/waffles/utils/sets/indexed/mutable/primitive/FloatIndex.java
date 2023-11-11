package waffles.utils.sets.indexed.mutable.primitive;

import waffles.utils.sets.countable.FloatArray;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code FloatIndex} provides a primitive-friendly alternative for an {@code ArrayIndex<Float>}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see FloatArray
 * @see Float
 */
public class FloatIndex implements FloatArray, MutableIndex<Float>
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
	public void clear()
	{
		data = new float[Count()];
	}
		
	@Override
	public Float get(int... coords)
	{
		return data[toIndex(order, coords)];
	}
	
	@Override
	public Float put(Float val, int... coords)
	{
		int index = toIndex(order, coords);
		float prev = data[index];
		data[index] = val;
		return prev;
	}
	
	@Override
	public Float remove(int... coords)
	{
		return put(null, coords);
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
	public int[] Dimensions()
	{
		return dimension;
	}
	
	@Override
	public float[] Data()
	{
		return data;
	}
	
	@Override
	public int Count()
	{
		return MutableIndex.super.Count();
	}
}