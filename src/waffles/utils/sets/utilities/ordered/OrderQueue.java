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
	 * A {@code Route} defines an {@code OrderQueue} direction.
	 *
	 * @author Waffles
	 * @since Jul 6, 2026
	 * @version 1.1
	 */
	public static enum Route
	{
		/**
		 * Route in decreasing order.
		 */
		DECREASING(-1),
		/**
		 * Route in increasing order.
		 */
		INCREASING(+1);
		
		
		private int sign;
		
		private Route(int s)
		{
			sign = s;
		}
		
		/**
		 * Returns a {@code Route} sign.
		 * 
		 * @return  a sign
		 */
		public int Sign()
		{
			return sign;
		}
	}
	
		
	/**
	 * Creates a new {@code OrderQueue}.
	 * 
	 * @param r  an order route
	 * 
	 * 
	 * @see Route
	 */
	public OrderQueue(Route r)
	{
		super((n1, n2) -> 
		{
			int o1 = n1.Arch().Order();
			int o2 = n2.Arch().Order();
			
			int od = r.Sign() * (o1 - o2);
			if(od == 0)
			{
				int i1 = n1.Arch().TreeIndex();
				int i2 = n2.Arch().TreeIndex();
				
				return r.Sign() * (i1 - i2);
			}
			
			return od;
		});
	}
	
	/**
	 * Creates a new {@code OrderQueue}.
	 */
	public OrderQueue()
	{
		this(Route.INCREASING);
	}
}