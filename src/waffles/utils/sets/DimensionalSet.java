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
 * @param <O>  an object type
 * 
 * 
 * @see Coordinator
 * @see Set
 */
public interface DimensionalSet<O> extends Coordinator, Set
{	
	/**
	 * A {@code Wrapper} defines a wrapper around another {@code Set}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see DimensionalSet
	 * @see Set
	 */
	public static interface Wrapper<O> extends Set.Wrapper, DimensionalSet<O>
	{
		@Override
		public default O get(int... crds)
		{
			return Delegate().get(crds);
		}
		
		@Override
		public abstract DimensionalSet<O> Delegate();
				
		@Override
		public default int[] Dimensions()
		{
			return Delegate().Dimensions();
		}
	}
	
	
	/**
	 * Returns a value from the {@code DimensionalSet}.
	 * 
	 * @param crds  an index coordinate
	 * @return      an index value
	 */
	public abstract O get(int... crds);

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