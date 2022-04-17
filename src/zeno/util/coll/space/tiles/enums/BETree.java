package zeno.util.coll.space.tiles.enums;

import java.util.Iterator;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.coll.Queue;
import zeno.util.coll.queues.FIFOQueue;
import zeno.util.coll.space.tiles.TiledSpace;
import zeno.util.coll.space.trees.KDTree;

/**
 * The {@code BETree} class defines an array index of enum values.
 * </br> Its internal storage is handled by a binary tree to improve performance.
 *
 * @author Waffles
 * @since 13 Apr 2022
 * @version 1.0
 *
 *
 * @param <E>  an enum type
 * @see TiledSpace
 * @see BETree
 * @see KDTree
 * @see Enum
 */
public class BETree<E extends Enum<E>> extends KDTree<BETree<E>.Tile> implements TiledSpace<E, BETree<E>.Tile>
{
	/**
	 * The {@code Nodes} class iterates over {@code BETree} nodes of a specific value.
	 *
	 * @author Waffles
	 * @since 17 Apr 2022
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 * @see BENode
	 */
	public class Nodes implements Iterator<BENode<E>>
	{
		private E value;
		private BENode<E> next;
		private Queue<BENode<E>> nodes;
		
		/**
		 * Creates a new {@code Nodes}.
		 * 
		 * @param val  an enum value
		 */
		public Nodes(E val)
		{
			value = val;
			nodes = new FIFOQueue<>();
			nodes.push(Root());
			next = findNext();
		}
		
		
		private BENode<E> findNext()
		{
			if(nodes.isEmpty())
			{
				return null;
			}
			
			next = nodes.pop();
			if(!next.hasValue(value))
			{
				return findNext();
			}
			
			if(!next.isLeaf())
			{
				nodes.push(next.LChild());
				nodes.push(next.RChild());
				return findNext();
			}
			
			return next;
		}
		
		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public BENode<E> next()
		{
			BENode<E> curr = next;
			next = findNext();
			return curr;
		}
	}
	
	/**
	 * The {@code Tile} class defines an implicit tile in a {@code BETree}.
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
		private BETree<E> parent;
		
		/**
		 * Creates a new {@code Tile}.
		 * 
		 * 
		 * @param p     a parent tree
		 * @param val   a tile value
		 * @param crds  a tile coordinate
		 */
		public Tile(BETree<E> p, E val, int... crds)
		{
			value  = val;
			coords = crds;
			parent = p;
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
		public BETree<E> Parent()
		{
			return parent;
		}

		@Override
		public int[] Coordinates()
		{
			return coords;
		}
	}
	
	
	private float tSize;
	private Queue<BENode<E>> queue;
	
	/**
	 * Creates a new {@code BETree}.
	 * 
	 * @param tSize  a tile size
	 * @param dim  a space dimension
	 */
	public BETree(float tSize, int... dim)
	{
		super(dim.length);
		this.tSize = tSize;
		
		int[] min = new int[dim.length];
		int[] max = new int[dim.length];
		for(int i = 0; i < dim.length; i++)
		{
			max[i] = dim[i] - 1;
		}

		setRoot(create(min, max));
	}
	
	/**
	 * Creates a tile for the {@code BETree}.
	 * 
	 * @param val  an enum value
	 * @param coords  a tile coordinate
	 * @return  an enum tile
	 * 
	 * 
	 * @see Tile
	 */
	public Tile create(E val, int... coords)
	{
		return new Tile(this, val, coords);
	}
	
	/**
	 * Changes a cuboid area of the {@code BETree}.
	 * 
	 * @param val  an index value
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void put(E val, int[] min, int[] max)
	{
		queue = new FIFOQueue<>();
		queue.push(Root());
		
		while(!queue.isEmpty())
		{
			BENode<E> node = queue.pop();
			BENode<E>.Collision cls = node.intersect(min, max);
			if(cls.isEmpty()) continue;
			if(cls.isCover())
			{
				node.clearChildren();
				node.setValue(val);
				continue;
			}
			
			if(node.isTile())
			{
				node.setValue(val);
				continue;
			}
			
			node.addValue(val);
			if(node.isLeaf())
			{
				node.split(min, max);
			}
			
			queue.push(node.LChild());
			queue.push(node.RChild());
		}
	}
	
	/**
	 * Removes a cuboid area of the {@code BETree}.
	 * 
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void remove(int[] min, int[] max)
	{
		put(null, min, max);
	}
	
	/**
	 * Iterates nodes of a type in the {@code BETree}.
	 * 
	 * @param val  an enum value
	 * @return  a node iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BENode
	 */
	public Iterable<BENode<E>> Nodes(E val)
	{
		return () -> new Nodes(val);
	}
	
	/**
	 * Returns a coordinate in the {@code BETree}.
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
		
	
	@Override
	public Iterable<BENode<E>> BFSearch()
	{
		return super.BFSearch();
	}

	@Override
	public Iterable<BENode<E>> DFSearch()
	{
		return super.DFSearch();
	}
	
	
	@Override
	public BENode<E> create(Object... vals)
	{
		int[] min = (int[]) vals[0];
		int[] max = (int[]) vals[1];
		
		return new BENode<>(this, min, max);
	}

	@Override
	public E put(E val, int... coords)
	{
		if(!contains(coords)) return null;

		
		BENode<E> node = Root();
		while(!node.isLeaf())
		{
			node.addValue(val);
			node = node.Child(coords);
		}
		
		if(node.Value() == val)
		{
			return val;
		}
		
		
		E prev = node.Value();
		while(!node.isTile())
		{
			node.addValue(val);
			node.split(coords, coords);
			node = node.Child(coords);
		}
		
		node.setValue(val);
		while(!node.isRoot())
		{
			BENode<E> sibl = node.Sibling();
			if(sibl.isLeaf())
			{
				if(sibl.hasValue(val))
				{
					node = node.Parent();
					node.clearChildren();
					node.setValue(val);
					continue;
				}
			}
			
			return prev;
		}

		return prev;
	}
	
	@Override
	public E get(int... coords)
	{
		if(!contains(coords)) return null;
		
		
		BENode<E> node = Root();
		while(!node.isLeaf())
		{
			node = node.Child(coords);
		}
		
		return node.Value();
	}
			
	
	@Override
	public BENode<E> Root()
	{
		return (BENode<E>) super.Root();
	}
	
	@Override
	public float TileSize()
	{
		return tSize;
	}
	
	
	@Override
	public int[] Minimum()
	{
		return Root().Minimum();
	}
	
	@Override
	public int[] Maximum()
	{
		return Root().Maximum();
	}
}