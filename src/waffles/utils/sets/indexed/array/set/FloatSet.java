package waffles.utils.sets.indexed.array.set;

import java.util.Iterator;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.indexed.array.like.FloatArray;
import waffles.utils.sets.utilities.indexed.iterators.arrays.ArrayValues;

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
	/**
	 * Wraps a {@code FloatSet} around an array.
	 * 
	 * @param set  a float array
	 * @return  a float set
	 */
	public static FloatSet of(float... set)
	{
		return () -> set;
	}
	
	
	@Override
	public default Iterator<Float> iterator()
	{
		return new ArrayValues<>(this);
	}
	
	@Override
	public default int[] indexOf(Float f)
	{
		for(int k = 0; k < Count(); k++)
		{
			if(Array()[k] == f)
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