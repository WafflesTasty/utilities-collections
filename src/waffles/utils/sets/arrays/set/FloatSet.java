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
	@Override
	public default int Count()
	{
		return Array().length;
	}
}