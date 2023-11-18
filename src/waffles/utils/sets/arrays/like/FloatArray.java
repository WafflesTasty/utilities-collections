package waffles.utils.sets.arrays.like;

import waffles.utils.sets.arrays.ArrayLike;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code FloatArray} manages a primitive float array as an {@code ArrayLike}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayLike
 * @see Copyable
 */
public interface FloatArray extends ArrayLike<float[], Float>, Copyable<FloatArray>
{
	@Override
	public default Float get(int... coords)
	{
		return Array()[toIndex(Ordering(), coords)];
	}
	
	@Override
	public default Float put(Float val, int... coords)
	{
		int index = toIndex(Ordering(), coords);
		Float prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Float remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public default FloatArray instance()
	{
		return () -> null;
	}
	
	@Override
	public default FloatArray copy()
	{
		return () -> Array();
	}
	
	@Override
	public default int DataSize()
	{
		return Array().length;
	}
}