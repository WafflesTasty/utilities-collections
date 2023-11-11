package waffles.utils.sets.countable;

import waffles.utils.sets.CountableSet;
import waffles.utils.tools.patterns.semantics.Copyable;

/**
 * A {@code FloatArray} manages a primitive float array as a {@code CountableSet}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see CountableSet
 * @see Copyable
 */
public interface FloatArray extends CountableSet, Copyable<FloatArray>
{
	/**
	 * Returns the data of the {@code FloatArray}.
	 * 
	 * @return  a primitive array
	 */
	public abstract float[] Data();
	
	
	@Override
	public default int Count()
	{
		return Data().length;
	}
}