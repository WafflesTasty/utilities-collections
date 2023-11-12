package waffles.utils.sets.arrays;

import waffles.utils.sets.ArrayLike;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code FloatArray} manages a primitive float array as an {@code ArraySet}.
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
public interface FloatArray extends ArrayLike<float[]>, Copyable<FloatArray>, MutableIndex<Float>
{
	/**
	 * Returns the ordering of the {@code FloatArray}.
	 * 
	 * @return  an index order
	 */
	public abstract Order Ordering();
	
	
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
}