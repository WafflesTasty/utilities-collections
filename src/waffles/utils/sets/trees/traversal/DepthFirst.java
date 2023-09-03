package waffles.utils.sets.trees.traversal;

import java.util.Iterator;

import waffles.utils.sets.queues.Deque;
import waffles.utils.sets.queues.delegate.JDeque;
import waffles.utils.sets.trees.Nodal;

/**
 * The {@code DepthFirst} class iterates over a tree in a depth-first manner.
 *
 * @author Waffles
 * @since 29 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <N>  a node type
 * @see Iterator
 * @see Nodal
 */
public class DepthFirst<N extends Nodal> implements Iterator<N>
{	
	private Deque<Nodal> queue;

	/**
	 * Creates a new {@code DepthFirst}.
	 * 
	 * @param base  a base node
	 */
	public DepthFirst(N base)
	{
		queue = new JDeque<>();
		queue.pushFirst(base);
	}

	
	@Override
	public boolean hasNext()
	{
		return !queue.isEmpty();
	}

	@Override
	public N next()
	{
		Nodal next = queue.popFirst();
		for(Nodal child : next.Arch().Children())
		{
			if(child != null)
			{
				queue.pushFirst(child);
			}
		}
		
		return (N) next;
	}
}