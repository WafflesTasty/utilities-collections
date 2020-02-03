package zeno.util.coll.utilities.iterators;

import java.util.Iterator;

import zeno.util.coll.arrays.Grid;

/**
 * The {@code GridIterator} class iterates over all elements in a {@code Grid}.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <V>  a grid value type
 * @see Iterator
 */
public class GridIterator<V> implements Iterator<V>
{
	private int next;
	private Object[] source;
	
	/**
	 * Creates a new {@code GridIterator}.
	 * 
	 * @param grid  a target grid
	 * 
	 * 
	 * @see Grid
	 */
	public GridIterator(Grid<V> grid)
	{
		source = grid.Array();
	}
	
	@Override
	public boolean hasNext()
	{
		return next < source.length;
	}

	@Override
	public V next()
	{
		int curr = next++;
		return (V) source[curr];
	}
}