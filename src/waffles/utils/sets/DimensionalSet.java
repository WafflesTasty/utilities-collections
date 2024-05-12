package waffles.utils.sets;

import waffles.utils.tools.patterns.semantics.Dimensional;
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
 * @see Dimensional
 * @see Set
 */
public interface DimensionalSet<O> extends Dimensional, Set
{	
	/**
	 * Returns a single value from the {@code DimensionalSet}.
	 * 
	 * @param coords  an index coordinate
	 * @return        an index value
	 */
	public abstract O get(int... coords);

	/**
	 * Returns the order of the {@code DimensionalSet}.
	 * The order represents how many indices are needed
	 * to define a single value in the set.
	 * 
	 * @return  a dimension order
	 */
	@Override
	public default int Order()
	{
		return Dimensions().length;
	}
		
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