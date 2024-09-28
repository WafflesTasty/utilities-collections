package waffles.utils.sets.queues.ordered;

import java.util.Comparator;
import java.util.Iterator;

import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.trees.binary.search.BSNode;
import waffles.utils.sets.trees.binary.search.BSTree;

/**
 * A {@code BSQueue} defines an ordered queue as a wrapper for a {@code BSTree}.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  a queue object type
 * @see Comparator
 * @see Queue
 */
public class BSQueue<O> implements Comparator<O>, Queue<O>
{	
	private BSTree<O> data;
	private Comparator<O> comp;
	
	/**
	 * Creates a new {@code BSQueue}.
	 * 
	 * @param c  an object comparator
	 * 
	 * 
	 * @see Comparator
	 */
	public BSQueue(Comparator<O> c)
	{
		data = new BSTree<>(this);
		comp = c;
	}
	
	/**
	 * Creates a new {@code BSQueue}.
	 */
	public BSQueue()
	{
		this((o1, o2) ->
		{
			return ((Comparable<O>) o1).compareTo(o2);
		});
	}

	
	@Override
	public Iterator<O> iterator()
	{
		return data.iterator();
	}

	@Override
	public int compare(O o1, O o2)
	{
		if(o1.equals(o2))
		{
			return 0;
		}
		
		// Tiebreakers ordered by FIFO.
		int val = comp.compare(o1, o2);
		if(val == 0) val = 1;
		return val;
	}

	@Override
	public boolean isEmpty()
	{
		return data.isEmpty();
	}

	@Override
	public int Count()
	{
		return data.Count();
	}

	
	@Override
	public void push(O obj)
	{
		data.add(obj);
	}

	@Override
	public void clear()
	{
		data = new BSTree<>(this);
	}
	
	@Override
	public O peek()
	{
		BSNode<O> node = data.Root();
		if(node != null)
		{
			node = node.LLeaf();
			return node.Value();
		}
		
		return null;
	}

	@Override
	public O pop()
	{
		BSNode<O> node = data.Root();
		if(node != null)
		{
			node = node.LLeaf();
			node.delete();
			
			return node.Value();
		}
		
		return null;
	}
}