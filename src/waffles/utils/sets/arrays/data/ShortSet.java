package waffles.utils.sets.arrays.data;

import waffles.utils.sets.arrays.ArrayData;
import waffles.utils.sets.arrays.like.ShortArray;

/**
 * A {@code ShortSet} maintains a primitive short array as an {@code ArrayData} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ShortArray
 * @see ArrayData
 */
@FunctionalInterface
public interface ShortSet extends ArrayData<short[], Short>, ShortArray
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