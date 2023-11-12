package waffles.utils.sets.countable;

import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code ByteArray} manages a primitive byte array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see MutableIndex
 * @see Copyable
 */
public interface ByteArray extends Copyable<ByteArray>, MutableIndex<Byte>
{
	/**
	 * Returns the primitive data of the {@code ByteArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract byte[] Array();
	
	
	@Override
	public default Byte get(int... coords)
	{
		return Array()[toIndex(Order.COL_MAJOR, coords)];
	}
	
	@Override
	public default Byte put(Byte val, int... coords)
	{
		int index = toIndex(Order.COL_MAJOR, coords);
		byte prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Byte remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}