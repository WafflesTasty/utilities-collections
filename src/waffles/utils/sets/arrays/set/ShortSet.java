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
	 * Returns the byte size of a single value.
	 */
	public static final int BYTESIZE = Short.BYTES;
	
	@Override
	public default int DataSize()
	{
		return Array().length;
	}
}