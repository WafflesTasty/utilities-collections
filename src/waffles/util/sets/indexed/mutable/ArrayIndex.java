package waffles.util.sets.indexed.mutable;

import waffles.util.sets.indexed.MutableIndex;

/**
 * An {@code ArrayIndex} implements an {@code Index} by internally storing data in an array.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an index object type
 * @see MutableIndex
 */
public class ArrayIndex<O> implements MutableIndex<O>
{
	private Object[] data;
	private int[] dimension;
	
	/**
	 * Creates a new {@code ArrayIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public ArrayIndex(int... dim)
	{
		dimension = dim;
		data = new Object[Count()];
	}
			
	/**
	 * Returns the array that stores the data
	 * of the {@code ArrayIndex}.
	 * 
	 * @return  a grid array
	 */
	public Object[] Array()
	{
		return data;
	}
	
	
	@Override
	public O get(int... coords)
	{
		return (O) data[toIndex(coords)];
	}
	
	@Override
	public O put(O val, int... coords)
	{
		int index = toIndex(coords);
		O prev = (O) data[index];
		data[index] = val;
		return prev;
	}
	
	@Override
	public O remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public void clear()
	{
		data = new Object[Count()];
	}
	
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}

	@Override
	public int[] indexOf(O val)
	{
		for(int i = 0; i < data.length; i++)
		{
			if(data[i] == val)
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
}