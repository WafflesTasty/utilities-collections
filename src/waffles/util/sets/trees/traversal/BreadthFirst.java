package waffles.util.sets.trees.traversal;

import java.util.Iterator;

import waffles.util.sets.queues.Deque;
import waffles.util.sets.queues.delegate.JDeque;
import waffles.util.sets.trees.Nodal;

/**
 * The {@code BreadthFirst} iterator iterates over a tree in a breadth-first manner.
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
public class BreadthFirst<N extends Nodal> implements Iterator<N>
{	
	private Deque<Nodal> queue;

	/**
	 * Creates a new {@code BreadthFirst}.
	 * 
	 * @param base  a base node
	 */
	public BreadthFirst(N base)
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
				queue.pushLast(child);
			}
		}
		
		return (N) next;
	}
}