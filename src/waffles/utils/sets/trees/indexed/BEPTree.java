package waffles.utils.sets.trees.indexed;

import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.delegate.JFIFOQueue;
import waffles.utils.sets.utilities.iterators.BEPNodes;
import waffles.utils.sets.utilities.iterators.BEPObjects;
import waffles.utils.sets.utilities.iterators.BEPKeys;

/**
 * A {@code BEPTree} defines a binary index partition tree for {@code Enum} values.
 *
 * @author Waffles
 * @since 28 Dec 2022
 * @version 1.1
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
	}
	
	/**
	 * Removes an index area of the {@code BEPTree}.
	 * 
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void remove(int[] min, int[] max)
	{
		// Starting a queue from the root...
		queue = new JFIFOQueue<>();
		queue.push(Root());
		
		// As long as the queue is non-empty...
		while(!queue.isEmpty())
		{
			// Pop the next node from the queue.
			BEPNode<E> node = queue.pop();
			
			
			boolean isCover = true;
			boolean isEmpty = false;
			
			int[] cMin = node.Minimum();
			int[] cMax = node.Maximum();

			// For each dimension in the index...
			for(int i = 0; i < cMin.length; i++)
			{
				// Check if the area is disjoint from the node.
				if(max[i] < cMin[i] || cMax[i] < min[i])
				{
					isEmpty = true;
				}
				
				// Check if the area fully covers the node.
				if(cMin[i] < min[i] || max[i] < cMax[i])
				{
					isCover = false;
				}
			}
			
			// If the area is disjoint, skip the node.
			if(isEmpty) continue;
			// If the area fully covers the node...
			if(isCover)
			{
				// Turn the node into a leaf
				// without any value.
				node.merge();
				node.setValue(null);
				continue;
			}
			
			// If the node is a single tile...
			if(node.isTile())
			{
				// Change its value.
				node.setValue(null);
				continue;
			}
			
			// If the node is otherwise a leaf...
			if(node.isLeaf())
			{
				// Skip it if it has no value.
				if(node.Value() == null)
					continue;
				
				// Otherwise, split it.
				node.split(min, max);
			}
			
			// Add the node's children to the queue.
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
		// If the value is null...
		if(val == null)
		{
			// Perform removal instead.
			remove(min, max);
		}
		
		
		// Starting a queue from the root...
		queue = new JFIFOQueue<>();
		queue.push(Root());
		
		// As long as the queue is non-empty...
		while(!queue.isEmpty())
		{
			// Pop the next node from the queue.
			BEPNode<E> node = queue.pop();
			
			
			boolean isCover = true;
			boolean isEmpty = false;
			
			int[] cMin = node.Minimum();
			int[] cMax = node.Maximum();

			// For each dimension in the index...
			for(int i = 0; i < cMin.length; i++)
			{
				// Check if the area is disjoint from the node.
				if(max[i] < cMin[i] || cMax[i] < min[i])
				{
					isEmpty = true;
				}
				
				// Check if the area fully covers the node.
				if(cMin[i] < min[i] || max[i] < cMax[i])
				{
					isCover = false;
				}
			}
			
			// If the area is disjoint, skip the node.
			if(isEmpty) continue;
			// If the area fully covers the node...
			if(isCover)
			{
				// Turn the node into a leaf with
				// the corresponding value.
				node.merge();
				node.setValue(val);
				continue;
			}
			
			// If the node is a single tile...
			if(node.isTile())
			{
				// Change its value.
				node.setValue(val);
				continue;
			}
			
			// Otherwise, add the value to the node...
			node.addValue(val);
			if(node.isLeaf())
			{
				// And split it if it is a leaf.
				node.split(min, max);
			}
			
			// Add the node's children to the queue.
			queue.push(node.LChild());
			queue.push(node.RChild());
		}
	}
	
	/**
	 * Iterates over the nodes of the {@code BEPTree}.
	 * 
	 * @param val  a node value
	 * @return  a node iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BEPNode
	 */
	public <N extends BEPNode<E>> Iterable<N> Nodes(E val)
	{
		return () -> new BEPNodes<>(this, val);
	}
	
	/**
	 * Iterates over objects along the {@code BEPTree}.
	 * 
	 * @param <O>  an object type
	 * @param src  an indexed set
	 * @param val  an enum value
	 * @return  an object iterable
	 * 
	 * 
	 * @see IndexedSet
	 * @see Iterable
	 */
	public <O> Iterable<O> Objects(IndexedSet<O> src, E val)
	{
		return () -> new BEPObjects<>(src, this, val);
	}
	
	/**
	 * Iterates over the keys of the {@code BEPTree}.
	 * 
	 * @param val  a node value
	 * @return  a key iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public Iterable<int[]> Keys(E val)
	{
		return () -> new BEPKeys(this, val);
	}
	
	/**
	 * Clears the contents of the {@code BEPTree}.
	 * 
	 * @param val  a base value
	 */
	public void fill(E val)
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
			node = node.get(coords);
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
			
			node = node.get(coords);
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
			node = node.get(coords);
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
			node = node.get(coords);
		}
		
		// And remove the final tile's value.
		node.setValue(null);		
		return prev;
	}
	
	@Override
	public void clear()
	{
		super.clear();
		if(Root() != null)
		{
			fill(Root().Value());
		}
	}
	
	
	@Override
	public BEPNode<E> createNode(Object... vals)
	{
		int[] min = (int[]) vals[0];
		int[] max = (int[]) vals[1];
		
		return new BEPNode<>(this, min, max);
	}
	
	@Override
	public BEPNode<E> nodeAt(int... coords)
	{
		return (BEPNode<E>) super.nodeAt(coords);
	}
	
	@Override
	public BEPNode<E> Root()
	{
		return (BEPNode<E>) super.Root();
	}
}