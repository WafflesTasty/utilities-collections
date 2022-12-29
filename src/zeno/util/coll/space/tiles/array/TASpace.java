package zeno.util.coll.space.tiles.array;

import java.util.Iterator;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.coll.indices.arrays.ArrayIndex;
import zeno.util.coll.space.tiles.TiledSpace;
import zeno.util.coll.utilities.iterators.AtomicIterator;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.tools.helper.Array;
import zeno.util.tools.helper.Iterables;

/**
 * The {@code TASpace} class defines a space partitioned into hypercubes of equal size.
 * </br> Internally, each cube is represented by an element in an array.
 * 
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.0
 *
 * 
 * @param <T>  a tile type
 * @see ArrayIndex
 * @see TiledSpace
 */
public class TASpace<T extends TiledSpace.Tile> extends ArrayIndex<T> implements TiledSpace<T>
{		
	private class Query implements Iterator<T>
	{
		private int[] next, min, max;
		
		public Query(T src, T tgt)
		{			
			min = src.Coordinates();
			max = tgt.Coordinates();
			
			next = Array.copy.of(min);
		}
		
		private int[] findNext()
		{
			int dim = next.length;
			for(int i = 0; i < dim; i++)
			{
				next[i] += 1;
				if(next[i] <= max[i])
				{
					return next;
				}

				if(i == dim-1)
				{
					return null;
				}
				
				next[i] = min[i];
			}
			
			return null;
		}
		
		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public T next()
		{
			T curr = get(next);
			next = findNext();
			return curr;
		}
	}
	
	
	private float tSize;
	
	/**
	 * Creates a new {@code TiledSpace}.
	 * 
	 * @param dim  a space index
	 */
	public TASpace(int... dim)
	{
		super(dim);
		tSize = 2f;
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
	
		
	@Override
	public TASpace<T> Index()
	{
		return this;
	}

	@Override
	public Iterable<T> queryTiles(int[] min, int[] max)
	{
		return () -> new AtomicIterator<>(this, min, max);
	}
	
	@Override
	public Iterable<T> query(ICuboid c)
	{		
		return () -> 
		{
			T min = find(c.Minimum());
			if(min == null)
			{
				min = get(Minimum());
			}

			T max = find(c.Maximum());
			if(max == null)
			{
				max = get(Maximum());
			}

			return new Query(min, max);
		};
	}

	@Override
	public Iterable<T> query(Point p)
	{
		T tile = find(p.asVector());
		if(tile != null)
		{
			return Iterables.singleton(tile);
		}
		
		return Iterables.empty();
	}
	
	@Override
	public float TileSize()
	{
		return tSize;
	}


	T find(Vector v)
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
}