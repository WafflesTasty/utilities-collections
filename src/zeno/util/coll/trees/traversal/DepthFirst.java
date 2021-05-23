package zeno.util.coll.trees.traversal;

import java.util.Iterator;

import zeno.util.coll.DEQueue;
import zeno.util.coll.queues.LLQueue;
import zeno.util.coll.utilities.relations.nodes.INode;

/**
 * The {@code DepthFirst} class iterates over a graph depth-first.
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
public class DepthFirst<N extends INode> implements Iterator<N>
{	
	private DEQueue<INode> queue;

	/**
	 * Creates a new {@code DepthFirst}.
	 * 
	 * @param base  a base node
	 */
	public DepthFirst(N base)
	{
		queue = new LLQueue<>();
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
			if(child != null)
			{
				queue.pushFirst(child);
			}
		}
		
		return (N) next;
	}
}