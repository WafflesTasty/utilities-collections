package waffles.utils.sets.indexed.array.index;

import waffles.utils.sets.indexed.array.like.BooleanArray;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code BooleanIndex} provides a primitive-friendly alternative for an {@code ObjectIndex<Boolean>}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see BooleanArray
 */
public class BooleanIndex implements BooleanArray
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
	public boolean[] Array()
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
		data = new boolean[Count()];
	}
}