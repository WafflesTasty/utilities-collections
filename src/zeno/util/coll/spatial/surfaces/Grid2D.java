package zeno.util.coll.spatial.surfaces;

import zeno.util.coll.Grid;
import zeno.util.geom.tools.bounds.Bounded2D;

/**
 * The {@code Grid2D} class divides a surface into equally sized rectangles.
 * 
 * 
 * @since Mar 31, 2017
 * @author Zeno
 * 
 * 
 * @param <T>  the type of tiles in the grid
 * 
 * @see Bounded2D
 * @see Space2D
 * @see Grid
 */
public abstract class Grid2D<T extends Grid2D.Tile> extends Grid<T> implements Space2D<T>
{
	/**
	 * The {@code Tile} interface defines a single tile in a {@link Grid2D}.
	 * 
	 * @since Mar 31, 2017
	 * @author Zeno
	 * 
	 * @see Bounded2D
	 * @see Grid2D
	 */
	public static interface Tile extends Grid.Tile, Bounded2D
	{
		/**
		 * Returns the row of the {@code Tile}.
		 * 
		 * @return  the tile's row
		 */
		public abstract int Row();
		
		/**
		 * Returns the column of the {@code Tile}.
		 * 
		 * @return  the tile's column
		 */
		public abstract int Column();
		
		
		@Override
		public default int[] Coordinates()
		{
			return new int[]
			{
				Column(), Row()			
			};
		}
	}

	
	/**
	 * Creates a new {@code Grid2D}.
	 * 
	 * @param col  a column count
	 * @param row  a row count
	 */
	public Grid2D(int col, int row)
	{
		super(col, row);
	}
	
	
	/**
	 * Returns the column count of the {@code Grid2D}.
	 * 
	 * @return  the grid's column count
	 */
	public int Columns()
	{
		return Dimensions()[0];
	}
	
	/**
	 * Returns the row count of the {@code Grid2D}.
	 * 
	 * @return  the grid's row count
	 */
	public int Rows()
	{
		return Dimensions()[1];
	}	
}