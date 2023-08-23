package waffles.util.sets.indexed.mutable;

import waffles.util.sets.indexed.MutableIndex;
import waffles.util.sets.keymaps.binary.BSMap;

/**
 * A {@code BSIndex} implements an {@code Index} by internally storing data in a {@code BSMap}.
 * This type of index is particularly useful when the index contains a lot of null values.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an index object type
 * @see MutableIndex
 */
public class BSIndex<O> implements MutableIndex<O>
{
	private int[] dimension;
	private BSMap<Integer, O> data;

	/**
	 * Creates a new {@code BSIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public BSIndex(int... dim)
	{
		data = new BSMap<>();
		dimension = dim;
	}
			
	/**
	 * Returns the map that stores the data
	 * of the {@code BSIndex}.
	 * 
	 * @return  a search map
	 * 
	 * 
	 * @see BSMap
	 */
	public BSMap<Integer, O> BSMap()
	{
		return data;
	}
	
	
	@Override
	public O get(int... coords)
	{
		return data.get(toIndex(coords));
	}
	
	@Override
	public O put(O val, int... coords)
	{
		return data.put(toIndex(coords), val);
	}
	
	@Override
	public O remove(int... coords)
	{
		return data.remove(toIndex(coords));
	}
	
	@Override
	public void clear()
	{
		data = new BSMap<>();
	}
	
	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}

	@Override
	public int[] indexOf(O val)
	{
		Integer index = data.search(val);
		if(index != null)
		{
			return toCoord(index);
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