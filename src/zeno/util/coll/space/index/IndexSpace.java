package zeno.util.coll.space.index;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.coll.indices.Index;
import zeno.util.coll.space.Space;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code IndexSpace} interface defines an index partitioned in a floating point space.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see IBounded
 * @see Space
 */
public interface IndexSpace<O extends IBounded> extends Space<O>
{	
	/**
	 * Returns the tile size of the {@code IndexSpace}.
	 * 
	 * @return  a tile size
	 */
	public abstract float TileSize();
	
	/**
	 * Returns the index of the {@code IndexSpace}.
	 * 
	 * @return  a space index
	 * 
	 * 
	 * @see Index
	 */
	public abstract Index<?> Index();
	

	
	/**
	 * Returns a coordinate in the {@code IndexSpace}.
	 * 
	 * @param v  a space vector
	 * @return  a tile coordinate
	 * 
	 * 
	 * @see Vector
	 */
	public default int[] Coordinate(Vector v)
	{
		int ord = Index().Order();
		int[] coords = new int[ord];
		for(int i = 0; i < ord; i++)
		{
			coords[i] = (int) (v.get(i) / TileSize());
			if(coords[i] < 0 || Index().Dimensions()[i] <= coords[i])
			{
				return null;
			}
		}

		return coords;
	}
			
	/**
	 * Returns the size of the {@code IndexSpace}.
	 * 
	 * @return  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Size()
	{
		int[] dims = Index().Dimensions();

		Vector s = Vectors.create(dims.length);
		for(int i = 0; i < dims.length; i++)
		{
			s.set(dims[i] * TileSize(), i);
		}
		
		return s;
	}

			
	@Override
	public default Bounds Bounds()
	{
		return Geometries.cuboid(Size().times(0.5f), Size());
	}
}