package waffles.utils.sets.utilities.ordered;

/**
 * An {@code Orderable} object defines its own {@code Order}.
 *
 * @author Waffles
 * @since May 6, 2026
 * @version 1.1
 */
@FunctionalInterface
public interface Orderable
{
	/**
	 * Returns the order of the {@code Orderable}.
	 * 
	 * @return  an integer order
	 * 
	 * 
	 * @see Ordered
	 */
	public abstract Ordered Order();
}