package zeno.util.coll.indices.arrays;

import zeno.util.tools.helper.Array;

/**
 * The {@code Index} interface defines a collection maintained in a discrete index.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <T>  an index type
 */
public interface Index<T>
{		
	/**
	 * The {@code Unique} interface defines an index with unique elements.
	 *
	 * @author Waffles
	 * @since 16 Nov 2021
	 * @version 1.0
	 *
	 *
	 * @param <T>  an object type
	 * @see Iterable
	 * @see Index
	 */
	public static interface Unique<T> extends Index<T>, Iterable<T>
	{
		/**
		 * Returns a coordinate in the {@code Index}.
		 * 
		 * @param val  a target value
		 * @return  a value coordinate
		 */
		public abstract int[] indexOf(T val);
	}
	
	
	/**
	 * Returns a value from the {@code Index}.
	 * 
	 * @param coords  index coordinates
	 * @return  a current index value
	 */
	public abstract T get(int... coords);
	
	/**
	 * Changes a value from the {@code Index}.
	 * 
	 * @param val  an index value
	 * @param coords  index coordinates
	 * @return  a previous index value
	 */
	public abstract T put(T val, int... coords);
	
	/**
	 * Checks a coordinate in the {@code Index}.
	 * 
	 * @param coords  a coordinate to check
	 * @return  {@code true} if the coordinate is allowed
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
			}
			
			// Otherwise, check the coordinate bounds.
			if(coords[i] < 0 || Dimensions()[i] <= coords[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Removes a value from the {@code Index}.
	 * 
	 * @param coords  index coordinates
	 * @return  a previous index value
	 */
	public default T remove(int... coords)
	{
		return put(null, coords);
	}
	
	
	
	/**
	 * Returns the dimension of the {@code Index}.
	 * 
	 * @return  an index dimension
	 */
	public default int[] Dimensions()
	{
		int[] dim = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			dim[i] = Maximum()[i] - Minimum()[i] + 1;
		}

		return dim;
	}
	
	/**
	 * Returns the minimum coordinate of the {@code Index}.
	 * 
	 * @return  a minimum coordinate
	 */
	public default int[] Minimum()
	{
		return new int[Order()];
	}
	
	/**
	 * Returns the maximum coordinate of the {@code Index}.
	 * 
	 * @return  a maximum coordinate
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
	
	/**
	 * Returns the tile count of the {@code Index}.
	 * 
	 * @return  an index tile count
	 */
	public default int Count()
	{
		return Array.product.of(Dimensions());
	}

	/**
	 * Returns the order of the {@code Index}.
	 * 
	 * @return  an index order
	 */
	public default int Order()
	{
		return Dimensions().length;
	}
}