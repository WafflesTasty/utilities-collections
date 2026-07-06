package waffles.utils.sets.arboreal.arborus.order;

import java.util.Iterator;

import waffles.utils.sets.arboreal.arborus.Arborus;
import waffles.utils.sets.arboreal.arborus.order.nodal.OrderNodal;
import waffles.utils.sets.utilities.ordered.BackwardIterator;
import waffles.utils.sets.utilities.ordered.ForwardIterator;

/**
 * An {@code OrderBoreal} defines an {@code Arborus} with manual node ordering.
 * Every {@code OrderNodal} defines its own relative order, and children of
 * the same parent node are always ordered in ascending relative order.
 *
 * @author Waffles
 * @since May 13, 2026
 * @version 1.1
 *
 *
 * @param <N>  a nodal type
 * @see OrderNodal
 * @see Arborus
 */
public interface OrderBoreal<N extends OrderNodal> extends Arborus<N>
{
	/**
	 * An {@code OrderBoreal.Query} defines tree queries for an {@code OrderBoreal}.
	 *
	 * @author Waffles
	 * @since May 13, 2026
	 * @version 1.1
	 *
	 *
	 * @param <N>  a nodal type
	 * @see OrderNodal
	 * @see Arborus
	 */
	public static interface Query<N extends OrderNodal> extends Arborus.Query<N>
	{
		/**
		 * Iterates over the order of an {@code OrderBoreal}.
		 * 
		 * @param n  a root nodal
		 * @return   a node iterator
		 * 
		 * 
		 * @see Iterator
		 */
		public default Iterator<N> Order(N n)
		{
			return new ForwardIterator<>(n);
		}
		
		/**
		 * Iterates over the reverse of an {@code OrderBoreal}.
		 * 
		 * @param n  a root nodal
		 * @return   a node iterator
		 * 
		 * 
		 * @see Iterator
		 */
		public default Iterator<N> Reverse(N n)
		{
			return new BackwardIterator<>(n);
		}
				
		
		@Override
		public abstract OrderBoreal<N> Tree();
		
		@Override
		public default Iterator<N> All()
		{
			return Order((N) Tree().Root());
		}
	}
	
	/**
	 * Iterates over nodes in decreasing order.
	 * 
	 * @return  a reverse iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<N> ReverseOrder()
	{
		return () -> Query().Reverse((N) Root());
	}
	
	
	@Override
	public default Query<N> Query()
	{
		return () -> this;
	}
}