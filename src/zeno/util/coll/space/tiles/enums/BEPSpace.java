package zeno.util.coll.space.tiles.enums;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.coll.indices.trees.BEPTree;
import zeno.util.coll.indices.trees.BIPNode;
import zeno.util.coll.space.tiles.TiledSpace;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code BEPSpace} class defines a space partition according to a {@code BEPTree}.
 *
 * @author Waffles
 * @since 28 Dec 2022
 * @version 1.0
 *
 *
 * @param <E>  an enum type
 * @see TiledSpace
 * @see BEPSpace
 */
public class BEPSpace<E extends Enum<E>> implements TiledSpace<BEPSpace<E>.Tile>
{
	/**
	 * The {@code Tile} class defines an implicit tile in a {@code BEPSpace}.
	 *
	 * @author Waffles
	 * @since 13 Apr 2022
	 * @version 1.0
	 * 
	 * 
	 * @see TiledSpace
	 */
	public class Tile implements TiledSpace.Tile
	{
		private E value;
		private int[] coords;
		
		/**
		 * Creates a new {@code Tile}.
		 * 
		 * 
		 * @param val   a tile value
		 * @param crds  a tile coordinate
		 */
		public Tile(E val, int... crds)
		{
			value  = val;
			coords = crds;
		}
		
		/**
		 * Returns the value of the {@code Tile}.
		 * 
		 * @return  a tile value
		 */
		public E Value()
		{
			return value;
		}
		

		@Override
		public BEPSpace<?> Parent()
		{
			return (BEPSpace<?>) BEPSpace.this;
		}

		@Override
		public int[] Coordinates()
		{
			return coords;
		}
	}
	
	
	private float tileSize;
	private BEPTree<E> tree;
	
	/**
	 * Creates a new {@code BEPSpace}.
	 * 
	 * @param tSize  a tile size
	 * @param dim    a tile dimension
	 */
	public BEPSpace(float tSize, int... dim)
	{
		tree = new BEPTree<>(dim);
		tileSize = tSize;
	}

	/**
	 * Returns the bounds of a {@code BIPNode}.
	 * 
	 * @param tgt  a target node
	 * @return  a node boundary
	 * 
	 * 
	 * @see BIPNode
	 * @see ICuboid
	 */
	public ICuboid Bounds(BIPNode<E> tgt)
	{
		int ord = tree.Order();
		int[] min = tgt.Minimum();
		int[] max = tgt.Maximum();
		
		Vector cen = Vectors.create(ord);
		Vector siz = Vectors.create(ord);
		for(int i = 0; i < ord; i++)
		{
			cen.set((min[i] + max[i] + 1) * tileSize / 2, i);
			siz.set((max[i] - min[i] + 1) * tileSize, i);
		}
		
		return Geometries.cuboid(cen, siz);
	}
	
	/**
	 * Changes a value in the {@code BEPSpace}.
	 * 
	 * @param val  an enum value
	 * @param coords  a tile coordinate
	 * @return  a previous value
	 */
	public E put(E val, int... coords)
	{
		if(!contains(coords))
			return null;
		
		E prev = tree.get(coords);
		tree.put(val, coords);
		return prev;
	}
		
	/**
	 * Returns the tree of the {@code BEPSpace}.
	 * 
	 * @return  an enum partition tree
	 * 
	 * 
	 * @see BEPTree
	 */
	public BEPTree<E> Tree()
	{
		return tree;
	}
	
		
	@Override
	public Tile get(int... coords)
	{
		return new Tile(tree.get(coords), coords);
	}
	
	@Override
	public Tile put(Tile val, int... coords)
	{
		E prev = put(val.Value(), coords);
		return new Tile(prev, coords);
	}
	
	@Override
	public Tile remove(int... coords)
	{
		E prev = tree.remove(coords);
		return new Tile(prev, coords);
	}
	
	@Override
	public int[] Dimensions()
	{
		return tree.Dimensions();
	}
	
	@Override
	public float TileSize()
	{
		return tileSize;
	}

}