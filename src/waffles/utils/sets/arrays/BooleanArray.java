package waffles.utils.sets.arrays;

import waffles.utils.sets.ArrayLike;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code BooleanArray} manages a primitive boolean array as a {@code MutableIndex}.
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
public interface BooleanArray extends ArrayLike<boolean[]>, Copyable<BooleanArray>, MutableIndex<Boolean>
{
	/**
	 * Returns the ordering of the {@code BooleanArray}.
	 * 
	 * @return  an index order
	 */
	public abstract Order Ordering();
	
	
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
}