package waffles.utils.sets.utilities.rooted.iterators.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import waffles.utils.sets.arboreal.binary.BiNodal;
import waffles.utils.sets.arboreal.binary.BiNode;

/**
 * A {@code PreOrder} iterator traverses a binary tree pre-ordered.
 * 
 * @author Waffles
 * @since Jun 30, 2017
 * @version 1.0
 * 
 *
 * @param <N>  a nodal type
 * @see Iterator
 * @see BiNodal
 */
public class PreOrder<N extends BiNodal> implements Iterator<N>
{	
	private BiNodal next, node;
	private Deque<BiNodal> queue;

	/**
	 * Creates a new {@code PreOrder}.
	 * 
	 * @param base  a base node
	 * 
	 * 
	 * @see BiNodal
	 */
	public PreOrder(BiNodal base)
	{
		queue = new ArrayDeque<>();
		node = base;
	}

	
	@Override
	public boolean hasNext()
	{
		return !(queue.isEmpty() && node == null);
	}

	@Override
	public N next()
	{
		if(node == null)
		{
			node = queue.pop();
		}
		
		next = node;
		BiNode n = node.Arch();
		if(n.RChild() != null)
		{
			queue.push(n.RChild());
		}
		
		node = n.LChild();
		return (N) next;
	}
}