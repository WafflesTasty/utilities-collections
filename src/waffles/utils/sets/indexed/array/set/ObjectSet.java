package waffles.utils.sets.indexed.array.set;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.indexed.array.like.ObjectArray;

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
	/**
	 * Wraps an {@code ObjectSet} around an array.
	 * 
	 * @param set  an object array
	 * @return  an object set
	 */
	public static ObjectSet<?> of(Object... set)
	{
		return () -> set;
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}