package waffles.utils.sets.countable;

import waffles.utils.sets.CountableSet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code ShortArray} manages a primitive short array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see CountableSet
 * @see Copyable
 */
public interface ShortArray extends CountableSet, Copyable<ShortArray>
{
	/**
	 * Returns the data of the {@code IntegerArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract short[] Data();
	
	
	@Override
	public default int Count()
	{
		return Data().length;
	}
}