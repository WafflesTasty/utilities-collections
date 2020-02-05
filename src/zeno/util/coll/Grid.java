package zeno.util.coll;

import zeno.util.tools.helper.Array;

/**
 * The {@code Grid} interface defines a collection maintained in a discrete grid layout.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <V>  a grid value type
 * @see Iterable
 */
public interface Grid<V> extends Iterable<V>
{
	/**
	 * Returns a value from the {@code Grid}.
	 * 
	 * @param coords  grid coordinates
	 * @return  a current grid value
	 */
	public abstract V get(int... coords);
	
	/**
	 * Changes a value from the {@code Grid}.
	 * 
	 * @param val  a grid value
	 * @param coords  grid coordinates
	 * @return  a previous grid value
	 */
	public abstract V put(V val, int... coords);
	
	/**
	 * Removes a value from the {@code Grid}.
	 * 
	 * @param coords  grid coordinates
	 * @return  a previous grid value
	 */
	public default V remove(int... coords)
	{
		return put(null, coords);
	}
	
	
	/**
	 * Returns the dimension of the {@code Grid}.
	 * 
	 * @return  a grid dimension
	 */
	public abstract int[] Dimensions();
	
	/**
	 * Returns the tile count of the {@code Grid}.
	 * 
	 * @return  a grid tile count
	 */
	public default int Count()
	{
		return Array.product.of(Dimensions());
	}

	/**
	 * Returns the order of the {@code Grid}.
	 * 
	 * @return  a grid order
	 */
	public default int Order()
	{
		return Dimensions().length;
	}
}