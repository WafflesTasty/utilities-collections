package waffles.utils.sets.indexed;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.DimensionalSet;
import waffles.utils.sets.utilities.indexed.coords.Coordination;
import waffles.utils.tools.primitives.Array;

/**
 * An {@code IndexedSet} stores objects in an implicit multi-dimensional array.
 * This allows objects to be retrieved based on an integer index.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see DimensionalSet
 * @see Coordination
 * @see CountableSet
 */
public interface IndexedSet<O> extends CountableSet, Coordination, DimensionalSet<O>
{	
	/**
	 * A {@code Wrapper} defines a wrapper around another {@code IndexedSet}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see CountableSet
	 * @see IndexedSet
	 */
	public static interface Wrapper<O> extends CountableSet.Wrapper, IndexedSet<O>
	{		
		@Override
		public abstract IndexedSet<O> Delegate();
				
				
		@Override
		public default int[] Minimum()
		{
			return Delegate().Minimum();
		}
		
		@Override
		public default int[] Maximum()
		{
			return Delegate().Maximum();
		}
		
		@Override
		public default int[] Dimensions()
		{
			return Delegate().Dimensions();
		}
		
		@Override
		public default int Count()
		{
			return IndexedSet.super.Count();
		}
	}
	
	@Override
	public default int Count()
	{
		return Array.product.of(Dimensions());
	}
}