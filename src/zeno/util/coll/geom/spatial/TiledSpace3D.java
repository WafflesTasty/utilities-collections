package zeno.util.coll.geom.spatial;

import zeno.util.coll.geom.TiledSpace;
import zeno.util.geom.collidables.IGeometrical3D;
import zeno.util.geom.collidables.bounds.Bounds3D;
import zeno.util.geom.collidables.geometry.spatial.Cube;
import zeno.util.geom.utilities.cardinal.Cardinal3D;

/**
 * The {@code TiledSpace3D} class defines a space partitioned into cubes of equal size.
 *
 * @author Zeno
 * @since 23 Jul 2020
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see TiledSpace
 * @see Space3D
 */
public class TiledSpace3D<T extends TiledSpace3D.Tile3D> extends TiledSpace<T> implements Space3D<T>
{
	/**
	 * The {@code Tile3D} class defines a single element of a {@code TiledSpace3D}.
	 *
	 * @author Zeno
	 * @since 23 Jul 2020
	 * @version 1.0
	 * 
	 * 
	 * @see IGeometrical3D
	 * @see TiledSpace
	 */
	public static interface Tile3D extends TiledSpace.Tile, IGeometrical3D
	{		
		/**
		 * Returns the row of the {@code Tile3D}.
		 * 
		 * @return  a row coordinate
		 */
		public abstract int Row();
		
		/**
		 * Returns the aisle of the {@code Tile3D}.
		 * 
		 * @return  an aisle coordinate
		 */
		public abstract int Aisle();
		
		/**
		 * Returns the column of the {@code Tile3D}.
		 * 
		 * @return  a column coordinate
		 */
		public abstract int Column();
		
	
		/**
		 * Returns a neighbor of the {@code Tile3D}.
		 * 
		 * @param c  a cardinal direcion
		 * @return  a neighboring tile
		 * 
		 * 
		 * @see Cardinal3D
		 */
		public default Tile3D Neighbor(Cardinal3D c)
		{
			int row = (int) (Row() + c.Y());
			int col = (int) (Column() + c.X());
			int ais = (int) (Aisle() + c.Z());
			
			return Parent().get(col, row, ais);
		}

		
		@Override
		public abstract TiledSpace3D<?> Parent();
		
		@Override
		public default int[] Coordinates()
		{
			return new int[]{Row(), Column(), Aisle()};
		}
		
		@Override
		public default Cube Shape()
		{
			return (Cube) Tile.super.Shape();
		}
	}

	
	/**
	 * Creates a new {@code TiledSpace3D}.
	 * 
	 * @param cols  a column count
	 * @param rows  a row count
	 * @param aisl  an aisle count
	 */
	public TiledSpace3D(int cols, int rows, int aisl)
	{
		super(cols, rows, aisl);
	}
	
	
	/**
	 * Returns the columns of the {@code TiledSpace3D}.
	 * 
	 * @return  a column count
	 */
	public int Columns()
	{
		return Dimensions()[0];
	}

	/**
	 * Returns the aisles of the {@code TiledSpace3D}.
	 * 
	 * @return  an aisle count
	 */
	public int Aisles()
	{
		return Dimensions()[2];
	}
	
	/**
	 * Returns the rows of the {@code TiledSpace3D}.
	 * 
	 * @return  a row count
	 */
	public int Rows()
	{
		return Dimensions()[1];
	}
	
	
	@Override
	public Bounds3D Bounds()
	{
		return (Bounds3D) super.Bounds();
	}
}