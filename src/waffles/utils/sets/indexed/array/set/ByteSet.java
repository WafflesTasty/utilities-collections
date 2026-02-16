package waffles.utils.sets.indexed.array.set;

import java.util.Iterator;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.indexed.array.like.ByteArray;
import waffles.utils.sets.utilities.indexed.iterators.arrays.ArrayValues;

/**
 * A {@code ByteSet} maintains a primitive byte array as an {@code ArraySet} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArraySet
 * @see ByteArray
 */
@FunctionalInterface
public interface ByteSet extends ArraySet<byte[], Byte>, ByteArray
{
	/**
	 * Wraps a {@code ByteSet} around an array.
	 * 
	 * @param set  a byte array
	 * @return  a byte set
	 */
	public static ByteSet of(byte... set)
	{
		return () -> set;
	}
	
	
	@Override
	public default Iterator<Byte> iterator()
	{
		return new ArrayValues<>(this);
	}
	
	@Override
	public default int[] indexOf(Byte b)
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