package waffles.utils.sets.arrays.data;

import waffles.utils.sets.arrays.ArrayData;
import waffles.utils.sets.arrays.like.BooleanArray;

/**
 * A {@code BooleanSet} maintains a primitive boolean array as an {@code ArrayData} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see BooleanArray
 * @see ArrayData
 */
@FunctionalInterface
public interface BooleanSet extends ArrayData<boolean[], Boolean>, BooleanArray
{
	@Override
	public default int DataSize()
	{
		return Array().length;
	}
}