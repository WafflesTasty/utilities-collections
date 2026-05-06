package waffles.utils.sets.utilities.indexed.coords;

import waffles.utils.sets.utilities.ordered.Ordered;

/**
 * A {@code Coordinated} object defines its own {@link #Coords()}.
 *
 * @author Waffles
 * @since 12 May 2024
 * @version 1.1
 *
 * 
 * @see Ordered
 */
@FunctionalInterface
public interface Coordinated extends Ordered
{
	/**
	 * Returns the coordinates of the {@code Coordinated}.
	 * 
	 * @return  a coordinate set
	 */
	public abstract int[] Coords();
	
	
	@Override
	public default int Order()
	{
		return Coords().length;
	}
}