package waffles.utils.sets.indexed.array.set;

import java.util.Iterator;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.indexed.array.like.LongArray;
import waffles.utils.sets.utilities.indexed.iterators.arrays.ArrayValues;

/**
 * A {@code LongSet} maintains a primitive long array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see LongArray
 * @see ArraySet
 */
@FunctionalInterface
public interface LongSet extends ArraySet<long[], Long>, LongArray
{
	/**
	 * Wraps a {@code LongSet} around an array.
	 * 
	 * @param set  a long array
	 * @return  a long set
	 */
	public static LongSet of(long... set)
	{
		return () -> set;
	}
	
	
	@Override
	public default Iterator<Long> iterator()
	{
		return new ArrayValues<>(this);
	}
	
	@Override
	public default int[] indexOf(Long l)
	{
		for(int k = 0; k < Count(); k++)
		{
			if(Array()[k] == l)
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