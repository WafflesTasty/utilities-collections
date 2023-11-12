package waffles.utils.sets.countable;

import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code LongArray} manages a primitive long array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see Copyable
 */
public interface LongArray extends Copyable<LongArray>, MutableIndex<Long>
{
	/**
	 * Returns the data of the {@code IntegerArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract long[] Array();
	
	
	@Override
	public default Long get(int... coords)
	{
		return Array()[toIndex(Order.COL_MAJOR, coords)];
	}
	
	@Override
	public default Long put(Long val, int... coords)
	{
		int index = toIndex(Order.COL_MAJOR, coords);
		long prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Long remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}