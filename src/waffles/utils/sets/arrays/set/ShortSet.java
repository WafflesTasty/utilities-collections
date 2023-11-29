package waffles.utils.sets.arrays.set;

import waffles.utils.sets.arrays.ArraySet;
import waffles.utils.sets.arrays.like.ShortArray;

/**
 * A {@code ShortSet} maintains a primitive short array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ShortArray
 * @see ArraySet
 */
@FunctionalInterface
public interface ShortSet extends ArraySet<short[], Short>, ShortArray
{
	/**
	 * Wraps a {@code ShortSet} around an array.
	 * 
	 * @param set  a short array
	 * @return  a short set
	 */
	public static ShortSet of(short... set)
	{
		return () -> set;
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}