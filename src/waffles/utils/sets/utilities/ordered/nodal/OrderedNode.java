package waffles.utils.sets.utilities.ordered.nodal;

import waffles.utils.sets.utilities.arboreal.Node;
import waffles.utils.sets.utilities.ordered.Ordered;

/**
 * An {@code OrderedNode} defines a basic node for an {@code OrderedNodal}.
 *
 * @author Waffles
 * @since May 6, 2026
 * @version 1.1
 *
 * 
 * @see OrderedNodal
 * @see Ordered
 */
public class OrderedNode extends Node implements OrderedNodal, Ordered.Mutable
{
	private int order;
	
	/**
	 * Creates a new {@code OrderedNode}.
	 * 
	 * @param n  a source nodal
	 * 
	 * 
	 * @see OrderedNodal
	 */
	public OrderedNode(OrderedNodal n)
	{
		super(n);
	}	
	
	/**
	 * Creates a new {@code OrderedNode}.
	 */
	public OrderedNode()
	{
		order = 0;
	}

	
	@Override
	public OrderedNode Arch()
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