package waffles.utils.sets.arrays.data;

import waffles.utils.sets.arrays.ArrayData;
import waffles.utils.sets.arrays.like.DoubleArray;

/**
 * A {@code DoubleSet} maintains a primitive double array as an {@code ArrayData} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayData
 * @see DoubleArray
 */
@FunctionalInterface
public interface DoubleSet extends ArrayData<double[], Double>, DoubleArray
{
	/**
	 * Returns the byte size of a single value.
	 */
	public static final int BYTESIZE = Double.BYTES;
	
	@Override
	public default int DataSize()
	{
		return Array().length;
	}
}