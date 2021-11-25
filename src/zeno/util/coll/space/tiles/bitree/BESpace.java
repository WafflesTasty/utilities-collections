package zeno.util.coll.space.tiles.bitree;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.coll.indices.enums.BEIndex;
import zeno.util.coll.indices.enums.BEIterator;
import zeno.util.coll.space.tiles.TiledSpace;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.tools.helper.Iterables;

/**
 * The {@code BESpace} class defines a tiled space where each tile stores an enum value.
 * </br> Internally, the space behaves as a binary tree to speed up queries.
 *
 * @author Waffles
 * @since 25 Nov 2021
 * @version 1.0
 *
 *
 * @param <E>  an enum type
 * @see TiledSpace
 * @see BEIndex
 * @see BESpace
 * @see Enum
 */
public class BESpace<E extends Enum<E>> extends BEIndex<E, BESpace.Tile<E>> implements TiledSpace<E, BESpace.Tile<E>>
{
	/**
	 * The {@code Tile} class defines a single implicit tile in a {@code BESpace}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2021
	 * @version 1.0
	 * 
	 * 
	 * @param <E>  an enum type
	 * @see TiledSpace
	 * @see BEIndex
	 * @see Enum
	 */
	public static class Tile<E extends Enum<E>> extends BEIndex.Tile<E> implements TiledSpace.Tile
	{
		private BESpace<E> parent;
		
		/**
		 * Creates a new {@code Tile}.
		 * 
		 * @param p     a parent space
		 * @param val   a tile value
		 * @param crds  a tile coordinate
		 */
		public Tile(BESpace<E> p, E val, int[] crds)
		{
			super(val, crds);
			parent = p;
		}

		@Override
		public BESpace<E> Parent()
		{
			return parent;
		}
	}
	
	
	private float tSize;
	private BEIndex<E, Tile<E>> index;
	
	/**
	 * Creates a new {@code BESpace}.
	 * 
	 * @param dim  an index dimension
	 */
	public BESpace(int... dim)
	{
		index = new BEIndex<>(dim);
		tSize = 2f;
	}
	
	/**
	 * Changes the tile size of the {@code BESpace}.
	 * 
	 * @param size  a tile size
	 */
	public void setTileSize(float size)
	{
		tSize = size;
	}
	
	
	@Override
	public Iterable<Tile<E>> query(Point p)
	{
		Vector v = p.asVector();
		int[] coords = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			coords[i] = (int) (v.get(i) / TileSize());
			if(coords[i] < 0 || Dimensions()[i] <= coords[i])
			{
				return Iterables.empty();
			}
		}

		if(contains(coords))
		{
			E value = index.get(coords);
			if(value != null)
			{
				Tile<E> tile = create(value, coords);
				return Iterables.singleton(tile);
			}
		}
		
		return Iterables.empty();
	}

	@Override
	public Iterable<Tile<E>> query(ICuboid c)
	{
		Vector vMin = c.Minimum();
		Vector vMax = c.Maximum();
		
		int[] min = new int[Order()];
		int[] max = new int[Order()];
		
		for(int i = 0; i < Order(); i++)
		{
			int dim = Dimensions()[i];
			
			min[i] = (int) (vMin.get(i) / TileSize());
			max[i] = (int) (vMax.get(i) / TileSize());
			
			if(dim <= min[i] || max[i] < 0)
			{
				return Iterables.empty();
			}
			
			if(dim <= max[i])
				max[i] = dim - 1;
			if(min[i] < 0)
				min[i] = 0;
		}
		
		return () -> new BEIterator<>(this, min, max);
	}
		
	
	@Override
	protected Tile<E> create(E value, int[] index)
	{
		return new Tile<>(this, value, index);
	}
	
	@Override
	public float TileSize()
	{
		return tSize;
	}
}