package waffles.utils.sets;

import waffles.utils.sets.utilities.coordinates.Coordinator;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code DimensionalSet} defines an array of integer dimensions,
 * which generally represent a multi-dimensional array of values.
 *
 * @author Waffles
 * @since Jul 5, 2018
 * @version 1.1
 * 
 * @param <O>  a set object type
 * 
 * 
 * @see Coordinator
 * @see Set
 */
public interface DimensionalSet<O> extends Coordinator, Set
{	
	/**
	 * Returns a single value from the {@code DimensionalSet}.
	 * 
	 * @param coords  an index coordinate
	 * @return        an index value
	 */
	public abstract O get(int... coords);

	/**
	 * Returns the size of the {@code DimensionalSet}.
	 * The size represents how many values can be
	 * stored within this set's dimensions.
	 * 
	 * @return  a set size
	 */
	public default int Size()
	{
		return Array.product.of(Dimensions());
	}
}