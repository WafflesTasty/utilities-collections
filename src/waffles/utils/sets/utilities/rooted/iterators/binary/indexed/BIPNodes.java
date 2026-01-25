package waffles.utils.sets.utilities.rooted.iterators.binary.indexed;

import java.util.Iterator;

import waffles.utils.sets.arboreal.binary.indexed.BIPNode;
import waffles.utils.sets.arboreal.binary.indexed.BIPTree;
import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.wrapper.FIFOQueue;

/**
 * A {@code BIPNodes} iterates over a subsection of a {@code BIPTree}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 * 
 * 
 * @param <N>  a node type
 * @see Iterator
 * @see BIPNode
 */
public class BIPNodes<N extends BIPNode> implements Iterator<N>
{
	private int[] min, max;
	private Queue<BIPNode> queue;
	
	/**
	 * Creates a new {@code BIPNodes}.
	 * 
	 * @param t    a source tree
	 * @param min  a minimum index
	 * @param max  a maximum index
	 * 
	 * 
	 * @see BIPTree
	 */
	public BIPNodes(BIPTree<?> t, int[] min, int[] max)
	{
		queue = new FIFOQueue<>();
		queue.push(t.Root());
		
		this.min = min;
		this.max = max;
	}
	
	
	@Override
	public boolean hasNext()
	{
		return !queue.isEmpty();
	}

	@Override
	public N next()
	{
		BIPNode curr = queue.pop();
		if(!curr.isLeaf())
		{
			int sDim = curr.DimSplit();
			BIPNode lchild = curr.LChild();
			BIPNode rchild = curr.RChild();
			
			if(min[sDim] < rchild.Minimum()[sDim])
				queue.push(lchild);
			if(max[sDim] > lchild.Maximum()[sDim])
				queue.push(rchild);
		}

		return (N) curr;
	}
}