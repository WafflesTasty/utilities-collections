package waffles.utils.sets.utilities.coordinates;

/**
 * A {@code Coordinated} object defines its own {@link #Coordinates()}.
 *
 * @author Waffles
 * @since 12 May 2024
 * @version 1.1
 *
 * 
 * @see Ordered
 */
public interface Coordinated extends Ordered
{
	/**
	 * Returns the coordinates of the {@code Coordinated}.
	 * 
	 * @return  a coordinate set
	 */
	public abstract int[] Coordinates();
	
	
	@Override
	public default int Order()
	{
		return Coordinates().length;
	}
}