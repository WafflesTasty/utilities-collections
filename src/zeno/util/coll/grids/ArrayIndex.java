package zeno.util.coll.grids;

import java.util.Iterator;

import zeno.util.coll.Index;
import zeno.util.coll.utilities.iterators.IndexIterator;

/**
 * The {@code ArrayIndex} class implements a {@code Index} with an internal array storage.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <V>  an index value type
 * @see Index
 */
public class ArrayIndex<V> implements Index<V>
{
	private Object[] source;
	private int[] dimension;
	
	/**
	 * Creates a new {@code ArrayIndex}.
	 * 
	 * @param dim  a grid dimension
	 */
	public ArrayIndex(int... dim)
	{
		dimension = dim;
		source = new Object[Count()];
	}
		
	/**
	 * Returns the source of the {@code ArrayIndex}.
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
		return new IndexIterator<>(this);
	}

	
	private int toIndex(int... coords)
	{
		int index = 0;
		for(int i = 0; i < Order(); i++)
		{
			index *= dimension[i];
			if(i < coords.length)
			{
				index += coords[i];
			}
		}

		return index;
	}

	@Override
	public boolean contains(int... coords)
	{
		// If coordinates exceed the grid order...
		for(int i = Order(); i < coords.length; i++)
		{
			// The remainder have to be zero.
			if(coords[i] != 0)
			{
				return false;
			}
		}
		
		int index = toIndex(coords);
		return 0 <= index && index < dimension.length;
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
}