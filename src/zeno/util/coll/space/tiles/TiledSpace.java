package zeno.util.coll.space.tiles;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.coll.space.Space;
import zeno.util.coll.space.index.IndexSpace;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.IGeometrical;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICube;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code TiledSpace} interface defines an index partitioned in a floating point space.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.1
 *
 * 
 * @param <T>  a  tile type
 * @see IBounded
 * @see Space
 */
public interface TiledSpace<T extends TiledSpace.Tile> extends IndexSpace<T>
{	
	/**
	 * The {@code Tile} interface defines a single element of an {@code TiledSpace}.
	 *
	 * @author Waffles
	 * @since 26 Feb 2020
	 * @version 1.1
	 * 
	 * 
	 * @see IGeometrical
	 */
	public static interface Tile extends IGeometrical
	{
		/**
		 * The {@code Transform} interface defines the transformation of a {@code Tile}.
		 *
		 * @author Waffles
		 * @since 26 Feb 2020
		 * @version 1.0
		 * 
		 * 
		 * @see ITransformation
		 */
		public static class Transform implements ITransformation
		{
			private Tile target;
			
			/**
			 * Creates a new {@code Transform}.
			 * 
			 * @param t  a target tile
			 * 
			 * 
			 * @see Tile
			 */
			public Transform(Tile t)
			{
				target = t;
			}
			
			
			@Override
			public Matrix Matrix(int dim)
			{
				int ord = target.Coordinates().length;
				float size = target.Parent().TileSize();
				
				Matrix mat = Matrices.create(dim + 1, dim + 1);
				for(int i = 0; i <= dim; i++)
				{
					if(ord <= i)
						mat.set(1f, i, i);
					else
					{
						int ci = target.Coordinates()[i];
						
						mat.set(size * (ci + 0.5f), i, dim);
						mat.set(size / 2, i, i);
					}
				}	

				return mat;
			}
			
			@Override
			public Matrix Inverse(int dim)
			{
				int ord = target.Coordinates().length;
				float size = target.Parent().TileSize();
				
				Matrix inv = Matrices.create(dim + 1, dim + 1);
				for(int i = 0; i <= dim; i++)
				{
					if(ord <= i)
						inv.set(1f, i, i);
					else
					{
						int ci = target.Coordinates()[i];
						
						inv.set(- (2 * ci + 1), i, dim);
						inv.set(2f / size, i, i);
					}
				}

				return inv;
			}
		}
		
		
		/**
		 * Returns the coordinates of the {@code Tile}.
		 * 
		 * @return  a coordinate set
		 */
		public abstract int[] Coordinates();
		
		/**
		 * Returns the parent space of the {@code Tile}.
		 * 
		 * @return  a parent space
		 * 
		 * 
		 * @see TiledSpace
		 */
		public abstract TiledSpace<?> Parent();
		
		
		@Override
		public default Transform Transform()
		{
			return new Transform(this);
		}

		@Override
		public default ICube Shape()
		{
			return Geometries.cube(Coordinates().length);
		}
	}
	
		
	/**
	 * Queries tiles from the {@code TiledSpace}.
	 * 
	 * @param min  a tile minimum
	 * @param max  a tile maximum
	 * @return  an object iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public abstract Iterable<T> queryTiles(int[] min, int[] max);
	
	
	/**
	 * Queries tiles from the {@code TiledSpace}.
	 * 
	 * @param crds  a tile coordinate
	 * @return  a target tile
	 */
	public default T queryTile(int... crds)
	{
		return (T) Index().get(crds);
	}
		
	/**
	 * Queries tiles from the {@code TiledSpace}.
	 * 
	 * @return  an object iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<T> queryTiles()
	{
		return queryTiles(Index().Minimum(), Index().Maximum());
	}
	
			
	@Override
	public default Bounds Bounds()
	{
		return Geometries.cuboid(Size().times(0.5f), Size());
	}
}