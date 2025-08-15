package waffles.utils.sets.queues.search;

import java.util.Comparator;
import java.util.Iterator;

import waffles.utils.sets._old.trees.binary.search.BSNode;
import waffles.utils.sets._old.trees.binary.search.BSTree;
import waffles.utils.sets.queues.Queue;

/**
 * A {@code BSQueue} defines a sorted queue backed by a {@code BSTree}.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
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
	public void remove(O obj)
	{
		data.remove(obj);
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