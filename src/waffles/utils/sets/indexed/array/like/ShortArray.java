package waffles.utils.sets.indexed.array.like;

import waffles.utils.sets.indexed.array.ArrayLike;
import waffles.utils.tools.patterns.properties.values.Copyable;

/**
 * A {@code ShortArray} manages a primitive short array as an {@code ArrayLike}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayLike
 * @see Copyable
 */
public interface ShortArray extends ArrayLike<short[], Short>, Copyable<ShortArray>
{
	@Override
	public default Short get(int... crds)
	{
		return Array()[toIndex(Ordering(), crds)];
	}
	
	@Override
	public default Short put(Short val, int... crds)
	{
		int index = toIndex(Ordering(), crds);
		Short prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Short remove(int... crds)
	{
		return put(null, crds);
	}
	
	@Override
	public default ShortArray instance()
	{
		return () -> null;
	}
	
	@Override
	public default ShortArray copy()
	{
		return () -> Array();
	}
}