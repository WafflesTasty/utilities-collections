package waffles.utils.sets.utilities.rooted.iterators;

import java.util.Iterator;

import waffles.utils.sets.queues.Deque;
import waffles.utils.sets.queues.wrapper.JavaDeque;
import waffles.utils.sets.utilities.rooted.Nodal;

/**
 * A {@code DepthFirst} iterator traverses a tree in a depth-first manner.
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
		queue = new JavaDeque<>();
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
		for(Nodal c : next.Arch().Children())
		{
			if(c != null)
			{
				queue.pushFirst(c);
			}
		}
		
		return (N) next;
	}
}