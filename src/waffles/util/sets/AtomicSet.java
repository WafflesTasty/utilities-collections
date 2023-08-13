package waffles.util.sets;

import waffles.utils.tools.collections.Iterables;

/**
 * An {@code AtomicSet} is a countable set which can iterate over its contents.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  a set object type
 * @see CountableSet
 * @see Iterable
 */
@FunctionalInterface
public interface AtomicSet<O> extends CountableSet<O>, Iterable<O>
{
	/**
	 * Checks if the {@code AtomicSet} contains an object.
	 * 
	 * @param obj  a potential set object
	 * @return  {@code true} if the set contains obj
	 */
	public default boolean contains(O obj)
	{
		for(O o : this)
		{
			if(obj.equals(o))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public default int Count()
	{
		return Iterables.count(this);
	}
}