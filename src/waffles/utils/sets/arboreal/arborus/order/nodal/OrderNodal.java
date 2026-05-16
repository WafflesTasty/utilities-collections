package waffles.utils.sets.arboreal.arborus.order.nodal;

import waffles.utils.sets.utilities.Ordered;
import waffles.utils.sets.utilities.arboreal.Nodal;
import waffles.utils.tools.patterns.properties.checks.Visibility;

/**
 * An {@code OrderNodal} defines a {@code Nodal} with an integer order.
 * This alway the {@code Nodal} to be traversed in a breadth-first manner
 * such that the children of each node are traversed in increasing order.
 *
 * @author Waffles
 * @since May 6, 2026
 * @version 1.1
 *
 * 
 * @see Visibility
 * @see Nodal
 */
public interface OrderNodal extends Nodal, Ordered.Mutable, Visibility
{
	@Override
	public abstract OrderNode Arch();
	
	@Override
	public default void setOrder(int ord)
	{
		Arch().setOrder(ord);
	}
	
	@Override
	public default int Order()
	{
		return Arch().Order();
	}
}