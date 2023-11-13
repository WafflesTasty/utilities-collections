package waffles.utils.sets.arrays;

import waffles.utils.sets.ArrayLike;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code LongArray} manages a primitive long array as an {@code ArrayLike}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayLike
 * @see Copyable
 */
public interface LongArray extends ArrayLike<long[], Long>, Copyable<LongArray>
{
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