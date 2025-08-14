package waffles.utils.sets.indexed.array.index;

import waffles.utils.sets.indexed.array.like.DoubleArray;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code DoubleIndex} provides a primitive-friendly alternative for an {@code ObjectIndex<Double>}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see DoubleArray
 */
public class DoubleIndex implements DoubleArray
{
	private Order order;
	private int[] dimension;
	private double[] data;
	
	/**
	 * Creates a new {@code DoubleIndex}.
	 * 
	 * @param ord  an index order
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public DoubleIndex(Order ord, double[] dat, int... dim)
	{
		dimension = dim;
		order = ord;
		data = dat;
	}
	
	/**
	 * Creates a new {@code DoubleIndex}.
	 * 
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public DoubleIndex(double[] dat, int... dim)
	{
		this(Order.COL_MAJOR, dat, dim);
	}
	
	/**
	 * Creates a new {@code DoubleIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public DoubleIndex(Order ord, int... dim)
	{
		order = ord;
		dimension = dim;
		data = new double[Count()];
	}
		
	/**
	 * Creates a new {@code DoubleIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public DoubleIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}

		
	@Override
	public DoubleIndex instance()
	{
		return new DoubleIndex(order, dimension);
	}
	
	@Override
	public DoubleIndex copy()
	{
		DoubleIndex copy = instance();
		copy.data = Array.copy.of(data);
		return copy;
	}
	
	
	@Override
	public double[] Array()
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
		data = new double[Count()];
	}
}