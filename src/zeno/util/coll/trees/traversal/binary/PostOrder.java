package zeno.util.coll.trees.traversal.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import zeno.util.coll.trees.binary.BiNode;

/**
 * The {@code PostOrder} class defines a post-order iterator for a binary tree.
 * 
 * @author Zeno
 * @since Jun 30, 2017
 * @version 1.0
 * 
 * 
 * @param <B>  a node type
 * @see Iterator
 * @see BiNode
 */
public class PostOrder<B extends BiNode> implements Iterator<B>
{	
	private BiNode peek;
	private BiNode next, node;
	private Deque<BiNode> queue;

	/**
	 * Creates a new {@code PostOrder}.
	 * 
	 * @param base  a base node
	 * 
	 * 
	 * @see BiNode
	 */
	public PostOrder(BiNode base)
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
	public B next()
	{
		while(true)
		{
			if(node != null)
			{
				queue.push(node);	
				node = node.LChild();
				continue;
			}
			
			peek = queue.peek().RChild();
			if(peek != null && peek != next)
			{
				node = peek;
				continue;
			}
			
			break;
		}
		
		next = queue.pop();
		return (B) next;
	}
}