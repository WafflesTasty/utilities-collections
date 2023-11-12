package waffles.utils.sets.arrays;

import waffles.utils.sets.ArrayLike;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code ByteArray} manages a primitive byte array as a {@code MutableIndex}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see Copyable
 * @see MutableIndex
 * @see ArrayLike
 */
public interface ByteArray extends ArrayLike<byte[]>, Copyable<ByteArray>, MutableIndex<Byte>
{
	/**
	 * Returns the ordering of the {@code ByteArray}.
	 * 
	 * @return  an index order
	 */
	public abstract Order Ordering();
	
	
	@Override
	public default Byte get(int... coords)
	{
		return Array()[toIndex(Ordering(), coords)];
	}
	
	@Override
	public default Byte put(Byte val, int... coords)
	{
		int index = toIndex(Ordering(), coords);
		Byte prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Byte remove(int... coords)
	{
		return put(null, coords);
	}
}