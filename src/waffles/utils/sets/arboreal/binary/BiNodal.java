package waffles.utils.sets.arboreal.binary;

import waffles.utils.sets.utilities.rooted.Nodal;

/**
 * A {@code BiNodal} object defines its own {@code BiNode}.
 * This allows binary child relationships amongst similar types.
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
