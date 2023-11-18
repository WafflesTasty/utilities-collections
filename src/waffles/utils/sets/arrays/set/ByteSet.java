package waffles.utils.sets.arrays.set;

import waffles.utils.sets.arrays.ArraySet;
import waffles.utils.sets.arrays.like.ByteArray;

/**
 * A {@code ByteSet} maintains a primitive byte array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArraySet
 * @see ByteArray
 */
@FunctionalInterface
public interface ByteSet extends ArraySet<byte[], Byte>, ByteArray
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