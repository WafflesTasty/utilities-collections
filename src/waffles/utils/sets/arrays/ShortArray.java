package waffles.utils.sets.arrays;

import waffles.utils.sets.ArraySet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code ShortArray} manages a primitive short array as an {@code ArraySet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Copyable
 * @see ArraySet
 */
public interface ShortArray extends Copyable<ShortArray>, ArraySet<Short>
{
	/**
	 * Returns the primitive array of the {@code ShortArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract short[] PArray();
	
	@Override
	public default Short[] Array()
	{
		Short[] data = new Short[Count()];
		for(int i = 0; i < Count(); i++)
		{
			data[i] = PArray()[i];
		}
		
		return data;
	}
}