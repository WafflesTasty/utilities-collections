package waffles.utils.sets.arrays;

import waffles.utils.sets.ArraySet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code LongArray} manages a primitive long array as an {@code ArraySet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Copyable
 * @see ArraySet
 */
public interface LongArray extends Copyable<LongArray>, ArraySet<Long>
{
	/**
	 * Returns the primitive array of the {@code LongArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract long[] PArray();
	
	@Override
	public default Long[] Array()
	{
		Long[] data = new Long[Count()];
		for(int i = 0; i < Count(); i++)
		{
			data[i] = PArray()[i];
		}
		
		return data;
	}
}