package waffles.util.sets.trees;

import waffles.util.sets.utilities.collectables.Hierarchical;

/**
 * A {@code Nodal} object can define tree-node relationships amongst similar types.
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
	public abstract Node Arch();
}
