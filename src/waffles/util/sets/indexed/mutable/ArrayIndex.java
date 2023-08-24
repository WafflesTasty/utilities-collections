package waffles.util.sets.indexed.mutable;

import waffles.util.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;
import waffles.utils.tools.primitives.Array;

/**
 * An {@code ArrayIndex} implements an {@code Index} by internally storing data in an array.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an index object type
 * @see MutableIndex
 * @see ArrayIndex
 * @see Copyable
 */
public class ArrayIndex<O> implements MutableIndex<O>, Copyable<ArrayIndex<O>>
{
	private Order order;
	private Object[] data;
	private int[] dimension;
	
	/**
	 * Creates a new {@code ArrayIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public ArrayIndex(Order ord, int... dim)
	{
		order = ord;
		dimension = dim;
		data = new Object[Count()];
	}
	
	/**
	 * Creates a new {@code ArrayIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public ArrayIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}
			
	/**
	 * Returns the array that stores the data
	 * of the {@code ArrayIndex}.
	 * 
	 * @return  a grid array
	 */
	public Object[] Array()
	{
		return data;
	}
	
	
	@Override
	public void clear()
	{
		data = new Object[Count()];
	}
		
	@Override
	public O get(int... coords)
	{
		return (O) data[toIndex(order, coords)];
	}
	
	@Override
	public O put(O val, int... coords)
	{
		int index = toIndex(order, coords);
		O prev = (O) data[index];
		data[index] = val;
		return prev;
	}
	
	@Override
	public O remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}

	
	@Override
	public ArrayIndex<O> instance()
	{
		return new ArrayIndex<>(order, Dimensions());
	}
	
	@Override
	public ArrayIndex<O> copy()
	{
		ArrayIndex<O> copy = instance();
		copy.data = Array.copy.of(data);
		return copy;
	}
}