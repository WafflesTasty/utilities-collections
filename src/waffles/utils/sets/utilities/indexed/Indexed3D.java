package waffles.utils.sets.utilities.indexed;

import waffles.utils.sets.utilities.indexed.coords.Coordinated3D;

/**
 * An {@code Indexed3D} object can position itself in a three-dimensional index.
 *
 * @author Waffles
 * @since 12 Jun 2025
 * @version 1.1
 *
 * 
 * @see Coordinated3D
 * @see Indexed
 */
public interface Indexed3D extends Indexed, Coordinated3D
{
	/**
	 * Moves the {@code Indexed3D} to a new coordinate.
	 * 
	 * @param r  an index row
	 * @param c  an index column
	 * @param a  an index aisle
	 */
	public default void moveTo(int r, int c, int a)
	{
		moveTo(new int[]{r, c, a});
	}

	/**
	 * Moves the {@code Indexed3D} for a coordinate.
	 * 
	 * @param r  an index row
	 * @param c  an index column
	 * @param a  an index aisle
	 */
	public default void moveFor(int r, int c, int a)
	{
		moveTo(r + Row(), c + Column(), a + Aisle());
	}
}