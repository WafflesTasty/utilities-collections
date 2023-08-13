package waffles.util.sets.trees.indexed;

import waffles.util.sets.indexed.MutableIndex;
import waffles.util.sets.queues.Queue;
import waffles.util.sets.queues.delegate.JFIFOQueue;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code BEPTree} defines a binary index partition tree for {@code Enum} values.
 *
 * @author Waffles
 * @since 28 Dec 2022
 * @version 1.0
 *
 *
 * @param <E>  an index enum type
 * @see MutableIndex
 * @see BIPTree
 * @see Enum
 */
public class BEPTree<E extends Enum<E>> extends BIPTree<E> implements MutableIndex<E>
{		
	private Queue<BEPNode<E>> queue;
	
	/**
	 * Creates a new {@code BEPTree}.
	 * 
	 * @param dim  an index dimension
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
	 * Removes an index area of the {@code BEPTree}.
	 * 
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void remove(int[] min, int[] max)
	{
		queue = new JFIFOQueue<>();
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
				node.merge();
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
	 * Changes an index area of the {@code BEPTree}.
	 * 
	 * @param val  an index value
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void put(E val, int[] min, int[] max)
	{
		queue = new JFIFOQueue<>();
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
				node.merge();
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
	 * Clears the contents of the {@code BEPTree}.
	 * 
	 * @param val  a base value
	 */
	public void clear(E val)
	{
		Root().merge();
		Root().setValue(val);
	}
	
		
	@Override
	public E valueOf(BIPNode node)
	{
		return ((BEPNode<E>) node).Value();
	}
		
	@Override
	public E put(E val, int... coords)
	{
		// If the value is null...
		if(val == null)
		{
			// Perform removal instead.
			return remove(coords);
		}
		
		// If the coordinates are out of bounds...
		if(!contains(coords))
		{
			// Bail.
			return null;
		}
		
		// Starting from the root...
		BEPNode<E> node = Root();
		while(!node.isLeaf())
		{
			// Find the closest child,
			// adding the value to the
			// nodes along the way.
			node.addValue(val);
			node = node.Child(coords);
		}
		
		// If the final child has the same value...
		if(val.equals(node.Value()))
		{
			// Bail.
			return val;
		}
		
		
		// If not, split it down to the tile.
		E prev = node.Value();
		while(!node.isTile())
		{
			node.split(coords, coords);
			node.addValue(val);
			
			node = node.Child(coords);
		}
		
		node.setValue(val);
		while(!node.isRoot())
		{
			// Finally, recursively merge
			// siblings with the same value.
			BEPNode<E> sibl = node.Sibling();
			if(sibl.isLeaf() && sibl.hasValue(val))
			{
				node = node.Parent();
				node.setValue(val);
				node.merge();
				continue;
			}
			
			return prev;
		}
		
		return prev;
	}

	@Override
	public E remove(int... coords)
	{
		// If the coordinates are out of bounds...
		if(!contains(coords))
		{
			// Bail.
			return null;
		}

		// Otherwise, start from the root...
		BEPNode<E> node = Root();
		while(!node.isLeaf())
		{
			// And find the closest child node.
			node = node.Child(coords);
		}
		
		// If it has no value...
		if(node.Value() == null)
		{
			// Bail.
			return null;
		}
		
		// Otherwise, split it down to the tile...
		E prev = node.Value();
		while(!node.isTile())
		{
			node.split(coords, coords);
			node = node.Child(coords);
		}
		
		// And remove the final tile's value.
		node.setValue(null);		
		return prev;
	}
	
	@Override
	public void clear()
	{
		Root().setValue(Root().Value());
		Root().merge();
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
	public BEPNode<E> Root()
	{
		return (BEPNode<E>) super.Root();
	}
}