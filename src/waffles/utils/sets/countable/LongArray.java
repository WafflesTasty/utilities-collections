package waffles.utils.sets.countable;

import waffles.utils.sets.CountableSet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code LongArray} manages a primitive long array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see CountableSet
 * @see Copyable
 */
public interface LongArray extends CountableSet, Copyable<LongArray>
{
	/**
	 * Returns the data of the {@code IntegerArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract long[] Data();
	
	
	@Override
	public default int Count()
	{
		return Data().length;
	}
}