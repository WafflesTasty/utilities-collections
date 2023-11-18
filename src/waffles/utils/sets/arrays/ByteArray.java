package waffles.utils.sets.arrays;

import waffles.utils.sets.ArrayLike;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code ByteArray} manages a primitive byte array as an {@code ArrayLike}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayLike
 * @see Copyable
 */
@FunctionalInterface
public interface ByteArray extends ArrayLike<byte[], Byte>, Copyable<ByteArray>
{
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
	
	@Override
	public default ByteArray instance()
	{
		return () -> null;
	}
	
	@Override
	public default ByteArray copy()
	{
		return () -> Array();
	}
	
	@Override
	public default int DataSize()
	{
		return Array().length;
	}
}