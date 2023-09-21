package waffles.utils.sets.utilities.iterators;

import java.util.Iterator;

import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.delegate.JFIFOQueue;
import waffles.utils.sets.trees.indexed.BEPNode;
import waffles.utils.sets.trees.indexed.BEPTree;

/**
 * A {@code BEPNodes} iterator iterates over all nodes in a {@code BEPTree} of the same value.
 *
 * @author Waffles
 * @since 21 Sep 2023
 * @version 1.1
 *
 *
 * @param <N>  a node type
 * @see Iterator
 * @see BEPNode
 */
public class BEPNodes<N extends BEPNode<?>> implements Iterator<N>
{
	private Enum<?> tgt;
	private Queue<BEPNode<?>> nodes;
	
	/**
	 * Creates a new {@code BEPNodes}.
	 * 
	 * @param s  a source tree
	 * @param t  a target value
	 * 
	 * 
	 * @see BEPTree
	 */
	public BEPNodes(BEPTree<?> s, Enum<?> t)
	{
		tgt = t;
		
		nodes = new JFIFOQueue<>();
		if(s.Root().hasValue(t))
		{
			nodes.push(s.Root());
		}
	}
	
	
	@Override
	public boolean hasNext()
	{
		return !nodes.isEmpty();
	}

	@Override
	public N next()
	{
		BEPNode<?> next = nodes.pop();
		if(!next.isLeaf())
		{
			BEPNode<?> lchild = next.LChild();
			if(lchild.hasValue(tgt))
			{
				nodes.push(lchild);
			}
			
			BEPNode<?> rchild = next.RChild();
			if(rchild.hasValue(tgt))
			{
				nodes.push(rchild);
			}
		}
		
		return (N) next;
	}
}