package waffles.utils.sets.arboreal.arborus.order.nodal;

import waffles.utils.sets.utilities.Ordered;
import waffles.utils.sets.utilities.arboreal.Node;

/**
 * An {@code OrderNode} defines a basic node for an {@code OrderNodal}.
 *
 * @author Waffles
 * @since May 6, 2026
 * @version 1.1
 *
 * 
 * @see OrderNodal
 * @see Ordered
 */
public class OrderNode extends Node implements OrderNodal, Ordered.Mutable
{
	private int order;
	
	/**
	 * Creates a new {@code OrderNode}.
	 * 
	 * @param n  a source nodal
	 * 
	 * 
	 * @see OrderNodal
	 */
	public OrderNode(OrderNodal n)
	{
		super(n);
	}	
	
	/**
	 * Creates a new {@code OrderNode}.
	 */
	public OrderNode()
	{
		order = 0;
	}

	
	@Override
	public OrderNode Arch()
	{
		return this;
	}
	
	@Override
	public void setOrder(int ord)
	{
		order = ord;
	}
	
	@Override
	public int Order()
	{
		return order;
	}
}