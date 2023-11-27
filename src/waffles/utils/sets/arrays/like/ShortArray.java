package waffles.utils.sets.arrays.like;

import waffles.utils.sets.arrays.ArrayLike;
import waffles.utils.tools.patterns.semantics.Copyable;
import waffles.utils.tools.primitives.Shorts;

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
	public default Short get(int... coords)
	{
		return Array()[toIndex(Ordering(), coords)];
	}
	
	@Override
	public default Short put(Short val, int... coords)
	{
		int index = toIndex(Ordering(), coords);
		Short prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Short remove(int... coords)
	{
		return put(null, coords);
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
	
	@Override
	public default int DataSize()
	{
		return Shorts.BYTE_SIZE * Count();
	}
}