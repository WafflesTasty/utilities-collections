package waffles.utils.sets.arrays.data;

import waffles.utils.sets.arrays.ArrayData;
import waffles.utils.sets.arrays.like.ByteArray;

/**
 * A {@code ByteSet} maintains a primitive byte array as an {@code ArrayData} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayData
 * @see ByteArray
 */
@FunctionalInterface
public interface ByteSet extends ArrayData<byte[], Byte>, ByteArray
{
	/**
	 * Returns the byte size of a single value.
	 */
	public static final int BYTESIZE = Byte.BYTES;
	
	@Override
	public default int DataSize()
	{
		return Array().length;
	}
}