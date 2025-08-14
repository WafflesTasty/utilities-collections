package waffles.utils.sets.indexed.array.like;

import waffles.utils.sets.indexed.array.ArrayLike;
import waffles.utils.tools.patterns.properties.values.Copyable;

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
public interface ByteArray extends ArrayLike<byte[], Byte>, Copyable<ByteArray>
{
	@Override
	public default Byte get(int... crds)
	{
		return Array()[toIndex(Ordering(), crds)];
	}
	
	@Override
	public default Byte put(Byte val, int... crds)
	{
		int index = toIndex(Ordering(), crds);
		Byte prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Byte remove(int... crds)
	{
		return put(null, crds);
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
}