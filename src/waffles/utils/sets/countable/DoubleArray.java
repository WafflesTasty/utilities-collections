package waffles.utils.sets.countable;

import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code DoubleArray} manages a primitive double array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see Copyable
 */
public interface DoubleArray extends Copyable<DoubleArray>, MutableIndex<Double>
{
	/**
	 * Returns the data of the {@code DoubleArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract double[] Array();
	
	
	@Override
	public default Double get(int... coords)
	{
		return Array()[toIndex(Order.COL_MAJOR, coords)];
	}
	
	@Override
	public default Double put(Double val, int... coords)
	{
		int index = toIndex(Order.COL_MAJOR, coords);
		double prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Double remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}