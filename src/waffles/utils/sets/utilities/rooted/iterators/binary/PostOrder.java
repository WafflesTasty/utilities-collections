package waffles.utils.sets.utilities.rooted.iterators.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import waffles.utils.sets.rooted.binary.BiNodal;
import waffles.utils.sets.rooted.binary.BiNode;

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
	private BiNodal next, nodal;
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
		while(true)
		{
			if(nodal != null)
			{
				queue.push(nodal);
				BiNode node = nodal.Arch();
				nodal = node.LChild();
				continue;
			}
			
			peek = queue.peek().Arch().RChild();
			if(peek != null && peek != next)
			{
				nodal = peek;
				continue;
			}
			
			break;
		}
		
		next = queue.pop();
		return (N) next;
	}
}