package waffles.util.sets.indexed.mutable;

import waffles.util.sets.indexed.MutableIndex;
import waffles.util.sets.keymaps.binary.BSMap;

/**
 * A {@code BSIndex} implements an {@code Index} by internally storing data in a {@code BSMap}.
 * This type of index is particularly useful when the index contains a lot of null values.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an index object type
 * @see MutableIndex
 */
public class BSIndex<O> implements MutableIndex<O>
{
	private Order order;
	private int[] dimension;
	private BSMap<Integer, O> data;

	/**
	 * Creates a new {@code BSIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public BSIndex(int... dim)
	{
		this(Order.COL_MAJOR, dim);
	}
	
	/**
	 * Creates a new {@code BSIndex}.
	 * 
	 * @param ord  an index order
	 * @param dim  an index dimension
	 */
	public BSIndex(Order ord, int... dim)
	{
		order = ord;
		dimension = dim;
		data = new BSMap<>();
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
	public BSMap<Integer, O> Map()
	{
		return data;
	}
	
	
	@Override
	public void clear()
	{
		data = new BSMap<>();
	}
	
	@Override
	public O get(int... coords)
	{
		return data.get(toIndex(order, coords));
	}
	
	@Override
	public O put(O val, int... coords)
	{
		return data.put(toIndex(order, coords), val);
	}
	
	@Override
	public O remove(int... coords)
	{
		return data.remove(toIndex(order, coords));
	}

	@Override
	public int[] Dimensions()
	{
		return dimension;
	}
}