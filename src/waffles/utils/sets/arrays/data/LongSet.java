package waffles.utils.sets.arrays.data;

import waffles.utils.sets.arrays.ArrayData;
import waffles.utils.sets.arrays.like.LongArray;

/**
 * A {@code LongSet} maintains a primitive long array as an {@code ArrayData} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayData
 * @see LongArray
 */
@FunctionalInterface
public interface LongSet extends ArrayData<long[], Long>, LongArray
{
	/**
	 * Returns the byte size of a single long value.
	 */
	public static final int BYTESIZE = Long.BYTES;
	
	@Override
	public default int DataSize()
	{
		return Array().length;
	}
}