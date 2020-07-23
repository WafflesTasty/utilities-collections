package zeno.util.coll.geom.planar;

import zeno.util.coll.geom.TiledSpace;
import zeno.util.geom.collidables.IGeometrical2D;
import zeno.util.geom.collidables.bounds.Bounds2D;
import zeno.util.geom.collidables.geometry.planar.Square;

/**
 * The {@code TiledSpace2D} class defines a space partitioned into squares of equal size.
 *
 * @author Zeno
 * @since 23 Jul 2020
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see TiledSpace
 * @see Space2D
 */
public class TiledSpace2D<T extends TiledSpace2D<T>.Tile2D> extends TiledSpace<T> implements Space2D<T>
{
	/**
	 * The {@code Tile2D} class defines a single element of a {@code TiledSpace2D}.
	 *
	 * @author Zeno
	 * @since 23 Jul 2020
	 * @version 1.0
	 * 
	 * 
	 * @see IGeometrical2D
	 * @see TiledSpace
	 */
	public class Tile2D extends TiledSpace<T>.Tile implements IGeometrical2D
	{		
		/**
		 * Creates a new {@code Tile2D}.
		 * 
		 * @param col  a column coordinate
		 * @param row  a row coordinate
		 */
		public Tile2D(int col, int row)
		{
			super(col, row);
		}
		
		/**
		 * Returns the column of the {@code Tile2D}.
		 * 
		 * @return  a column coordinate
		 */
		public int Column()
		{
			return Coordinates()[0];
		}
		
		/**
		 * Returns the row of the {@code Tile2D}.
		 * 
		 * @return  a row coordinate
		 */
		public int Row()
		{
			return Coordinates()[1];
		}
		
		
		@Override
		public TiledSpace2D<?> Parent()
		{
			return (TiledSpace2D<?>) super.Parent();
		}
		
		@Override
		public Square Shape()
		{
			return (Square) super.Shape();
		}
	}

	
	/**
	 * Creates a new {@code TiledSpace2D}.
	 * 
	 * @param cols  a column count
	 * @param rows  a row count
	 */
	public TiledSpace2D(int cols, int rows)
	{
		super(cols, rows);
	}
	
	/**
	 * Returns the columns of the {@code TiledSpace2D}.
	 * 
	 * @return  a column count
	 */
	public int Columns()
	{
		return Dimensions()[0];
	}
	
	/**
	 * Returns the rows of the {@code TiledSpace2D}.
	 * 
	 * @return  a row count
	 */
	public int Rows()
	{
		return Dimensions()[1];
	}
	
	
	@Override
	public Bounds2D Bounds()
	{
		return (Bounds2D) super.Bounds();
	}
}