package waffles.utils.sets.indexed.array.like;

import waffles.utils.sets.indexed.array.ArrayLike;
import waffles.utils.tools.patterns.properties.values.Copyable;

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
	public default Double get(int... crds)
	{
		return Array()[toIndex(Ordering(), crds)];
	}
	
	@Override
	public default Double put(Double val, int... crds)
	{
		int index = toIndex(Ordering(), crds);
		Double prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Double remove(int... crds)
	{
		return put(null, crds);
	}
	
	@Override
	public default DoubleArray instance()
	{
		return () -> null;
	}
	
	@Override
	public default DoubleArray copy()
	{
		return () -> Array();
	}
}