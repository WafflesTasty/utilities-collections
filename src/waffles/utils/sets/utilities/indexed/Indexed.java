package waffles.utils.sets.utilities.indexed;

import waffles.utils.sets.utilities.indexed.coords.Coordinated;

/**
 * An {@code Indexed} object can position itself in an n-dimensional index.
 *
 * @author Waffles
 * @since 12 Jun 2025
 * @version 1.1
 *
 * 
 * @see Coordinated
 */
public interface Indexed extends Coordinated
{
	/**
	 * Moves the {@code Indexed} to a new coordinate.
	 * 
	 * @param crds  an index coordinate
	 */
	public abstract void moveTo(int... crds);

	/**
	 * Moves the {@code Indexed} for a coordinate.
	 * 
	 * @param crds  an index coordinate
	 */
	public default void moveFor(int... crds)
	{
		int[] curr = Coordinates();
		for(int i = 0; i < Order(); i++)
		{
			curr[i] += crds[i];
		}
		
		moveTo(curr);
	}
}