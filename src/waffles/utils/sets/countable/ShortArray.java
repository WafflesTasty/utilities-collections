package waffles.utils.sets.countable;

import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code ShortArray} manages a primitive short array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see Copyable
 */
public interface ShortArray extends Copyable<ShortArray>, MutableIndex<Short>
{
	/**
	 * Returns the data of the {@code IntegerArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract short[] Array();
	
	
	@Override
	public default Short get(int... coords)
	{
		return Array()[toIndex(Order.COL_MAJOR, coords)];
	}
	
	@Override
	public default Short put(Short val, int... coords)
	{
		int index = toIndex(Order.COL_MAJOR, coords);
		short prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Short remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}