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