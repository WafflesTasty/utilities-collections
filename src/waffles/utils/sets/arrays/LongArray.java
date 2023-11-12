package waffles.utils.sets.arrays;

import waffles.utils.sets.ArrayLike;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code LongArray} manages a primitive long array as a {@code MutableIndex}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Copyable
 * @see MutableIndex
 * @see ArrayLike
 */
public interface LongArray extends ArrayLike<long[]>, Copyable<LongArray>, MutableIndex<Long>
{
	/**
	 * Returns the ordering of the {@code LongArray}.
	 * 
	 * @return  an index order
	 */
	public abstract Order Ordering();
	
	
	@Override
	public default Long get(int... coords)
	{
		return Array()[toIndex(Ordering(), coords)];
	}
	
	@Override
	public default Long put(Long val, int... coords)
	{
		int index = toIndex(Ordering(), coords);
		Long prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Long remove(int... coords)
	{
		return put(null, coords);
	}
}