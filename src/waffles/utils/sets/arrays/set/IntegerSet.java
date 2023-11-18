package waffles.utils.sets.arrays.set;

import waffles.utils.sets.arrays.ArraySet;
import waffles.utils.sets.arrays.like.IntegerArray;

/**
 * An {@code IntegerSet} maintains a primitive integer array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see IntegerArray
 * @see ArraySet
 */
@FunctionalInterface
public interface IntegerSet extends ArraySet<int[], Integer>, IntegerArray
{
	@Override
	public default int DataSize()
	{
		return Integer.BYTES * Count();
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}