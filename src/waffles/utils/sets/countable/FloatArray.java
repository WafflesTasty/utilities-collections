package waffles.utils.sets.countable;

import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code FloatArray} manages a primitive float array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see Copyable
 */
public interface FloatArray extends Copyable<FloatArray>, MutableIndex<Float>
{
	/**
	 * Returns the data of the {@code FloatArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract float[] Array();
	
	
	@Override
	public default Float get(int... coords)
	{
		return Array()[toIndex(Order.COL_MAJOR, coords)];
	}
	
	@Override
	public default Float put(Float val, int... coords)
	{
		int index = toIndex(Order.COL_MAJOR, coords);
		float prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Float remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}