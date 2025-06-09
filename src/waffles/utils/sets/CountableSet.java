package waffles.utils.sets;

import waffles.utils.tools.patterns.semantics.Clearable;
import waffles.utils.tools.patterns.semantics.Countable;

/**
 * A {@code CountableSet} contains a countable number of objects of a specified type.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.1
 * 
 * 
 * @see Clearable
 * @see Countable
 * @see Set
 */
@FunctionalInterface
public interface CountableSet extends Clearable, Countable, Set
{		
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