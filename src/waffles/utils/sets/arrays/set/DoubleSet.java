package waffles.utils.sets.arrays.set;

import waffles.utils.sets.arrays.ArraySet;
import waffles.utils.sets.arrays.like.DoubleArray;

/**
 * A {@code DoubleSet} maintains a primitive double array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArraySet
 * @see DoubleArray
 */
@FunctionalInterface
public interface DoubleSet extends ArraySet<double[], Double>, DoubleArray
{
	@Override
	public default int DataSize()
	{
		return Double.BYTES * Count();
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}