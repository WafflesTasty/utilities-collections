package zeno.util.coll.indices.arrays;

import java.util.Iterator;

import zeno.util.tools.helper.iterators.ArrayIterator;

/**
 * The {@code ArrayIndex} class implements a {@code Index} with an internal array storage.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <V>  an index type
 * @see Index
 */
public class ArrayIndex<V> implements Index.Unique<V>
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
		return new ArrayIterator<>(source);
	}

	@Override
	public int[] indexOf(V val)
	{
		for(int i = 0; i < source.length; i++)
		{
			if(source == val)
			{
				return toCoord(i);
			}
		}

		return null;
	}
	
	
	private int[] toCoord(int index)
	{
		int[] coord = new int[Order()];
		
		int mod = index;
		for(int i = Order() - 1; i >= 0; i--)
		{
			coord[i] = mod % dimension[i];
			mod = (mod - coord[i]) / dimension[i];
		}
		
		return coord;
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