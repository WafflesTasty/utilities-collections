package waffles.utils.sets.utilities.indexed.coords;

/**
 * A {@code Coordination} represents a chunk of an {@code IndexedSet}.
 * 
 * @author Waffles
 * @since 13 Feb 2026
 * @version 1.1
 *
 * 
 * @see Coordinator
 */
public interface Coordination extends Coordinator
{
	/**
	 * Validates a coordinate in the {@code Coordination}.
	 * This will return false when a coordinate is outside
	 * the bounds of its dimensions. Any coordinate that has
	 * too many values will return true if and only if
	 * the additional dimensions are all zero.
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
			
			int min = Minimum()[i];
			int max = Maximum()[i];

			// Otherwise, check the coordinate bounds.
			if(crds[i] < min || max < crds[i])
			{
				return false;
			}
		}
		
		return true;
	}

	
	/**
	 * Returns a {@code Coordination} index minimum.
	 * 
	 * @return  a minimum index
	 */
	public default int[] Minimum()
	{
		return new int[Order()];
	}
	
	/**
	 * Returns a {@code Coordination} index maximum.
	 * 
	 * @return  a maximum index
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
}