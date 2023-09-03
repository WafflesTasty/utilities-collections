package waffles.utils.sets.utilities.collectables;

/**
 * A {@code Hierarchical} object can define parent-child relationships amongst similar types.
 *
 * @author Waffles
 * @since Mar 5, 2016
 * @version 1.0
 */
@FunctionalInterface
public interface Hierarchical
{		
	/**
	 * Returns the {@code Hierarchy} node of the object.
	 * 
	 * @return  a hierarchy node
	 * 
	 * 
	 * @see Hierarchy
	 */
	public abstract Hierarchy Arch();
}