package waffles.utils.sets.utilities.coordinates.indexed;

import waffles.utils.sets.utilities.coordinates.Coordinated2D;

/**
 * An {@code Indexed2D} object can position itself in a two-dimensional index.
 *
 * @author Waffles
 * @since 12 Jun 2025
 * @version 1.1
 *
 * 
 * @see Coordinated2D
 * @see Indexed
 */
public interface Indexed2D extends Indexed, Coordinated2D
{
	/**
	 * Moves the {@code Indexed2D} to a new coordinate.
	 * 
	 * @param r  an index row
	 * @param c  an index column
	 */
	public default void moveTo(int r, int c)
	{
		moveTo(new int[]{r, c});
	}

	/**
	 * Moves the {@code Indexed} for a coordinate.
	 * 
	 * @param r  an index row
	 * @param c  an index column
	 */
	public default void moveFor(int r, int c)
	{
		moveTo(r + Row(), c + Column());
	}
}