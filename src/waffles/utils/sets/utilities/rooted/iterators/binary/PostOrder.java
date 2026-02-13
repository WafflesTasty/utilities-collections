package waffles.utils.sets.utilities.rooted.iterators.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import waffles.utils.sets.arboreal.binary.BiNodal;
import waffles.utils.sets.arboreal.binary.BiNode;

/**
 * A {@code PostOrder} iterator traverses a binary tree post-ordered.
 * 
 * @author Waffles
 * @since Jun 30, 2017
 * @version 1.0
 * 
 * 
 * @param <N>  a nodal type
 * @see Iterator
 * @see BiNode
 */
public class PostOrder<N extends BiNodal> implements Iterator<N>
{	
	private BiNodal peek;
	private BiNodal next, node;
	private Deque<BiNodal> queue;

	/**
	 * Creates a new {@code PostOrder}.
	 * 
	 * @param base  a base node
	 * 
	 * 
	 * @see BiNodal
	 */
	public PostOrder(BiNodal base)
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
		while(true)
		{
			if(node != null)
			{
				queue.push(node);
				BiNode n = node.Arch();
				node = n.LChild();
				continue;
			}
			
			peek = queue.peek().Arch().RChild();
			if(peek != null && peek != next)
			{
				node = peek;
				continue;
			}
			
			break;
		}
		
		next = queue.pop();
		return (N) next;
	}
}