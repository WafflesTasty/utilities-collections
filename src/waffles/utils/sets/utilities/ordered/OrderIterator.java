package waffles.utils.sets.utilities.ordered;

import java.util.Iterator;

import waffles.utils.sets.arboreal.arborus.order.nodal.OrderNodal;
import waffles.utils.sets.utilities.arboreal.Nodal;
import waffles.utils.sets.utilities.arboreal.Node;
import waffles.utils.sets.utilities.ordered.OrderQueue.Route;
import waffles.utils.tools.collections.iterators.SingleIterator;

/**
 * An {@code OrderIterator} iterates over an {@code OrderBoreal} ascending.
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
public class OrderIterator<N extends OrderNodal> implements Iterator<N>
{
	private Iterator<N> set;
	private OrderQueue<N> queue;
	
	/**
	 * Creates a new {@code OrderIterator}.
	 * 
	 * @param root  a root nodal
	 * @param r   an order route
	 * 
	 * 
	 * @see Route
	 */
	public OrderIterator(N root, Route r)
	{
		Node node = root.Arch();
		queue = new OrderQueue<>(r);
		for(Nodal c : node.Children())
		{
			N child = (N) c;
			if(child.isVisible())
			{
				queue.push(child);
			}
		}
		
		set = new SingleIterator<>(root);
	}
	
	/**
	 * Creates a new {@code OrderIterator}.
	 * 
	 * @param root  a root nodal
	 */
	public OrderIterator(N root)
	{			
		this(root, Route.INCREASING);
	}

	
	@Override
	public boolean hasNext()
	{
		return set.hasNext();
	}

	@Override
	public N next()
	{
		N curr = set.next();
		if(!set.hasNext())
		{
			if(!queue.isEmpty())
			{
				N next = queue.pop();
				set = new OrderIterator<>(next);
			}
		}
		
		return curr;
	}
}