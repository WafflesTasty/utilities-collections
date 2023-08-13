package waffles.util.sets.trees.binary;

import waffles.util.sets.trees.Nodal;

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
