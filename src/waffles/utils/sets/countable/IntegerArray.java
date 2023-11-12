package waffles.utils.sets.countable;

import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * An {@code IntegerArray} manages a primitive integer array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see Copyable
 */
public interface IntegerArray extends Copyable<IntegerArray>, MutableIndex<Integer>
{
	/**
	 * Returns the data of the {@code IntegerArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract int[] Array();
	
	
	@Override
	public default Integer get(int... coords)
	{
		return Array()[toIndex(Order.COL_MAJOR, coords)];
	}
	
	@Override
	public default Integer put(Integer val, int... coords)
	{
		int index = toIndex(Order.COL_MAJOR, coords);
		int prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Integer remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}