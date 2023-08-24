package waffles.util.sets.indexed;

import waffles.utils.tools.patterns.semantics.Clearable;

/**
 * A {@code MutableIndex} defines an index with directly mutable values.
 * The index allows coordinates to be converted to unique integers
 * according to a given index data order.
 *
 * @author Waffles
 * @since 16 Nov 2021
 * @version 1.1
 *
 *
 * @param <O>  an index object type
 * @see IndexedSet
 * @see Clearable
 */
public interface MutableIndex<O> extends IndexedSet<O>, Clearable
{
	/**
	 * The {@code Order} enum defines two ways of converting coordinates
	 * to a unique integer value, generalizing the concept of row-major
	 * and col-major ordering for matrices.
	 *
	 * @author Waffles
	 * @since 24 Aug 2023
	 * @version 1.0
	 */
	public static enum Order
	{
		/**
		 * A column-major order.
		 */
		COL_MAJOR,
		/**
		 * A row-major order.
		 */
		ROW_MAJOR;

		
		private static int[] toColCoord(int index, int[] dims)
		{
			int mod = index;
			int[] coord = new int[dims.length];
			for(int i = 0; i < dims.length; i++)
			{
				coord[i] = mod % dims[i];
				mod = (mod - coord[i]) / dims[i];
			}
			
			return coord;
		}

		private static int[] toRowCoord(int index, int[] dims)
		{
			int mod = index;
			int[] coord = new int[dims.length];
			for(int i = dims.length - 1; i >= 0; i--)
			{
				coord[i] = mod % dims[i];
				mod = (mod - coord[i]) / dims[i];
			}
			
			return coord;
		}
		
		private static int toColIndex(int[] coords, int[] dims)
		{
			int index = 0;
			for(int i = dims.length - 1; i >= 0; i--)
			{
				index *= dims[i];
				index += coords[i];
			}

			return index;
		}
		
		private static int toRowIndex(int[] coords, int[] dims)
		{
			int index = 0;
			for(int i = 0; i < dims.length; i++)
			{
				index *= dims[i];
				index += coords[i];
			}

			return index;
		}
	}
	
	
	/**
	 * Removes a value from the {@code MutableIndex}.
	 * 
	 * @param coords  index coordinates
	 * @return  a previous index value
	 */
	public abstract O remove(int... coords);
		
	/**
	 * Changes a value in the {@code MutableIndex}.
	 * 
	 * @param val  an index value
	 * @param coords  index coordinates
	 * @return  a previous index value
	 */
	public abstract O put(O val, int... coords);
	
	
	/**
	 * Converts a unique index value into a set of coordinates.
	 * 
	 * @param order  an index order
	 * @param index  an index value
	 * @return  an index coordinate
	 */
	public default int[] toCoord(Order order, int index)
	{
		switch(order)
		{
		case COL_MAJOR:
			return Order.toColCoord(index, Dimensions());
		case ROW_MAJOR:
			return Order.toRowCoord(index, Dimensions());
		default:
			return null;
		}
	}
	
	/**
	 * Converts a set of coordinates into a unique index value.
	 * 
	 * @param order  an index order
	 * @param coords  an index coordinate
	 * @return  an index value
	 */
	public default int toIndex(Order order, int... coords)
	{
		switch(order)
		{
		case COL_MAJOR:
			return Order.toColIndex(coords, Dimensions());
		case ROW_MAJOR:
			return Order.toRowIndex(coords, Dimensions());
		default:
			return -1;
		}
	}
}