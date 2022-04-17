package zeno.util.coll.space.tiles.enums;

import java.util.Iterator;

import zeno.util.coll.space.trees.KDNode;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.tools.Integers;
import zeno.util.tools.helper.Array;
import zeno.util.tools.helper.Iterables;

/**
 * The {@code BENode} class defines a single node in a {@code BETree}.
 * </br> Each node stores enum values occurring beneath it and its minimum and maximum.
 *
 * @author Waffles
 * @since 13 Apr 2022
 * @version 1.0
 *
 *
 * @param <E>  an enum type
 * @see BETree
 * @see KDNode
 * @see Enum
 */
public class BENode<E extends Enum<E>> extends KDNode<BETree<E>.Tile>
{
	/**
	 * The {@code Tiles} class iterates over an interval of tiles in a {@code BENode}.
	 *
	 * @author Waffles
	 * @since 13 Apr 2022
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 * @see BETree
	 */
	public class Tiles implements Iterator<BETree<E>.Tile>
	{
		private int[] cMin, cMax, next;
					
		/**
		 * Creates a new {@code Tiles}.
		 * 
		 * @param min  an iterator minimum
		 * @param max  an iterator maximum
		 */
		public Tiles(int[] min, int[] max)
		{
			cMin = new int[min.length];
			cMax = new int[max.length];

			for(int i = 0; i < cMin.length; i++)
			{
				cMin[i] = Integers.max(min[i], Minimum()[i]);
				cMax[i] = Integers.min(max[i], Maximum()[i]);
			}
			
			next = findNext();
		}
		
		/**
		 * Creates a new {@code Tiles}.
		 */
		public Tiles()
		{
			cMin = Array.copy.of(Minimum());
			cMax = Array.copy.of(Maximum());
			
			next = findNext();
		}
		
		
		private int[] findNext()
		{
			// If this is the first element...
			if(next == null)
			{
				// Return the minimum.
				next = new int[cMin.length];
				for(int i = 0; i < cMin.length; i++)
				{
					next[i] = cMin[i];
				}
				
				return next;
			}
			
			// Otherwise, increase the counter.
			for(int i = 0; i < next.length; i++)
			{
				next[i] += 1;
				// If it hasn't exceeded maximum...
				if(next[i] <= cMax[i])
				{
					// Return it.
					return next;
				}

				next[i] = cMin[i];
			}

			return null;
		}
		
		@Override
		public BETree<E>.Tile next()
		{
			int[] coords = Array.copy.of(next); next = findNext();
			return Tree().create(Value(), coords);
		}
		
		@Override
		public boolean hasNext()
		{
			return next != null;
		}
	}
	
	/**
	 * The {@code Collision} class provides info on intersection of a range with a {@code BENode}.
	 *
	 * @author Waffles
	 * @since 07 Dec 2021
	 * @version 1.0
	 * 
	 * 
	 * @see BENode
	 */
	public class Collision
	{
		private boolean isCover, isEmpty;
		private int[] cMin, cMax, iMin, iMax;
		
		/**
		 * Creates a new {@code Collision}.
		 * 
		 * @param src  a source node
		 * @param min  a range minimum
		 * @param max  a range maximum
		 */
		public Collision(BENode<E> src, int[] min, int[] max)
		{
			isCover = true;
			
			cMin = src.Minimum();
			cMax = src.Maximum();
			
			iMin = new int[cMin.length];
			iMax = new int[cMax.length];
			
			for(int i = 0; i < cMin.length; i++)
			{
				if(max[i] < cMin[i] || cMax[i] < min[i])
				{
					isEmpty = true;
				}
				
				if(cMin[i] < min[i] || max[i] < cMax[i])
				{
					isCover = false;
				}
				
				iMin[i] = Integers.max(min[i], cMin[i]);
				iMax[i] = Integers.min(max[i], cMax[i]);
			}
		}

		
		/**
		 * Checks if the range covers the entire node.
		 * 
		 * @return  {@code true} if the node is covered
		 */
		public boolean isCover()
		{
			return isCover;
		}
		
		/**
		 * Checks if the range is disjoint from the node.
		 * 
		 * @return  {@code true} if intersection is empty
		 */
		public boolean isEmpty()
		{
			return isEmpty;
		}
		
		/**
		 * Returns the minimum of the intersection.
		 * 
		 * @return  an intersection minimum
		 */
		public int[] Minimum()
		{
			return iMin;
		}
		
		/**
		 * Returns the maximum of the intersection.
		 * 
		 * @return  an intersection maximum
		 */
		public int[] Maximum()
		{
			return iMin;
		}
	}	
		
	
	private Enum<E>[] values;
	private int[] cMin, cMax;
	
	/**
	 * Creates a new {@code BENode}.
	 * 
	 * @param tree  a parent tree
	 * @param min   a minimum index
	 * @param max   a maximum index
	 * 
	 * 
	 * @see BETree
	 */
	public BENode(BETree<E> tree, int[] min, int[] max)
	{
		super(tree);
		
		cMin = Array.copy.of(min);
		cMax = Array.copy.of(max);
		values = new Enum[]{null};
	}

