package waffles.utils.sets.arrays;

import waffles.utils.sets.ArrayLike;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code DoubleArray} manages a primitive double array as an {@code ArrayLike}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayLike
 * @see Copyable
 */
public interface DoubleArray extends ArrayLike<double[], Double>, Copyable<DoubleArray>
{
	@Override
	public default Double get(int... coords)
	{
		return Array()[toIndex(Ordering(), coords)];
	}
	
	@Override
	public default Double put(Double val, int... coords)
	{
		int index = toIndex(Ordering(), coords);
		Double prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Double remove(int... coords)
	{
		return put(null, coords);
	}
}