package waffles.utils.sets.countable;

import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code BooleanArray} manages a primitive boolean array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see Copyable
 */
public interface BooleanArray extends Copyable<BooleanArray>, MutableIndex<Boolean>
{
	/**
	 * Returns the primitive data of the {@code BooleanArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract boolean[] Array();
	
	
	@Override
	public default Boolean get(int... coords)
	{
		return Array()[toIndex(Order.COL_MAJOR, coords)];
	}
	
	@Override
	public default Boolean put(Boolean val, int... coords)
	{
		int index = toIndex(Order.COL_MAJOR, coords);
		boolean prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Boolean remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}