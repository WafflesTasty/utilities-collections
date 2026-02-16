package waffles.utils.sets.indexed.array.set;

import java.util.Iterator;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.indexed.array.like.DoubleArray;
import waffles.utils.sets.utilities.indexed.iterators.arrays.ArrayValues;

/**
 * A {@code DoubleSet} maintains a primitive double array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArraySet
 * @see DoubleArray
 */
@FunctionalInterface
public interface DoubleSet extends ArraySet<double[], Double>, DoubleArray
{
	/**
	 * Wraps a {@code DoubleSet} around an array.
	 * 
	 * @param set  a double array
	 * @return  a double set
	 */
	public static DoubleSet of(double... set)
	{
		return () -> set;
	}
	
	
	@Override
	public default Iterator<Double> iterator()
	{
		return new ArrayValues<>(this);
	}
	
	@Override
	public default int[] indexOf(Double d)
	{
		for(int k = 0; k < Count(); k++)
		{
			if(Array()[k] == d)
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