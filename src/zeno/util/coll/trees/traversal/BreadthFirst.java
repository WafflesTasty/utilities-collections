package zeno.util.coll.trees.traversal;

import java.util.Iterator;

import zeno.util.coll.Queue;
import zeno.util.coll.utilities.relations.nodes.INode;

/**
 * The {@code BreadthFirst} class iterates over a graph breadth-first.
 *
 * @author Zeno
 * @since 29 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <N>  a node type
 * @see Iterator
 * @see INode
 */
public class BreadthFirst<N extends INode> implements Iterator<N>
{	
	private Queue<INode> queue;

	/**
	 * Creates a new {@code BreadthFirst}.
	 * 
	 * @param base  a base node
	 */
	public BreadthFirst(N base)
	{
		queue = new Queue<>();
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
		INode next = queue.popFirst();
		for(INode child : next.Relations().Children())
		{
			queue.pushLast(child);
		}
		
		return (N) next;
	}
}