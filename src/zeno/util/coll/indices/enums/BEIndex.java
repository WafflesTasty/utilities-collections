package zeno.util.coll.indices.enums;

import java.util.Iterator;

import zeno.util.coll.indices.arrays.Index;
import zeno.util.coll.trees.binary.BiTree;

/**
 * The {@code BEIndex} class defines an index of enum values.
 * </br> Its data is stored as a binary tree to optimize index searches.
 *
 * @author Waffles
 * @since 25 Nov 2021
 * @version 1.1
 * 
 * 
 * @param <E>  an enum type
 * @param <T>   a tile type
 * @see BiTree
 * @see Index
 */
public class BEIndex<E extends Enum<E>, T extends BEIndex.Tile<E>> extends BiTree implements Index<E>, Iterable<T>
{		
	/**
	 * The {@code Tile} interface defines a single tile in a {@code BEIndex}.
	 *
	 * @author Waffles
	 * @since 24 Nov 2021
	 * @version 1.0
	 *
	 *
	 * @param <E>  an enum type
	 * @see Enum
	 */
	public static class Tile<E extends Enum<E>>
	{
		private E value;
		private int[] coords;
		
		/**
		 * Creates a new {@code Tile}.
		 * 
		 * @param val   a tile value
		 * @param crds  a tile coordinate
		 */
		public Tile(E val, int... crds)
		{
			coords = crds;
			value  = val;
		}

		/**
		 * Returns the coordinates of the {@code Tile}.
		 * 
		 * @return  a tile coordinate
		 */
		public int[] Coordinates()
		{
			return coords;
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
	}
	
	
	private int[] dimension;
	
	/**
	 * Creates a new {@code BEIndex}.
	 * 
	 * @param dim  an index dimension
	 */
	public BEIndex(int... dim)
	{
		int[] min = new int[dim.length];
		int[] max = new int[dim.length];
		for(int i = 0; i < dim.length; i++)
		{
			max[i] = dim[i] - 1;
		}
		
		setRoot(create(min, max));
		dimension = dim;
	}

	/**
	 * Changes a cuboid area of the {@code BEIndex}.
	 * 
	 * @param val  an index value
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void put(E val, int[] min, int[] max)
	{
		Root().put(val, min, max);
	}
	
				
	protected BENode<E> search(int... coords)
	{
		BENode<E> node = Root();
		// Start checking from root...
		while(true)
		{
			// If the node is a leaf...
			if(node.isLeaf())
			{
				// Return it.
				return node;
			}
			
			// Otherwise, move to a child node.
			node = node.Child(coords);
		}
	}
	
	protected Tile<E> create(E val, int[] coords)
	{
		return new Tile<>(val, coords);
	}
	
	@Override
	public E put(E val, int... coords)
	{
		BENode<E> node = search(coords);
		if(node.Value() == val)
		{
			return val;
		}
		
		E prev = node.Value();
		while(!node.isTile())
		{
			node.split();
			node.setValue(null);
			node = node.Child(coords);
		}
		
		node.setValue(val);
		while(!node.isRoot())
		{
			BENode<E> sibl = node.Sibling();
			if(sibl.Value() != val)
			{
				return prev;
			}
			
			node = node.Parent();
			node.clearChildren();
			node.setValue(val);
		}

		return prev;
	}
	
	@Override
	public E get(int... coords)
	{
		return search(coords).Value();
	}

	
	@Override
	public int[] Dimensions()
	{
		return dimension;
	}
	
	@Override
	public Iterator<T> iterator()
	{
		return new BEIterator<>(this);
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
	public BENode<E> Root()
	{
		return (BENode<E>) super.Root();
	}
}