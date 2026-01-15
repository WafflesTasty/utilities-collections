	package waffles.utils.sets.indexed;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.DimensionalSet;
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
 * @see CountableSet
 */
public interface IndexedSet<O> extends CountableSet, DimensionalSet<O>
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
	
	
	/**
	 * Verifies if the {@code IndexedSet} defines a coordinate.
	 * This will return false when a coordinate is negative or outside
	 * the bounds of its given dimensions. Any index array that is too
	 * large for the set will return true if and only if the bonus
	 * dimensions are all equal to zero.
	 * 
	 * @param crds  an index coordinate
	 * @return  {@code true} if coordinate is valid
	 */
	public default boolean defines(int... crds)
	{
		for(int i = 0; i < crds.length; i++)
		{
			// If coordinates exceed the index order...
			if(Order() <= i)
			{
				// The remainder have to be zero.
				if(crds[i] != 0)
				{
					return false;
				}
				
				continue;
			}

			// Otherwise, check the coordinate bounds.
			if(crds[i] < Minimum()[i] || Maximum()[i] < crds[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * Returns the minimum coordinate of the {@code IndexedSet}.
	 * 
	 * @return  a minimum index coordinate
	 */
	public default int[] Minimum()
	{
		return new int[Order()];
	}
	
	/**
	 * Returns the maximum coordinate of the {@code IndexedSet}.
	 * 
	 * @return  a maximum index coordinate
	 */
	public default int[] Maximum()
	{
		int[] max = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			max[i] = Dimensions()[i] - 1;
		}
		
		return max;
	}


	@Override
	public default int[] Dimensions()
	{
		int[] min = Minimum();
		int[] max = Maximum();
		
		int[] dim = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			dim[i] = max[i] - min[i] + 1;
		}

		return dim;
	}
	
	@Override
	public default int Count()
	{
		return Array.product.of(Dimensions());
	}
}