package zeno.util.coll.indices.trees;

import java.util.Iterator;

import zeno.util.coll.Queue;
import zeno.util.coll.indices.Index;
import zeno.util.coll.queues.FIFOQueue;
import zeno.util.tools.Integers;

/**
 * The {@code BEPTree} class performs binary partition on an enum index.
 *
 * @author Waffles
 * @since 28 Dec 2022
 * @version 1.0
 *
 *
 * @param <E>  an enum type
 * @see BIPTree
 * @see Index
 * @see Enum
 */
public class BEPTree<E extends Enum<E>> extends BIPTree<E> implements Index.Atomic<E>
{	
	/**
	 * The {@code Nodes} class iterates over {@code BEPTree} nodes of a specific value.
	 *
	 * @author Waffles
	 * @since 17 Apr 2022
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 * @see BIPNode
	 */
	public class QRYValue implements Iterator<BIPNode<E>>
	{
		private E value;
		private BIPNode<E> next;
		private Queue<BIPNode<E>> nodes;
		
		/**
		 * Creates a new {@code QRYValue}.
		 * 
		 * @param val  an enum value
		 */
		public QRYValue(E val)
		{
			value = val;
			nodes = new FIFOQueue<>();
			nodes.push(Root());
			next = findNext();
		}
		
		
		BIPNode<E> findNext()
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
		public BIPNode<E> next()
		{
			BIPNode<E> curr = next;
			next = findNext();
			return curr;
		}
	}
	
	
	private Queue<BIPNode<E>> queue;
	
	/**
	 * Creates a new {@code BEPTree}.
	 * 
	 * @param dim    a tile dimension
	 */
	public BEPTree(int... dim)
	{
		super(dim);
		
		int[] min = new int[dim.length];
		int[] max = new int[dim.length];
		for(int i = 0; i < dim.length; i++)
		{
			max[i] = dim[i] - 1;
		}

		setRoot(create(min, max));
	}

	/**
	 * Queries a value into the {@code BEPTree}.
	 * 
	 * @param val  a value to query
	 * @return  a node iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BIPNode
	 */
	public Iterable<BIPNode<E>> query(E val)
	{
		return () -> new QRYValue(val);
	}
	
	/**
	 * Changes a cuboid area of the {@code BEPTree}.
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
			BIPNode<E> node = queue.pop();
			
			
			boolean isCover = true;
			boolean isEmpty = false;
			
			int[] cMin = node.Minimum();
			int[] cMax = node.Maximum();

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
			}
			
			if(isEmpty) continue;
			if(isCover)
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
	 * Removes a cuboid area of the {@code BEPTree}.
	 * 
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void remove(int[] min, int[] max)
	{
		queue = new FIFOQueue<>();
		queue.push(Root());
		
		while(!queue.isEmpty())
		{
			BIPNode<E> node = queue.pop();
			
			
			boolean isCover = true;
			boolean isEmpty = false;
			
			int[] cMin = node.Minimum();
			int[] cMax = node.Maximum();
			
			int[] iMin = new int[cMin.length];
			int[] iMax = new int[cMax.length];
			
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
			
			if(isEmpty) continue;
			if(isCover)
			{
				node.clearChildren();
				node.setValue(null);
				continue;
			}
			
			if(node.isTile())
			{
				node.setValue(null);
				continue;
			}
						
			if(node.isLeaf())
			{
				if(node.Value() == null)
					continue;
				
				node.split(min, max);
			}
			
			queue.push(node.LChild());
			queue.push(node.RChild());
		}
	}
		
	
	@Override
	public E remove(int... coords)
	{
		if(!contains(coords)) return null;

		BIPNode<E> node = Root();
		while(!node.isLeaf())
		{
			node = node.Child(coords);
		}
		
		if(node.Value() == null)
		{
			return null;
		}
		
		E prev = node.Value();
		while(!node.isTile())
		{
			node.split(coords, coords);
			node = node.Child(coords);
		}
		
		node.setValue(null);		
		return prev;
	}
	
	@Override
	public E put(E val, int... coords)
	{
		if(val == null) return remove(coords);
		if(!contains(coords)) return null;
		
		BIPNode<E> node = Root();
		while(!node.isLeaf())
		{
			node.addValue(val);
			node = node.Child(coords);
		}
		
		if(val.equals(node.Value()))
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
			BIPNode<E> sibl = node.Sibling();
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
	public int[] indexOf(E val)
	{
		BIPNode<E> node = Root();
		if(!node.hasValue(val))
		{
			return null;
		}
		
		while(!node.isLeaf())
		{
			BIPNode<E> lchild = node.LChild();
			BIPNode<E> rchild = node.RChild();
			
			if(lchild.hasValue(val))
				node = lchild;
			else
				node = rchild;
		}
		
		return node.Minimum();
	}
}