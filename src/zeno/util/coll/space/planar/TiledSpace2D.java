package zeno.util.coll.space.planar;

import zeno.util.coll.space.TiledSpace;
import zeno.util.geom.collidables.IGeometrical2D;
import zeno.util.geom.collidables.bounds.Bounds2D;
import zeno.util.geom.collidables.geometry.planar.Square;
import zeno.util.geom.utilities.cardinal.Cardinal2D;

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
public class TiledSpace2D<T extends TiledSpace2D.Tile2D> extends TiledSpace<T> implements Space2D<T>
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
	public static interface Tile2D extends TiledSpace.Tile, IGeometrical2D
	{		
		/**
		 * Returns the row of the {@code Tile2D}.
		 * 
		 * @return  a row coordinate
		 */
		public abstract int Row();
		
		/**
		 * Returns the column of the {@code Tile2D}.
		 * 
		 * @return  a column coordinate
		 */
		public abstract int Column();
		
		
		/**
		 * Returns a neighbor of the {@code Tile2D}.
		 * 
		 * @param c  a cardinal direcion
		 * @return  a neighboring tile
		 * 
		 * 
		 * @see Cardinal2D
		 */
		public default Tile2D Neighbor(Cardinal2D c)
		{
			int row = (int) (Row() + c.Y());
			int col = (int) (Column() + c.X());
			
			return Parent().get(col, row);
		}

		
		@Override
		public abstract TiledSpace2D<?> Parent();
		
		@Override
		public default int[] Coordinates()
		{
			return new int[]{Column(), Row()};
		}
		
		@Override
		public default Square Shape()
		{
			return (Square) Tile.super.Shape();
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