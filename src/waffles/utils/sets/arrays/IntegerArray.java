package waffles.utils.sets.arrays;

import waffles.utils.sets.ArraySet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * An {@code IntegerArray} manages a primitive integer array as an {@code ArraySet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Copyable
 * @see ArraySet
 */
public interface IntegerArray extends Copyable<IntegerArray>, ArraySet<Integer>
{
	/**
	 * Returns the primitive array of the {@code IntegerArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract int[] PArray();
	
	@Override
	public default Integer[] Array()
	{
		Integer[] data = new Integer[Count()];
		for(int i = 0; i < Count(); i++)
		{
			data[i] = PArray()[i];
		}
		
		return data;
	}
}