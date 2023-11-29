package waffles.utils.sets.arrays.set;

import waffles.utils.sets.arrays.ArraySet;
import waffles.utils.sets.arrays.like.BooleanArray;

/**
 * A {@code BooleanSet} maintains a primitive boolean array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see BooleanArray
 * @see ArraySet
 */
@FunctionalInterface
public interface BooleanSet extends ArraySet<boolean[], Boolean>, BooleanArray
{	
	/**
	 * Wraps a {@code BooleanSet} around an array.
	 * 
	 * @param set  a boolean array
	 * @return  a boolean set
	 */
	public static BooleanSet of(boolean... set)
	{
		return () -> set;
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}