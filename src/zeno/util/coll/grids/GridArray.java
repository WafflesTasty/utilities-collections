package zeno.util.coll.grids;

import java.util.Iterator;

import zeno.util.coll.Grid;
import zeno.util.coll.utilities.iterators.GridIterator;

/**
 * The {@code GridArray} class implements a {@code Grid} with an internal array storage.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <V>  a grid value type
 * @see Grid
 */
public class GridArray<V> implements Grid<V>
{
	private Object[] source;
	private int[] dimension;
	
	/**
	 * Creates a new {@code GridArray}.
	 * 
	 * @param dim  a grid dimension
	 */
	public GridArray(int... dim)
	{
		source = new Object[Count()];
		dimension = dim;
	}
	
	/**
	 * Returns the source {@code Array}.
	 * 
	 * @return  a grid array
	 */
	public Object[] Array()
	{
		return source;
	}
	
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}

	@Override
	public Iterator<V> iterator()
	{
		return new GridIterator<>(this);
	}
		
	
	@Override
	public V put(V val, int... coords)
	{
		int index = toIndex(coords);
		V prev = (V) source[index];
		source[index] = val;
		return prev;
	}
		
	@Override
	public V get(int... coords)
	{
		return (V) source[toIndex(coords)];
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