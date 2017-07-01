package zeno.util.coll.tools.iterators.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import zeno.util.coll.trees.BiNode;
import zeno.util.coll.trees.BiTree;

/**
 * The {@code PreOrder} class defines a pre-order iterator for a {@link BiTree}.
 * 
 * @since Jun 30, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects in the tree
 * @see Iterator
 * @see BiNode
 */
public class PreOrder<O> implements Iterator<BiNode<O>>
{	
	private BiNode<O> next, node;
	private Deque<BiNode<O>> queue;

	/**
	 * Creates a new {@code PreOrder}.
	 * 
	 * @param tree  a tree to iterate
	 * @see BiTree
	 */
	public PreOrder(BiTree<O> tree)
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
		return next;
	}
}