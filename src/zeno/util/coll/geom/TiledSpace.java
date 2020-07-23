package zeno.util.coll.geom;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.coll.indices.ArrayIndex;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.IGeometrical;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.geometry.generic.ICube;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.helper.Iterables;

/**
 * The {@code TiledSpace} class defines a space partitioned into hypercubes of equal size.
 *
 * @author Zeno
 * @since 28 Feb 2020
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see ArrayIndex
 * @see Space
 */
public class TiledSpace<T extends TiledSpace.Tile> extends ArrayIndex<T> implements Space<T>
{		
	/**
	 * The {@code Tile} interface defines a single element of a {@code TiledSpace}.
	 *
	 * @author Zeno
	 * @since 26 Feb 2020
	 * @version 1.0
	 * 
	 * 
	 * @see IGeometrical
	 */
	public static interface Tile extends IGeometrical
	{
		/**
		 * The {@code Transform} interface defines the transformation of a {@code Tile}.
		 *
		 * @author Zeno
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
				int ord = target.Parent().Order();
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
				int ord = target.Parent().Order();
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
						inv.set(2 / size, i, i);
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
			Vector o = Vectors.create(Parent().Order());
			return Geometries.cube(o, 2f);
		}
	}
	
	
	private float tSize;
	
	/**
	 * Creates a new {@code TiledSpace}.
	 * 
	 * @param dim  a space index
	 */
	public TiledSpace(int... dim)
	{
		super(dim); setTileSize(2f);
	}
	
	/**
	 * Changes the tile size of the {@code TiledSpace}.
	 * 
	 * @param s  a tile size
	 */
	public void setTileSize(float s)
	{
		tSize = s;
	}
	
	/**
	 * Returns the tile size of the {@code TiledSpace}.
	 * 
	 * @return  a tile size
	 */
	public float TileSize()
	{
		return tSize;
	}
	
	/**
	 * Returns the size of the {@code TiledSpace}.
	 * 
	 * @return  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Size()
	{
		Vector s = Vectors.create(Order());
		for(int i = 0; i < Order(); i++)
		{
			s.set(Dimensions()[i] * tSize, i);
		}
		
		return s;
	}
	
	
	private T query(Vector v)
	{
		int[] index = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			index[i] = (int) (v.get(i) / TileSize());
			if(index[i] < 0 || Dimensions()[i] <= index[i])
			{
				return null;
			}
		}
		
		return get(index);
	}
	
	@Override
	public Iterable<T> query(ICuboid c)
	{
		T min = query(c.Minimum());
		if(min == null)
		{
			min = get(Minimum());
		}
		
		T max = query(c.Maximum());
		if(max == null)
		{
			max = get(Maximum());
		}
		
		return null;
	}

	@Override
	public Iterable<T> query(Point p)
	{
		T tile = query(p.asVector());
		if(tile != null)
		{
			return Iterables.singleton(tile);
		}
		
		return Iterables.empty();
	}
	
	@Override
	public Bounds Bounds()
	{
		return Geometries.cuboid(Size().times(0.5f), Size());
	}
}