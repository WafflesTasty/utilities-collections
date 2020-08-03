package zeno.util.coll.trees.traversal.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import zeno.util.coll.trees.BiNode;

/**
 * The {@code InOrder} class defines an in-order iterator for a binary tree.
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
public class InOrder<B extends BiNode> implements Iterator<B>
{
	private BiNode node, next;
	private Deque<BiNode> queue;

	/**
	 * Creates a new {@code InOrder}.
	 * 
	 * @param base  a base node
	 * 
	 * 
	 * @see BiNode
	 */
	public InOrder(BiNode base)
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
		while(node != null)
		{
			queue.push(node);
			node = node.LChild();
		}

		next = queue.poll();
		node = next.RChild();
		return (B) next;
	}
}