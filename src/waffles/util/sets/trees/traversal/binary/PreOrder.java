package waffles.util.sets.trees.traversal.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import waffles.util.sets.trees.binary.BiNodal;
import waffles.util.sets.trees.binary.BiNode;

/**
 * The {@code PreOrder} class defines a pre-order iterator for a binary tree.
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
public class PreOrder<N extends BiNodal> implements Iterator<N>
{	
	private BiNodal next, nodal;
	private Deque<BiNodal> queue;

	/**
	 * Creates a new {@code PreOrder}.
	 * 
	 * @param base  a base node
	 * 
	 * 
	 * @see BiNodal
	 */
	public PreOrder(BiNodal base)
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
		if(nodal == null)
		{
			nodal = queue.pop();
		}
		
		next = nodal;
		BiNode node = nodal.Arch();
		if(node.RChild() != null)
		{
			queue.push(node.RChild());
		}
		
		nodal = node.LChild();
		return (N) next;
	}
}