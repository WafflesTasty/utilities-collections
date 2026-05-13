package waffles.utils.sets.utilities.indexed.coords;

import waffles.utils.sets.utilities.Ordered;

/**
 * A {@code Coordinator} defines an object with a multi-valued dimension.
 *
 * @author Waffles
 * @since 12 May 2024
 * @version 1.1
 *
 * 
 * @see Ordered
 */
public interface Coordinator extends Ordered
{
	/**
	 * Returns the dimensions of the {@code Coordinator}.
	 * 
	 * @return  a dimension array
	 */
	public abstract int[] Dimensions();
	
	/**
	 * Returns the order of the {@code Coordinator}.
	 * The order represents how many indices are needed
	 * to define a single value in its dimensions.
	 * 
	 * @return  a dimension order
	 */
	@Override
	public default int Order()
	{
		return Dimensions().length;
	}
}