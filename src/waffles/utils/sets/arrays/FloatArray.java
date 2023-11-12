package waffles.utils.sets.arrays;

import waffles.utils.sets.ArraySet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code FloatArray} manages a primitive float array as an {@code ArraySet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Copyable
 * @see ArraySet
 */
public interface FloatArray extends Copyable<FloatArray>, ArraySet<Float>
{
	/**
	 * Returns the primitive array of the {@code FloatArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract float[] PArray();
	
	@Override
	public default Float[] Array()
	{
		Float[] data = new Float[Count()];
		for(int i = 0; i < Count(); i++)
		{
			data[i] = PArray()[i];
		}
		
		return data;
	}
}