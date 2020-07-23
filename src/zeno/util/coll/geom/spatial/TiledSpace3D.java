package zeno.util.coll.geom.spatial;

import zeno.util.coll.geom.TiledSpace;
import zeno.util.geom.collidables.IGeometrical3D;
import zeno.util.geom.collidables.bounds.Bounds3D;
import zeno.util.geom.collidables.geometry.spatial.Cube;

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
public class TiledSpace3D<T extends TiledSpace3D<T>.Tile3D> extends TiledSpace<T> implements Space3D<T>
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
	public class Tile3D extends TiledSpace<T>.Tile implements IGeometrical3D
	{		
		/**
		 * Creates a new {@code Tile3D}.
		 * 
		 * @param col  a column coordinate
		 * @param row  a row coordinate
		 * @param ais  an aisle coordinate
		 */
		public Tile3D(int col, int row, int ais)
		{
			super(col, row, ais);
		}
		
		/**
		 * Returns the column of the {@code Tile3D}.
		 * 
		 * @return  a column coordinate
		 */
		public int Column()
		{
			return Coordinates()[0];
		}
		
		/**
		 * Returns the aisle of the {@code Tile3D}.
		 * 
		 * @return  an aisle coordinate
		 */
		public int Aisle()
		{
			return Coordinates()[2];
		}
		
		/**
		 * Returns the row of the {@code Tile3D}.
		 * 
		 * @return  a row coordinate
		 */
		public int Row()
		{
			return Coordinates()[1];
		}
		
		
		@Override
		public TiledSpace3D<?> Parent()
		{
			return (TiledSpace3D<?>) super.Parent();
		}
		
		@Override
		public Cube Shape()
		{
			return (Cube) super.Shape();
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