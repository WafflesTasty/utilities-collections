package waffles.utils.sets.arrays;

import waffles.utils.sets.ArraySet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code BooleanArray} manages a primitive boolean array as an {@code ArraySet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Copyable
 * @see ArraySet
 */
public interface BooleanArray extends Copyable<BooleanArray>, ArraySet<Boolean>
{
	/**
	 * Returns the primitive array of the {@code BooleanArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract boolean[] PArray();

	@Override
	public default Boolean[] Array()
	{
		Boolean[] data = new Boolean[Count()];
		for(int i = 0; i < Count(); i++)
		{
			data[i] = PArray()[i];
		}
		
		return data;
	}
}