package waffles.utils.sets.utilities;

/**
 * A {@code Selection} is fired when an object is picked from a set.
 *
 * @author Waffles
 * @since Jul 10, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 */
@FunctionalInterface
public interface Selection<O>
{
	/**
	 * Selects a data object.
	 * 
	 * @param dat  a data object
	 */
	public abstract void select(O dat);
}