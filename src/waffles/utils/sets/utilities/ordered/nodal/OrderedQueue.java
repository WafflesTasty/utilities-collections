package waffles.utils.sets.utilities.ordered.nodal;

import waffles.utils.sets.queues.search.BSQueue;

/**
 * An {@code OrderedQueue} defines a {@code BSQueue} for {@code Ordered} objects.
 *
 * @author Waffles
 * @since May 6, 2026
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see OrderedNodal
 * @see BSQueue
 */
public class OrderedQueue<O extends OrderedNodal> extends BSQueue<O>
{
	/**
	 * Creates a new {@code OrderedQueue}.
	 */
	public OrderedQueue()
	{
		super((n1, n2) -> 
		{
			int o1 = n1.Arch().Order();
			int o2 = n2.Arch().Order();
			int od = o2 - o1;
			
			if(od == 0)
			{
				int i1 = n1.Arch().TreeIndex();
				int i2 = n2.Arch().TreeIndex();
				
				return i2 - i1;
			}
			
			return od;
		});
	}
}