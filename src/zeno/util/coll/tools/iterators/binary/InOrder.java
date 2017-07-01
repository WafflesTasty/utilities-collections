package zeno.util.coll.tools.iterators.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import zeno.util.coll.trees.BiNode;
import zeno.util.coll.trees.BiTree;

/**
 * The {@code InOrder} class defines an in-order iterator for a {@link BiTree}.
 * 
 * @since Jun 30, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects in the tree
 * @see Iterator
 * @see BiNode
 */
public class InOrder<O> implements Iterator<BiNode<O>>
{
	private BiNode<O> next, node;
	private Deque<BiNode<O>> queue;

	/**
	 * Creates a new {@code InOrder}.
	 * 
	 * @param tree  a tree to iterate
	 * @see BiTree
	 */
	public InOrder(BiTree<O> tree)
	{
		queue = new ArrayDeque<>();
		node = tree.Root();
	}
	
	
	@Override
	public boolean hasNext()
	{
		return !(queue.isEmpty() && node == null);
	}
	
	@Override
	public BiNode<O> next()
	{
		while(node != null)
		{
			queue.push(node);
			node = node.LChild();
		}

		next = queue.pop();
		node = next.RChild();	
		return next;
	}
}