package waffles.utils.sets.utilities.rooted.iterators.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import waffles.utils.sets.arboreal.binary.BiNodal;
import waffles.utils.sets.arboreal.binary.BiNode;

/**
 * A {@code InOrder} iterator traverses a binary tree in-ordered.
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
public class InOrder<N extends BiNodal> implements Iterator<N>
{
	private BiNodal node, next;
	private Deque<BiNodal> queue;

	/**
	 * Creates a new {@code InOrder}.
	 * 
	 * @param base  a base node
	 * 
	 * 
	 * @see BiNodal
	 */
	public InOrder(BiNodal base)
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
		BiNode n;
		while(node != null)
		{
			queue.push(node);
			n = node.Arch();
			node = n.LChild();
		}

		next = queue.poll();
		n = next.Arch();
		node = n.RChild();
		return (N) next;
	}
}