package waffles.utils.sets.arrays.set;

import waffles.utils.sets.arrays.ArraySet;
import waffles.utils.sets.arrays.like.ObjectArray;

/**
 * An {@code ObjectSet} maintains an object array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see ObjectArray
 * @see ArraySet
 */
@FunctionalInterface
public interface ObjectSet<O> extends ArraySet<Object[], O>, ObjectArray<O>
{
	@Override
	public default int Count()
	{
		return Array().length;
	}
}