	/**
	 * Intersects a range with the {@code BENode}.
	 * 
	 * @param min  a range minimum
	 * @param max  a range maximum
	 * @return  a collision
	 * 
	 * 
	 * @see Collision
	 */
	public Collision intersect(int[] min, int[] max)
	{
		return new Collision(this, min, max);
	}
	
	/**
	 * Performs a split on the {@code BENode}.
	 * 
	 * @param min  an interval minimum
	 * @param max  an interval maximum
	 */
	public void split(int[] min, int[] max)
	{
		float tSize = Tree().TileSize();
		
		
		float vMin = 0f;
		int kMin = -1, dMin = 0;
		for(int k = 0; k < cMin.length; k++)
		{
			if(dMin < min[k] - cMin[k])
			{
				vMin = (min[k] + 0) * tSize;
				dMin = min[k] - cMin[k];
				kMin = k;
			}
		}

		float vMax = 0f;
		int kMax = -1, dMax = 0;
		for(int k = 0; k < cMax.length; k++)
		{
			if(dMax < cMax[k] - max[k])
			{
				vMax = (max[k] + 1) * tSize;
				dMax = cMax[k] - max[k];
				kMax = k;
			}
		}
		
		
		int[] lMax = Array.copy.of(cMax);
		int[] rMin = Array.copy.of(cMin);
		
		if(dMin < dMax)
		{
			lMax[kMax] = min[kMax] + 0;
			rMin[kMax] = min[kMax] + 1;
			setCut(kMax, vMax);
		}
		else
		{
			lMax[kMin] = min[kMin] - 1;
			rMin[kMin] = min[kMin] + 0;
			setCut(kMin, vMin);
		}

		
		BENode<E> lChild = Tree().create(cMin, lMax);
		BENode<E> rChild = Tree().create(rMin, cMax);
		
		lChild.setValue(Value());
		rChild.setValue(Value());
				
		setLChild(lChild);
		setRChild(rChild);
	}
	
	/**
	 * Returns a child of the {@code BENode}.
	 * 
	 * @param coords  a tile coordinate
	 * @return  a child node
	 * 
	 * 
	 * @see BENode
	 */
	public BENode<E> Child(int... coords)
	{
		if(isLeaf()) return null;
		float tSize = Tree().TileSize();
		if(coords[Cut().Dimension()] * tSize < Cut().Value())
			return LChild();
		return RChild();
	}
	
	
	/**
	 * Adds a value to the {@code BENode}.
	 * 
	 * @param val  an enum value
	 */
	public void addValue(E val)
	{
		if(!hasValue(val))
		{
			values = Array.add.to(values, val);
		}
	}
	
	/**
	 * Checks a value in the {@code BENode}.
	 * 
	 * @param val  an enum value
	 * @return  {@code true} if the node contains the value
	 */
	public boolean hasValue(E val)
	{
		for(Enum<E> e : values)
		{
			if(e == val)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Changes the value of the {@code BENode}.
	 * 
	 * @param val  an enum value
	 */
	public void setValue(E val)
	{
		values = new Enum[]{val};
	}
	
	
	/**
	 * Checks the size of the {@code BENode}.
	 * </br> A node is a tile iff it contains exactly one coordinate.
	 * 
	 * @return  {@code true} if the node is a tile
	 */
	public boolean isTile()
	{
		return Array.equals.of(cMin, cMax);
	}
	
	/**
	 * Returns the minimum of the {@code BENode}.
	 * 
	 * @return  a minimum coordinate
	 */
	public int[] Minimum()
	{
		return cMin;
	}
	
	/**
	 * Returns the maximum of the {@code BENode}.
	 * 
	 * @return  a maximum coordinate
	 */
	public int[] Maximum()
	{
		return cMax;
	}
	
	/**
	 * Returns the value of the {@code BENode}.
	 * 
	 * @return  an enum value
	 */
	public E Value()
	{
		if(values.length > 0)
			return (E) values[0];
		return null;
	}
	
	
	@Override
	public Iterable<BETree<E>.Tile> query(ICuboid c)
	{
		if(!isLeaf()) return Iterables.empty();
		int[] cMin = Tree().Coordinate(c.Minimum());
		int[] cMax = Tree().Coordinate(c.Maximum());
		return () -> new Tiles(cMin, cMax);
	}

	@Override
	public Iterable<BETree<E>.Tile> query(Point p)
	{
		if(!isLeaf()) return Iterables.empty();
		int[] coords = Tree().Coordinate(p.asVector());
		BETree<E>.Tile tile = Tree().create(Value(), coords);
		return Iterables.singleton(tile);
	}

	@Override
	public Iterable<BETree<E>.Tile> queryAll()
	{
		if(!isLeaf()) return Iterables.empty();
		return () -> new Tiles();
	}
	
	
	@Override
	public BENode<E> Parent()
	{
		return (BENode<E>) super.Parent();
	}
	
	@Override
	public BENode<E> LChild()
	{
		return (BENode<E>) super.LChild();
	}
	
	@Override
	public BENode<E> RChild()
	{
		return (BENode<E>) super.RChild();
	}
	
	@Override
	public BENode<E> Sibling()
	{
		return (BENode<E>) super.Sibling();
	}
	
	@Override
	public BETree<E> Tree()
	{
		return (BETree<E>) super.Tree();
	}
}