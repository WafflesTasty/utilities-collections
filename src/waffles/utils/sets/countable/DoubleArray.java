package waffles.utils.sets.countable;

import waffles.utils.sets.CountableSet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code DoubleArray} manages a primitive double array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see CountableSet
 * @see Copyable
 */
public interface DoubleArray extends CountableSet, Copyable<DoubleArray>
{
	/**
	 * Returns the data of the {@code DoubleArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract double[] Data();
	
	
	@Override
	public default int Count()
	{
		return Data().length;
	}
}