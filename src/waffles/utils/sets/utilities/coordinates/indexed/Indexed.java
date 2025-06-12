package waffles.utils.sets.utilities.coordinates.indexed;

import waffles.utils.sets.utilities.coordinates.Coordinated;

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
	 * @param coords  index coordinates
	 */
	public abstract void moveTo(int... coords);

	/**
	 * Moves the {@code Indexed} for a coordinate.
	 * 
	 * @param coords  index coordinates
	 */
	public default void moveFor(int... coords)
	{
		int[] curr = Coordinates();
		for(int i = 0; i < Order(); i++)
		{
			curr[i] += coords[i];
		}
		
		moveTo(curr);
	}
}