package waffles.util.sets.trees.traversal.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import waffles.util.sets.trees.binary.BiNodal;
import waffles.util.sets.trees.binary.BiNode;

/**
 * The {@code InOrder} class defines an in-order iterator for a binary tree.
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
	private BiNodal nodal, next;
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
		nodal = base;
	}


	@Override
	public boolean hasNext()
	{
		return !(queue.isEmpty() && nodal == null);
	}
	
	@Override
	public N next()
	{
		while(nodal != null)
		{
			queue.push(nodal);
			BiNode node = nodal.Arch();
			nodal = node.LChild();
		}

		next = queue.poll();
		BiNode node = next.Arch();
		nodal = node.RChild();
		return (N) next;
	}
}