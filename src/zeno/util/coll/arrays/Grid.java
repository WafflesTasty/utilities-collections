package zeno.util.coll.arrays;

import java.util.Iterator;

import zeno.util.coll.utilities.iterators.GridIterator;
import zeno.util.tools.helper.Array;

/**
 * The {@code Grid} class defines a collection maintained in a grid layout.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <V>  a grid value type
 * @see Iterable
 */
public class Grid<V> implements Iterable<V>
{
	private Object[] source;
	private int[] dimension;
	
	/**
	 * Creates a new {@code Grid}.
	 * 
	 * @param dim  a grid dimension
	 */
	public Grid(int... dim)
	{
		dimension = dim;
		source = new Object[TileCount()];
	}
	
	/**
	 * Returns a value from the {@code Grid}.
	 * 
	 * @param coords  grid coordinates
	 * @return  a current grid value
	 */
	public V get(int... coords)
	{
		return (V) source[toIndex(coords)];
	}
	
	/**
	 * Changes a value from the {@code Grid}.
	 * 
	 * @param val  a grid value
	 * @param coords  grid coordinates
	 * @return  a previous grid value
	 */
	public V put(V val, int... coords)
	{
		int index = toIndex(coords);
		V prev = (V) source[index];
		source[index] = val;
		return prev;
	}
	
	/**
	 * Removes a value from the {@code Grid}.
	 * 
	 * @param coords  grid coordinates
	 * @return  a previous grid value
	 */
	public V remove(int... coords)
	{
		return put(null, coords);
	}
	
	
	/**
	 * Returns the array of the {@code Grid}.
	 * 
	 * @return  a grid array
	 */
	public Object[] Array()
	{
		return source;
	}
	
	/**
	 * Returns the dimension of the {@code Grid}.
	 * 
	 * @return  a grid dimension
	 */
	public int[] Dimensions()
	{
		return dimension;
	}
	
	/**
	 * Returns the tile count of the {@code Grid}.
	 * 
	 * @return  a grid tile count
	 */
	public int TileCount()
	{
		return Array.product.of(dimension);
	}

	/**
	 * Returns the order of the {@code Grid}.
	 * 
	 * @return  a grid order
	 */
	public int Order()
	{
		return dimension.length;
	}
			
	
	@Override
	public Iterator<V> iterator()
	{
		return new GridIterator<>(this);
	}
	
	int toIndex(int... coords)
	{
		int index = 0;
		for(int i = 0; i < Order(); i++)
		{
			index *= dimension[i];
			index += coords[i];
		}

		return index;
	}
}