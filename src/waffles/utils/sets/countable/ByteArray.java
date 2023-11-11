package waffles.utils.sets.countable;

import waffles.utils.sets.CountableSet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code ByteArray} manages a primitive byte array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see CountableSet
 * @see Copyable
 */
public interface ByteArray extends CountableSet, Copyable<ByteArray>
{
	/**
	 * Returns the primitive data of the {@code ByteArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract byte[] Data();
	
	
	@Override
	public default int Count()
	{
		return Data().length;
	}
}