package waffles.utils.sets.utilities.ordered;

import waffles.utils.tools.patterns.properties.Immutable;

/**
 * An {@code Ordered} object defines an integer order.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.1
 * 
 * 
 * @see Immutable
 */
@FunctionalInterface
public interface Ordered extends Immutable
{
	/**
	 * An {@code Ordered.Mutable} can change its own integer order.
	 *
	 * @author Waffles
	 * @since May 6, 2026
	 * @version 1.1
	 *
	 * 
	 * @see Immutable
	 * @see Ordered
	 */
	public static interface Mutable extends Immutable.Mutable, Ordered
	{
		/**
		 * Changes the order of the {@code Ordered}.
		 * 
		 * @param ord  an integer order
		 */
		public abstract void setOrder(int ord);
	}
	
	
	/**
	 * Returns the order of the {@code Ordered}.
	 * 
	 * @return  an integer order
	 */
	public abstract int Order();
}