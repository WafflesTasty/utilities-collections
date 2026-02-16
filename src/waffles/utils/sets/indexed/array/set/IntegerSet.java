package waffles.utils.sets.indexed.array.set;

import java.util.Iterator;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.indexed.array.like.IntegerArray;
import waffles.utils.sets.utilities.indexed.iterators.arrays.ArrayValues;

/**
 * An {@code IntegerSet} maintains a primitive integer array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see IntegerArray
 * @see ArraySet
 */
@FunctionalInterface
public interface IntegerSet extends ArraySet<int[], Integer>, IntegerArray
{
	/**
	 * Wraps a {@code IntegerSet} around an array.
	 * 
	 * @param set  an integer array
	 * @return  an integer set
	 */
	public static IntegerSet of(int... set)
	{
		return () -> set;
	}
	
	
	@Override
	public default Iterator<Integer> iterator()
	{
		return new ArrayValues<>(this);
	}
	
	@Override
	public default int[] indexOf(Integer i)
	{
		for(int k = 0; k < Count(); k++)
		{
			if(Array()[k] == i)
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