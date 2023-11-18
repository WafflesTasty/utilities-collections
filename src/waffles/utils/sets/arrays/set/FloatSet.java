package waffles.utils.sets.arrays.set;

import waffles.utils.sets.arrays.ArraySet;
import waffles.utils.sets.arrays.like.FloatArray;

/**
 * A {@code FloatSet} maintains a primitive float array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see FloatArray
 * @see ArraySet
 */
@FunctionalInterface
public interface FloatSet extends ArraySet<float[], Float>, FloatArray
{
	/**
	 * Returns the byte size of a single float value.
	 */
	public static final int BYTESIZE = Float.BYTES;

	@Override
	public default int DataSize()
	{
		return Array().length;
	}
}