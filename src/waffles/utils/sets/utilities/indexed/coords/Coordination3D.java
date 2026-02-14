package waffles.utils.sets.utilities.indexed.coords;

/**
 * A {@code Coordination3D} represents a three-dimensional chunk of an {@code IndexedSet}.
 * 
 * @author Waffles
 * @since 13 Feb 2026
 * @version 1.1
 *
 * 
 * @see Coordinator
 */
public interface Coordination3D extends Coordination
{
	/**
	 * Returns the {@code Coordination3D} row count.
	 *
	 * @return  a row count
	 */
	public default int Rows()
	{
		return Dimensions()[0];
	}
	
	/**
	 * Returns the {@code Coordination3D} column count.
	 *
	 * @return  a column count
	 */
	public default int Columns()
	{
		return Dimensions()[1];
	}

	/**
	 * Returns the {@code Coordination3D} aisle count.
	 *
	 * @return  an aisle count
	 */
	public default int Aisles()
	{
		return Dimensions()[2];
	}
	
	
	@Override
	public default int Order()
	{
		return 3;
	}
}