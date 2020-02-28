package zeno.util.coll;

import zeno.util.tools.helper.Array;

/**
 * The {@code Index} interface defines a collection maintained in a discrete index.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <T>  an index type
 * @see Iterable
 */
public interface Index<T> extends Iterable<T>
{		
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
	public abstract boolean contains(int... coords);
	
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
	public abstract int[] Dimensions();
	
	/**
	 * Returns the minimum coordinate of the {@code Index}.
	 * 
	 * @return  a minimum coordinate
	 */
	public default int[] Minimum()
	{
		int[] min = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			min[i] = 0;
		}
		
		return min;
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