package zeno.util.coll.utilities.iterators;

import java.util.Iterator;

import zeno.util.coll.indices.ArrayIndex;

/**
 * The {@code IndexIterator} class iterates over all elements in a {@code Index}.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <V>  an index value type
 * @see Iterator
 */
public class IndexIterator<V> implements Iterator<V>
{
	private int next;
	private Object[] source;
	
	/**
	 * Creates a new {@code IndexIterator}.
	 * 
	 * @param grid  a target grid
	 * 
	 * 
	 * @see ArrayIndex
	 */
	public IndexIterator(ArrayIndex<V> grid)
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