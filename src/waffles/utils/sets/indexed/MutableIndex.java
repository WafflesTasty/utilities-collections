package waffles.utils.sets.indexed;

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
 * @param <O>  an object type
 * @see IndexedSet
 */
public interface MutableIndex<O> extends IndexedSet<O>
{
	/**
	 * An {@code Order} defines two ways of converting coordinates
	 * to a unique integer value, generalizing the concept of
	 * row-major and col-major ordering for matrices.
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

		
		private static int[] toColCoord(int idx, int[] dims)
		{
			int mod = idx;
			int[] coord = new int[dims.length];
			for(int i = 0; i < dims.length; i++)
			{
				coord[i] = mod % dims[i];
				mod = (mod - coord[i]) / dims[i];
			}
			
			return coord;
		}

		private static int[] toRowCoord(int idx, int[] dims)
		{
			int mod = idx;
			int[] coord = new int[dims.length];
			for(int i = dims.length - 1; i >= 0; i--)
			{
				coord[i] = mod % dims[i];
				mod = (mod - coord[i]) / dims[i];
			}
			
			return coord;
		}
		
		private static int toColIndex(int[] crds, int[] dims)
		{
			int idx = 0;
			for(int i = dims.length - 1; i >= 0; i--)
			{
				idx *= dims[i];
				idx += crds[i];
			}

			return idx;
		}
		
		private static int toRowIndex(int[] crds, int[] dims)
		{
			int idx = 0;
			for(int i = 0; i < dims.length; i++)
			{
				idx *= dims[i];
				idx += crds[i];
			}

			return idx;
		}
	}
	
	/**
	 * A {@code Wrapper} defines a wrapper around another {@code MutableIndex}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see MutableIndex
	 * @see IndexedSet
	 */
	public static interface Wrapper<O> extends IndexedSet.Wrapper<O>, MutableIndex<O>
	{		
		@Override
		public abstract MutableIndex<O> Delegate();
						
		@Override
		public default O put(O val, int... crds)
		{
			return Delegate().put(val, crds);
		}
		
		@Override
		public default O remove(int... crds)
		{
			return Delegate().remove(crds);
		}
	}
	
	
	/**
	 * Removes a value from the {@code MutableIndex}.
	 * 
	 * @param crds  an index coordinate
	 * @return  a previous index value
	 */
	public abstract O remove(int... crds);
		
	/**
	 * Changes a value in the {@code MutableIndex}.
	 * 
	 * @param val   an index value
	 * @param crds  an index coordinate
	 * @return  a previous index value
	 */
	public abstract O put(O val, int... crds);
	
	
	/**
	 * Converts a unique index value into a set of coordinates.
	 * 
	 * @param ord  an index order
	 * @param idx  an index value
	 * @return  an index coordinate
	 */
	public default int[] toCoord(Order ord, int idx)
	{
		switch(ord)
		{
		case COL_MAJOR:
			return Order.toColCoord(idx, Dimensions());
		case ROW_MAJOR:
			return Order.toRowCoord(idx, Dimensions());
		default:
			return null;
		}
	}
	
	/**
	 * Converts a set of coordinates into a unique index value.
	 * 
	 * @param ord   an index order
	 * @param crds  an index coordinate
	 * @return  an index value
	 */
	public default int toIndex(Order ord, int... crds)
	{
		switch(ord)
		{
		case COL_MAJOR:
			return Order.toColIndex(crds, Dimensions());
		case ROW_MAJOR:
			return Order.toRowIndex(crds, Dimensions());
		default:
			return -1;
		}
	}
}