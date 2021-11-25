package zeno.util.coll.space.tiles.array;

import zeno.util.coll.space.Space2D;
import zeno.util.geom.collidables.IGeometrical2D;
import zeno.util.geom.collidables.bounds.Bounds2D;
import zeno.util.geom.collidables.geometry.planar.Square;
import zeno.util.geom.utilities.mobility.Cardinal2D;

/**
 * The {@code TiledSpace2D} class defines a space partitioned into squares of equal size.
 *
 * @author Waffles
 * @since 23 Jul 2020
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see TASpace
 * @see Space2D
 */
public class TASpace2D<T extends TASpace2D.Tile2D> extends TASpace<T> implements Space2D<T>
{
	/**
	 * The {@code Tile2D} class defines a single element of a {@code TASpace2D}.
	 *
	 * @author Zeno
	 * @since 23 Jul 2020
	 * @version 1.0
	 * 
	 * 
	 * @see IGeometrical2D
	 * @see TASpace
	 */
	public static interface Tile2D extends TASpace.Tile, IGeometrical2D
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
			
			if(Parent().contains(col, row))
			{
				return Parent().get(col, row);
			}
			
			return null;
		}

		
		@Override
		public abstract TASpace2D<?> Parent();
		
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
	 * Creates a new {@code TASpace2D}.
	 * 
	 * @param cols  a column count
	 * @param rows  a row count
	 */
	public TASpace2D(int cols, int rows)
	{
		super(cols, rows);
	}
	
	
	/**
	 * Returns the width of the {@code TASpace2D}.
	 * 
	 * @return  a space width
	 */
	public float Width()
	{
		return Size().get(0);
	}
	
	/**
	 * Returns the height of the {@code TASpace2D}.
	 * 
	 * @return  a space width
	 */
	public float Height()
	{
		return Size().get(1);
	}
	
	/**
	 * Returns the columns of the {@code TASpace2D}.
	 * 
	 * @return  a column count
	 */
	public int Columns()
	{
		return Dimensions()[0];
	}
	
	/**
	 * Returns the rows of the {@code TASpace2D}.
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