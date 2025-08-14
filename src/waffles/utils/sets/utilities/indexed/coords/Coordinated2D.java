package waffles.utils.sets.utilities.indexed.coords;

/**
 * A {@code Coordinated2D} object defines two-dimensional coordinates.
 *
 * @author Waffles
 * @since 12 May 2024
 * @version 1.1
 *
 * 
 * @see Coordinated
 */
public interface Coordinated2D extends Coordinated
{
	/**
	 * Returns the row of the {@code Coordinated2D}.
	 * 
	 * @return  a row index
	 */
	public default int Row()
	{
		return Coordinates()[0];
	}
	
	/**
	 * Returns the column of the {@code Coordinated2D}.
	 * 
	 * @return  a column index
	 */
	public default int Column()
	{
		return Coordinates()[1];
	}
	
	
	@Override
	public default int Order()
	{
		return 2;
	}
}