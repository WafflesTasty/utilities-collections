package zeno.util.coll.indices.enums;

import java.util.Iterator;

import zeno.util.coll.Queue;
import zeno.util.coll.indices.arrays.Index;
import zeno.util.coll.indices.enums.BENode.Collision;
import zeno.util.coll.queues.FIFOQueue;
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
		
	/**
	 * The {@code TypeIterator} class iterates over all nodes with a specific value.
	 *
	 * @author Waffles
	 * @since 08 Dec 2021
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 * @see BENode
	 */
	public class TypeIterator implements Iterator<BENode<E>>
	{
		private E value;
		private BENode<E> next;
		private Queue<BENode<E>> nodes;
		
		/**
		 * Creates a new {@code TypeIterator}.
		 * 
		 * @param val  an enum value
		 */
		public TypeIterator(E val)
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
	

	private int[] dimension;
	private Queue<BENode<E>> queue;
	
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
	 * Removes a cuboid area of the {@code BEIndex}.
	 * 
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void remove(int[] min, int[] max)
	{
		put(null, min, max);
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
		queue = new FIFOQueue<>();
		queue.push(Root());
		
		while(!queue.isEmpty())
		{
			BENode<E> node = queue.pop();
			Collision<E> cls = node.intersect(min, max);
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
				node.split();
			}
			
			queue.push(node.LChild());
			queue.push(node.RChild());
		}
	}
	
	/**
	 * Iterates nodes of a type in the {@code BEIndex}.
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
		return () -> new TypeIterator(val);
	}
	
				
	protected Tile<E> create(E val, int[] coords)
	{
		return new Tile<>(val, coords);
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
			node.split();
			node.addValue(val);
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
		BENode<E> node = Root();
		while(!node.isLeaf())
		{
			node= node.Child(coords);
		}
		
		return node.Value();
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