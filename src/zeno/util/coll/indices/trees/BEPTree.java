package zeno.util.coll.indices.trees;

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
	private Queue<BEPNode<E>> queue;
	
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
		setTool(Tool());
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
			BEPNode<E> node = queue.pop();
			
			
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
			BEPNode<E> node = queue.pop();
			
			
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
	
	
	@Override
	public int[] indexOf(E val)
	{
		BEPNode<E> node = Root();
		if(!node.hasValue(val))
		{
			return null;
		}
		
		while(!node.isLeaf())
		{
			BEPNode<E> lchild = node.LChild();
			BEPNode<E> rchild = node.RChild();
			
			if(lchild.hasValue(val))
				node = lchild;
			else
				node = rchild;
		}
		
		return node.Minimum();
	}

	@Override
	public BEPNode<E> create(Object... vals)
	{
		int[] min = (int[]) vals[0];
		int[] max = (int[]) vals[1];
		
		return new BEPNode<>(this, min, max);
	}
	
	@Override
	public E put(E val, int... coords)
	{
		if(val == null) return remove(coords);
		if(!contains(coords)) return null;
		
		BEPNode<E> node = Root();
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
			BEPNode<E> sibl = node.Sibling();
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
	public E remove(int... coords)
	{
		if(!contains(coords)) return null;

		BEPNode<E> node = Root();
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
	public BEPNode<E> Root()
	{
		return (BEPNode<E>) super.Root();
	}
	
	QRYTool<E> Tool()
	{
		return (node) ->
		{
			return ((BEPNode<E>) node).Value();
		};
	}
}