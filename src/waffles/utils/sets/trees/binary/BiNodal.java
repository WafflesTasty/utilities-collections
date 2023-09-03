package waffles.utils.sets.trees.binary;

import waffles.utils.sets.trees.Nodal;

/**
 * A {@code BiNodal} object can define binary tree relationships amongst similar types.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.0
 * 
 * 
 * @see Nodal
 */
public interface BiNodal extends Nodal
{	
	@Override
	public abstract BiNode Arch();
}
