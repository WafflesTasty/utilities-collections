package waffles.utils.sets.utilities.ordered.nodal;

import java.util.Iterator;

import waffles.utils.sets.utilities.ordered.Ordered;
import waffles.utils.sets.utilities.rooted.Nodal;
import waffles.utils.sets.utilities.rooted.Node;
import waffles.utils.tools.collections.iterators.SingleIterator;

/**
 * An {@code OrderedNodal} defines a {@code Nodal} with an integer order.
 * This alway the {@code Nodal} to be traversed in a breadth-first manner
 * such that the children of each node are traversed in increasing order.
 *
 * @author Waffles
 * @since May 6, 2026
 * @version 1.1
 *
 * 
 * @see Ordered
 * @see Nodal
 */
public interface OrderedNodal extends Ordered, Nodal
{
	/**
	 * An {@code Order} defines an ordered iterator for an {@code OrderedNodal}.
	 *
	 * @author Waffles
	 * @since May 6, 2026
	 * @version 1.1
	 *
	 * 
	 * @param <N>  a nodal type
	 * @see OrderedNodal
	 * @see Iterator
	 */
	public static class Order<N extends OrderedNodal> implements Iterator<N>
	{
		private Iterator<N> set;
		private OrderedQueue<N> queue;
		
		/**
		 * Creates a new {@code Order}.
		 * 
		 * @param root  a root node
		 */
		public Order(N root)
		{			
			Node node = root.Arch();
			queue = new OrderedQueue<>();
			for(Nodal c : node.Children())
			{
				queue.push((N) c);
			}
			
			set = new SingleIterator<>(root);
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
					N node = queue.pop();
					set = new Order<>(node);
				}
			}
			
			return curr;
		}
	}

	/**
	 * Iterates over the nodes in the {@code OrderedNodal} in increasing order.
	 * 
	 * @param <N>  a nodal type
	 * @return  a node iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default <N extends OrderedNodal> Iterable<N> OrderedNodes()
	{
		return () -> new Order<>((N) this);
	}
}