package waffles.utils.sets;

import waffles.utils.tools.patterns.semantics.Clearable;

/**
 * A {@code CountableSet} contains a countable number of objects of a specified type.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.1
 * 
 * 
 * @see Clearable
 * @see Set
 */
@FunctionalInterface
public interface CountableSet extends Clearable, Set
{	
	/**
	 * Returns the size of the {@code CountableSet}.
	 * 
	 * @return  an item count
	 */
	public abstract int Count();
	
	/**
	 * Checks if the {@code CountableSet} is completely empty.
	 * 
	 * @return  {@code true} if the set is empty
	 */
	public default boolean isEmpty()
	{
		return Count() == 0;
	}
	
	
	@Override
	public default void clear()
	{
		// NOT APPLICABLE
	}
}