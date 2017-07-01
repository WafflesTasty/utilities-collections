package zeno.util.coll.tools.iterators.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import zeno.util.coll.trees.BiNode;
import zeno.util.coll.trees.BiTree;

/**
 * The {@code PostOrder} class defines a post-order iterator for a {@link BiTree}.
 * 
 * @since Jun 30, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects in the tree
 * @see Iterator
 * @see BiNode
 */
public class PostOrder<O> implements Iterator<BiNode<O>>
{	
	private BiNode<O> peek;
	private BiNode<O> next, node;
	private Deque<BiNode<O>> queue;

	/**
	 * Creates a new {@code PostOrder}.
	 * 
	 * @param tree  a tree to iterate
	 * @see BiTree
	 */
	public PostOrder(BiTree<O> tree)
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
		while(true)
		{
			if(node != null)
			{
				queue.push(node);
				peek = node.RChild();
				node = node.LChild();
				continue;
			}
			
			if(peek != null && peek != next)
			{
				node = peek;
				continue;
			}
			
			break;
		}
		
		next = queue.pop();
		return next;
	}
}