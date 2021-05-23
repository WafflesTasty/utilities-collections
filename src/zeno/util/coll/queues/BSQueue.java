package zeno.util.coll.queues;

import java.util.Comparator;
import java.util.Iterator;

import zeno.util.coll.Queue;
import zeno.util.coll.trees.binary.BSNode;
import zeno.util.coll.trees.binary.BSTree;

/**
 * The {@code BSQueue} class defines an ordered queue as a {@code BSTree} wrapper.
 *
 * @author Zeno
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Queue
 */
public class BSQueue<O> implements Queue<O>, Comparator<O>
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
			
			data.onDelete(node);
			return node.Value();
		}
		
		return null;
	}
}