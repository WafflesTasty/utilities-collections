package waffles.utils.sets.utilities.coordinates;

/**
 * An {@code Ordered} interface defines an object with an order.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.1
 */
@FunctionalInterface
public interface Ordered
{
	/**
	 * Returns the order of the object.
	 * 
	 * @return  an integer order
	 */
	public abstract int Order();
}