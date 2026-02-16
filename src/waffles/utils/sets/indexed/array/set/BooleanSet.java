package waffles.utils.sets.indexed.array.set;

import java.util.Iterator;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.indexed.array.like.BooleanArray;
import waffles.utils.sets.utilities.indexed.iterators.arrays.ArrayValues;

/**
 * A {@code BooleanSet} maintains a primitive boolean array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see BooleanArray
 * @see ArraySet
 */
@FunctionalInterface
public interface BooleanSet extends ArraySet<boolean[], Boolean>, BooleanArray
{	
	/**
	 * Wraps a {@code BooleanSet} around an array.
	 * 
	 * @param set  a boolean array
	 * @return  a boolean set
	 */
	public static BooleanSet of(boolean... set)
	{
		return () -> set;
	}
	
	@Override
	public default Iterator<Boolean> iterator()
	{
		return new ArrayValues<>(this);
	}
	
	@Override
	public default int[] indexOf(Boolean b)
	{
		for(int k = 0; k < Count(); k++)
		{
			if(Array()[k] == b)
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