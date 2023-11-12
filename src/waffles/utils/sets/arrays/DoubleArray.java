package waffles.utils.sets.arrays;

import waffles.utils.sets.ArraySet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code DoubleArray} manages a primitive double array as an {@code ArraySet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Copyable
 * @see ArraySet
 */
public interface DoubleArray extends Copyable<DoubleArray>, ArraySet<Double>
{
	/**
	 * Returns the primitive array of the {@code DoubleArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract double[] PArray();
	
	@Override
	public default Double[] Array()
	{
		Double[] data = new Double[Count()];
		for(int i = 0; i < Count(); i++)
		{
			data[i] = PArray()[i];
		}
		
		return data;
	}
}