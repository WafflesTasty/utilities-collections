package zeno.util.coll.space.tiles;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.coll.indices.Index;
import zeno.util.coll.space.Space;
import zeno.util.coll.utilities.iterators.AtomicIterator;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.IGeometrical;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.geometry.generic.ICube;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.helper.Iterables;

/**
 * The {@code TiledSpace} interface defines a space partitioned into hypercubes of equal size.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.1
 *
 * 
 * @param <T>  a tile type
 * @see Index
 * @see Space
 */
public interface TiledSpace<T extends TiledSpace.Tile> extends Index.Atomic<T>, Space<T>
{
	/**
	 * The {@code Tile} interface defines a single element of a {@code TiledSpace}.
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
			Vector o = Vectors.create(Coordinates().length);
			return Geometries.cube(o, 2f);
		}
	}

	
	/**
	 * Returns the tile size of the {@code TiledSpace}.
	 * 
	 * @return  a tile size
	 */
	public abstract float TileSize();
	
	/**
	 * Returns a coordinate in the {@code TiledSpace}.
	 * 
	 * @param v  a space vector
	 * @return  a tile coordinate
	 * 
	 * 
	 * @see Vector
	 */
	public default int[] Coordinate(Vector v)
	{
		int[] coords = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			coords[i] = (int) (v.get(i) / TileSize());
			if(coords[i] < 0 || Dimensions()[i] <= coords[i])
			{
				return null;
			}
		}

		return coords;
	}
	
	/**
	 * Iterates over tiles in the {@code TiledSpace}.
	 * 
	 * @return  a tile iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<T> queryAll()
	{
		return () -> new AtomicIterator<>(this, Minimum(), Maximum());
	}
	
	/**
	 * Returns the size of the {@code TiledSpace}.
	 * 
	 * @return  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Size()
	{
		int dim = Dimensions().length;
		Vector s = Vectors.create(dim);
		for(int i = 0; i < dim; i++)
		{
			s.set(Dimensions()[i] * TileSize(), i);
		}
		
		return s;
	}

		
	@Override
	public default Iterable<T> query(Point p)
	{
		int[] crds = Coordinate(p.asVector());
		return Iterables.singleton(get(crds));
	}
	
	@Override
	public default Iterable<T> query(ICuboid c)
	{
		int[] min = Coordinate(c.Minimum());
		int[] max = Coordinate(c.Maximum());

		return () -> new AtomicIterator<>(this, min, max);
	}

	@Override
	public default int[] indexOf(T val)
	{
		return val.Coordinates();
	}
	
	@Override
	public default Bounds Bounds()
	{
		return Geometries.cuboid(Size().times(0.5f), Size());
	}
}