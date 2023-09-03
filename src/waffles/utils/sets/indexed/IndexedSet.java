package waffles.utils.sets.indexed;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.DimensionalSet;
import waffles.utils.tools.primitives.Array;

/**
 * An {@code IndexedSet} stores objects in an implicit multi-dimensional array.
 * </br> This allows objects to be retrieved based on an integer index.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an index object type
 * @see DimensionalSet
 * @see CountableSet
 */
public interface IndexedSet<O> extends CountableSet<O>, DimensionalSet<O>
{	
	/**
	 * Verifies if the {@code IndexedSet} contains a coordinate.
	 * This will return false when a coordinate is negative or outside
	 * the bounds of its given dimensions. Any index array that is too
	 * large for the set will return true if and only if the bonus
	 * dimension are all equal to zero.
	 * 
	 * @param coords  a coordinate to verify
	 * @return  {@code true} if the coordinate is within bounds of the index
	 */
	public default boolean contains(int... coords)
	{
		for(int i = 0; i < coords.length; i++)
		{
			// If coordinates exceed the index order...
			if(Order() <= i)
			{
				// The remainder have to be zero.
				if(coords[i] != 0)
				{
					return false;
				}
				
				continue;
			}

			// Otherwise, check the coordinate bounds.
			if(coords[i] < Minimum()[i] || Maximum()[i] < coords[i])
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

	
	private int Dimension(int i)
	{
		return Maximum()[i] - Minimum()[i] + 1;
	}
	
	@Override
	public default int[] Dimensions()
	{
		int[] dim = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			dim[i] = Dimension(i);
		}

		return dim;
	}
	
	@Override
	public default int Count()
	{
		return Array.product.of(Dimensions());
	}
}