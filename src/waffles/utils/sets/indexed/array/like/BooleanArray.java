package waffles.utils.sets.indexed.array.like;

import waffles.utils.sets.indexed.array.ArrayLike;
import waffles.utils.tools.patterns.properties.values.Copyable;

/**
 * A {@code BooleanArray} manages a primitive boolean array as an {@code ArrayLike}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayLike
 * @see Copyable
 */
public interface BooleanArray extends ArrayLike<boolean[], Boolean>, Copyable<BooleanArray>
{
	@Override
	public default Boolean get(int... coords)
	{
		return Array()[toIndex(Ordering(), coords)];
	}
	
	@Override
	public default Boolean put(Boolean val, int... coords)
	{
		int index = toIndex(Ordering(), coords);
		Boolean prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Boolean remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public default BooleanArray instance()
	{
		return () -> null;
	}
	
	@Override
	public default BooleanArray copy()
	{
		return () -> Array();
	}
}