package zeno.util.coll.space.tiles.bitree;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.coll.indices.enums.BEIndex;
import zeno.util.coll.indices.enums.BEIterator;
import zeno.util.coll.indices.enums.BENode;
import zeno.util.coll.space.tiles.TiledSpace;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.utilities.Geometries;
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
	
	/**
	 * The {@code Node} class defines a single node in a {@code BESpace}.
	 * Since this is a bounded space, the nodes are bounded objects.
	 * 
	 * @author Waffles
	 * @since 02 Dec 2021
	 * @version 1.0
	 *
	 *
	 * @param <E>  an enum type
	 * @see IBounded
	 * @see BENode
	 */
	public static class Node<E extends Enum<E>> extends BENode<E> implements IBounded
	{
		/**
		 * Creates a new {@code Node}.
		 * 
		 * @param tree  a parent tree
		 * @param min   an index minimum
		 * @param max   an index maximum
		 * 
		 * 
		 * @see BESpace
		 */
		public Node(BESpace<E> tree, int[] min, int[] max)
		{
			super(tree, min, max);
		}

		
		@Override
		public BESpace<E> Tree()
		{
			return (BESpace<E>) super.Tree();
		}
		
		@Override
		public ICuboid Bounds()
		{
			int[] min = Minimum();
			int[] max = Maximum();
			
			float tSize = Tree().TileSize();
						
			Vector c = Vectors.create(min.length);
			Vector s = Vectors.create(min.length);

			for(int i = 0; i < min.length; i++)
			{
				float dif = max[i] - min[i] + 1;
				float avg = (min[i] + max[i] + 1) / 2f;
				
				c.set(avg * tSize, i);
				s.set(dif * tSize, i);
			}
			
			return Geometries.cuboid(c, s);
		}
	}
	
	
	private float tSize;
	
	/**
	 * Creates a new {@code BESpace}.
	 * 
	 * @param dim  an index dimension
	 */
	public BESpace(int... dim)
	{
		super(dim);
		tSize = 2f;
	}

	/**
	 * Returns a coordinate in the {@code BESpace}.
	 * 
	 * @param v  a target vector
	 * @return   a tile coordinate
	 * 
	 * 
	 * @see Vector
	 */
	public int[] Coordinate(Vector v)
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
	 * Changes the tile size of the {@code BESpace}.
	 * 
	 * @param size  a tile size
	 */
	public void setTileSize(float size)
	{
		tSize = size;
	}
	
	
	@Override
	public Node<E> create(Object... vals)
	{
		int[] min = (int[]) vals[0];
		int[] max = (int[]) vals[1];

		return new Node<>(this, min, max);
	}
	
	@Override
	protected Tile<E> create(E value, int[] index)
	{
		return new Tile<>(this, value, index);
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
	public Iterable<Tile<E>> query(Point p)
	{
		int[] coords = Coordinate(p.asVector());
		if(coords == null)
		{
			return Iterables.empty();
		}
		
		if(contains(coords))
		{
			E value = get(coords);
			if(value != null)
			{
				Tile<E> tile = create(value, coords);
				return Iterables.singleton(tile);
			}
		}
		
		return Iterables.empty();
	}
		
	@Override
	public float TileSize()
	{
		return tSize;
	}

	@Override
	public Vector Size()
	{
		int[] max = Maximum();
		Vector size = Vectors.create(max.length);
		for(int i = 0; i < max.length; i++)
		{
			size.set(max[i] * tSize, i);
		}

		return size;
	}
}