package waffles.utils.sets.indexed.array.index;

import waffles.utils.sets.indexed.array.like.ObjectArray;

/**
 * An {@code ObjectIndex} implements a basic {@code ObjectArray}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see ObjectArray
 */
public class ObjectIndex<O> implements ObjectArray<O>
{
	private Order order;
	private Object[] data;
	private int[] dimension;

	/**
	 * Creates a new {@code ObjectIndex}.
	 * 
	 * @param ord  an index order
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public ObjectIndex(Order ord, Object[] dat, int... dim)
	{
		dimension = dim;
		order = ord;
		data = dat;
	}
	
	/**
	 * Creates a new {@code ObjectIndex}.
	 * 
	 * @param dat  an index data
	 * @param dim  an index dimension
	 */
	public ObjectIndex(Object[] dat, int... dim)
	{
		this(Order.COL_MAJOR, dat, dim);
	}
	
	/**
	 * Creates a new {@code ObjectIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public ObjectIndex(Order ord, int... dim)
	{		
		dimension = dim;
		data = new Object[Count()];
		order = ord;
	}
		
	/**
	 * Creates a new {@code ObjectIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public ObjectIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}

	
	@Override
	public Order Ordering()
	{
		return order;
	}
			
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}

	@Override
	public Object[] Array()
	{
		return data;
	}
	
	@Override
	public void clear()
	{
		data = new Object[Count()];
	}
}