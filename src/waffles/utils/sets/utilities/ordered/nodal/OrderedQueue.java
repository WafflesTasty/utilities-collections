package waffles.utils.sets.utilities.ordered.nodal;

import waffles.utils.sets.queues.search.BSQueue;
import waffles.utils.sets.utilities.ordered.Ordered;

/**
 * An {@code OrderedQueue} defines a {@code BSQueue} for {@code Ordered} objects.
 *
 * @author Waffles
 * @since May 6, 2026
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see BSQueue
 * @see Ordered
 */
public class OrderedQueue<O extends Ordered> extends BSQueue<O>
{
	/**
	 * Creates a new {@code OrderedQueue}.
	 */
	public OrderedQueue()
	{
		super((n1, n2) -> n2.Order() - n1.Order());
	}
}