package waffles.utils.sets.utilities;

/**
 * An {@code Ordered} object defines an integer order.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.1
 */
@FunctionalInterface
public interface Ordered
{
	/**
	 * Returns the order of the {@code Ordered}.
	 * 
	 * @return  an integer order
	 */
	public abstract int Order();
}