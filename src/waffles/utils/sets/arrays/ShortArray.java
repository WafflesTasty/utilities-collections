package waffles.utils.sets.arrays;

import waffles.utils.sets.ArrayLike;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code ShortArray} manages a primitive short array as a {@code MutableIndex}.
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
public interface ShortArray extends ArrayLike<short[]>, Copyable<ShortArray>, MutableIndex<Short>
{
	/**
	 * Returns the ordering of the {@code ShortArray}.
	 * 
	 * @return  an index order
	 */
	public abstract Order Ordering();
	
	
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
}