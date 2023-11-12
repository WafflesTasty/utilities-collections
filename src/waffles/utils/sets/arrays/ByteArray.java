package waffles.utils.sets.arrays;

import waffles.utils.sets.ArraySet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code ByteArray} manages a primitive byte array as an {@code ArraySet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Copyable
 * @see ArraySet
 */
public interface ByteArray extends Copyable<ByteArray>, ArraySet<Byte>
{
	/**
	 * Returns the primitive array of the {@code ByteArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract byte[] PArray();
	
	@Override
	public default Byte[] Array()
	{
		Byte[] data = new Byte[Count()];
		for(int i = 0; i < Count(); i++)
		{
			data[i] = PArray()[i];
		}
		
		return data;
	}
}