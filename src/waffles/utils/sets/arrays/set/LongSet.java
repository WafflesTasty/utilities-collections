package waffles.utils.sets.arrays.set;

import waffles.utils.sets.arrays.ArraySet;
import waffles.utils.sets.arrays.like.LongArray;

/**
 * A {@code LongSet} maintains a primitive long array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArraySet
 * @see LongArray
 */
@FunctionalInterface
public interface LongSet extends ArraySet<long[], Long>, LongArray
{
	@Override
	public default int DataSize()
	{
		return Long.BYTES * Count();
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}