package waffles.utils.sets.arboreal.arborus.order.nodal;

import waffles.utils.sets.utilities.arboreal.Nodal;

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
 * @see Nodal
 */
public interface OrderNodal extends Nodal
{
	@Override
	public abstract OrderNode Arch();
}