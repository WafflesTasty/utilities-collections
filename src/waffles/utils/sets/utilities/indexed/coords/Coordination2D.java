package waffles.utils.sets.utilities.indexed.coords;

/**
 * A {@code Coordination2D} represents a two-dimensional chunk of an {@code IndexedSet}.
 * 
 * @author Waffles
 * @since 13 Feb 2026
 * @version 1.1
 *
 * 
 * @see Coordinator
 */
public interface Coordination2D extends Coordination
{
	/**
	 * Returns the {@code Coordination2D} row count.
	 *
	 * @return  a row count
	 */
	public default int Rows()
	{
		return Dimensions()[0];
	}
	
	/**
	 * Returns the {@code Coordination2D} column count.
	 *
	 * @return  a column count
	 */
	public default int Columns()
	{
		return Dimensions()[1];
	}

	
	@Override
	public default int Order()
	{
		return 2;
	}
}