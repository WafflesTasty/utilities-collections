package zeno.util.coll.indices.enums;

import java.util.Iterator;

import zeno.util.coll.indices.enums.BEIndex.Tile;
import zeno.util.coll.queues.FIFOQueue;
import zeno.util.tools.Integers;

/**
 * The {@code BEIterator} class iterates over rectangular regions of a {@code BEIndex}.
 *
 * @author Waffles
 * @since 25 Nov 2021
 * @version 1.0
 *
 *
 * @param <E>  an enum type
 * @param <T>   a tile type
 * @see Iterator
 * @see Enum
 * @see Tile
 */
public class BEIterator<E extends Enum<E>, T extends Tile<E>> implements Iterator<T>
{
	private BEIndex<E, T> target;
	private int[] cMin, cMax, next;
	private FIFOQueue<BENode<E>> queue;
	
	/**
	 * Creates a new {@code BEIterator}.
	 * 
	 * @param tgt  a target index
	 * 
	 * 
	 * @see BEIndex
	 */
	public BEIterator(BEIndex<E, T> tgt)
	{
		this(tgt, tgt.Minimum(), tgt.Maximum());
	}
		
	/**
	 * Creates a new {@code BEIterator}.
	 * 
	 * @param tgt  a target index
	 * @param min  an iterator minimum
	 * @param max  an iterator maximum
	 * 
	 * 
	 * @see BEIndex
	 */
	public BEIterator(BEIndex<E, T> tgt, int[] min, int[] max)
	{
		queue = new FIFOQueue<>();
		queue.push(tgt.Root());
		cMin = min;	cMax = max;
		next = findNext();
	}
	
	
	private int[] findNext()
	{
		BENode<E> node = queue.peek();
		// If the node is not a leaf...
		if(!node.isLeaf())
		{
			// Proceed to the relevant children...
			BENode<E> nMin = node.Child(cMin);
			BENode<E> nMax = node.Child(cMax);
			
			
			queue.pop();				
			queue.push(nMin);
			if(nMin != nMax)
			{
				queue.push(nMax);
			}
			
			// ...and try again.
			return findNext();
		}

		
		int dim = cMin.length;
		int[] nMin = new int[dim];
		int[] nMax = new int[dim];
		// Otherwise, find the iterator min/max.
		for(int i = 0; i < dim; i++)
		{
			nMin[i] = Integers.max(cMin[i], node.Minimum()[i]);
			nMax[i] = Integers.min(cMax[i], node.Maximum()[i]);
		}
		
		
		// If this is the first element...
		if(next == null)
		{
			// Return the minimum.
			next = nMin; return next;
		}
		
		// Otherwise, increase the counter.
		for(int i = 0; i < dim; i++)
		{
			next[i] += 1;
			// If it hasn't exceeded maximum...
			if(next[i] <= nMax[i])
			{
				// Return it.
				return next;
			}

			next[i] = nMin[i];
		}
		
		// Otherwise, remove the node...
		queue.pop();
		// ...and, try again.
		return findNext();
	}
	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public T next()
	{
		int[] coords = next;
		E value = queue.peek().Value();
		Tile<E> tile = target.create(value, coords);
		
		next = findNext();
		return (T) tile;
	}
}