package waffles.utils.sets.indexed.array.like;

import waffles.utils.sets.indexed.array.ArrayLike;
import waffles.utils.tools.patterns.properties.values.Copyable;

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
	public default Long get(int... crds)
	{
		return Array()[toIndex(Ordering(), crds)];
	}
	
	@Override
	public default Long put(Long val, int... crds)
	{
		int index = toIndex(Ordering(), crds);
		Long prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Long remove(int... crds)
	{
		return put(null, crds);
	}
	
	@Override
	public default LongArray instance()
	{
		return () -> null;
	}
	
	@Override
	public default LongArray copy()
	{
		return () -> Array();
	}
}