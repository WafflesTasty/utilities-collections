package waffles.utils.sets.indexed.array.set;

import java.util.Iterator;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.indexed.array.like.ShortArray;
import waffles.utils.sets.utilities.indexed.iterators.arrays.ArrayValues;

/**
 * A {@code ShortSet} maintains a primitive short array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ShortArray
 * @see ArraySet
 */
@FunctionalInterface
public interface ShortSet extends ArraySet<short[], Short>, ShortArray
{
	/**
	 * Wraps a {@code ShortSet} around an array.
	 * 
	 * @param set  a short array
	 * @return  a short set
	 */
	public static ShortSet of(short... set)
	{
		return () -> set;
	}
	
	
	@Override
	public default Iterator<Short> iterator()
	{
		return new ArrayValues<>(this);
	}
	
	@Override
	public default int[] indexOf(Short s)
	{
		for(int k = 0; k < Count(); k++)
		{
			if(Array()[k] == s)
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