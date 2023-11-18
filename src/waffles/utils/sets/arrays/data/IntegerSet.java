package waffles.utils.sets.arrays.data;

import waffles.utils.sets.arrays.ArrayData;
import waffles.utils.sets.arrays.like.IntegerArray;

/**
 * An {@code IntegerSet} maintains a primitive integer array as an {@code ArrayData} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see IntegerArray
 * @see ArrayData
 */
@FunctionalInterface
public interface IntegerSet extends ArrayData<int[], Integer>, IntegerArray
{
	/**
	 * Returns the byte size of a single integer value.
	 */
	public static final int BYTESIZE = Integer.BYTES;
	
	@Override
	public default int DataSize()
	{
		return Array().length;
	}
}