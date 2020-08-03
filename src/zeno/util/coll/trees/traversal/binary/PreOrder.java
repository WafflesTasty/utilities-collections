package zeno.util.coll.trees.traversal.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import zeno.util.coll.trees.binary.BiNode;

/**
 * The {@code PreOrder} class defines a pre-order iterator for a binary tree.
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
public class PreOrder<B extends BiNode> implements Iterator<B>
{	
	private BiNode next, node;
	private Deque<BiNode> queue;

	/**
	 * Creates a new {@code PreOrder}.
	 * 
	 * @param base  a base node
	 * 
	 * 
	 * @see BiNode
	 */
	public PreOrder(BiNode base)
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
		if(node == null)
		{
			node = queue.pop();
		}
		
		next = node;
		if(node.RChild() != null)
		{
			queue.push(node.RChild());
		}
		
		node = node.LChild();
		return (B) next;
	}
}