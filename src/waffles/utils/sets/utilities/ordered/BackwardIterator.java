package waffles.utils.sets.utilities.ordered;

import java.util.Iterator;

import waffles.utils.sets.arboreal.arborus.order.nodal.OrderNodal;
import waffles.utils.sets.utilities.arboreal.Nodal;
import waffles.utils.sets.utilities.arboreal.Node;
import waffles.utils.sets.utilities.ordered.OrderQueue.Route;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code BackwardIterator} iterates over an {@code OrderNodal} in descending order.
 *
 * @author Waffles
 * @since May 6, 2026
 * @version 1.1
 *
 * 
 * @param <N>  a nodal type
 * @see OrderNodal
 * @see Iterator
 */
public class BackwardIterator<N extends OrderNodal> implements Iterator<N>
{
	private N last, next;
	private Iterator<N> set;
	private OrderQueue<N> queue;
	
	/**
	 * Creates a new {@code BackwardIterator}.
	 * 
	 * @param root  a root nodal
	 * 
	 * 
	 * @see Route
	 */
	public BackwardIterator(N root)
	{
		last = root;
		Node node = root.Arch();
		queue = new OrderQueue<>(Route.DECREASING);
		for(Nodal c : node.Children())
		{
			N child = (N) c;
			if(child.isVisible())
			{
				queue.push(child);
			}
		}

		set = new EmptyIterator<>();
		next = findNext();
	}

	
	private N findNext()
	{
		if(set.hasNext())
		{
			return set.next();
		}
		
		if(!queue.isEmpty())
		{
			N node = queue.pop();
			set = new BackwardIterator<>(node);
			return findNext();
		}
		
		if(next != last)
		{
			next = last;
			return next;
		}
		
		return null;
	}
	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public N next()
	{
		N curr = next;
		next = findNext();
		return curr;
	}
}