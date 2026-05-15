package waffles.utils.sets.utilities.ordered;

import waffles.utils.sets.arboreal.arborus.order.nodal.OrderNodal;
import waffles.utils.sets.queues.search.BSQueue;

/**
 * An {@code OrderQueue} defines a {@code BSQueue} for {@code Ordered} objects.
 *
 * @author Waffles
 * @since May 6, 2026
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see OrderNodal
 * @see BSQueue
 */
public class OrderQueue<O extends OrderNodal> extends BSQueue<O>
{
	/**
	 * Creates a new {@code OrderQueue}.
	 */
	public OrderQueue()
	{
		super((n1, n2) -> 
		{
			int o1 = n1.Arch().Order();
			int o2 = n2.Arch().Order();
			int od = o1 - o2;
			
			if(od == 0)
			{
				int i1 = n1.Arch().TreeIndex();
				int i2 = n2.Arch().TreeIndex();
				
				return i1 - i2;
			}
			
			return od;
		});
	}
}