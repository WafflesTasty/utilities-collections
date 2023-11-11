package waffles.utils.sets.countable;

import waffles.utils.sets.CountableSet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code BooleanArray} manages a primitive boolean array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see CountableSet
 * @see Copyable
 */
public interface BooleanArray extends CountableSet, Copyable<BooleanArray>
{
	/**
	 * Returns the primitive data of the {@code BooleanArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract boolean[] Array();
	
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}