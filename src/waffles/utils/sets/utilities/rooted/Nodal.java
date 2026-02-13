package waffles.utils.sets.utilities.rooted;

/**
 * A {@code Nodal} object defines its own {@code Node}.
 * This allows parent-child relationships between similar objects.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.0
 * 
 * 
 * @see Hierarchical
 */
public interface Nodal extends Hierarchical
{	
	@Override
	public abstract Nodal Arch();
}
