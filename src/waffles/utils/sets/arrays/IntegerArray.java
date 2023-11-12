package waffles.utils.sets.arrays;

import waffles.utils.sets.ArrayLike;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * An {@code IntegerArray} manages a primitive integer array as a {@code MutableIndex}.
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
public interface IntegerArray extends ArrayLike<int[]>, Copyable<IntegerArray>, MutableIndex<Integer>
{
	/**
	 * Returns the ordering of the {@code IntegerArray}.
	 * 
	 * @return  an index order
	 */
	public abstract Order Ordering();
	
	
	@Override
	public default Integer get(int... coords)
	{
		return Array()[toIndex(Ordering(), coords)];
	}
	
	@Override
	public default Integer put(Integer val, int... coords)
	{
		int index = toIndex(Ordering(), coords);
		Integer prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Integer remove(int... coords)
	{
		return put(null, coords);
	}
}