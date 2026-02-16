package waffles.utils.sets.indexed.array.set;

import java.util.Iterator;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.indexed.array.like.ObjectArray;
import waffles.utils.sets.utilities.indexed.iterators.arrays.ArrayValues;

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
	public static <O> ObjectSet<O> of(Object... set)
	{
		return () -> set;
	}
	
	
	@Override
	public default Iterator<O> iterator()
	{
		return new ArrayValues<>(this);
	}
	
	@Override
	public default int[] indexOf(O o)
	{
		for(int k = 0; k < Count(); k++)
		{
			if(Array()[k] == o)
			{
				return new int[]{k};
			}
		}
		
		return null;
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}