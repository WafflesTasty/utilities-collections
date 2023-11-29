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
	/**
	 * Wraps a {@code LongSet} around an array.
	 * 
	 * @param set  a long array
	 * @return  a long set
	 */
	public static LongSet of(long... set)
	{
		return () -> set;
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}