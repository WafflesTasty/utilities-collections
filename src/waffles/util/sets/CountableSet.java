package waffles.util.sets;

/**
 * A {@code CountableSet} contains a countable number of objects of a specified type.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  a set object type
 */
@FunctionalInterface
public interface CountableSet<O>
{	
	/**
	 * Checks if the {@code CountableSet} is completely empty.
	 * 
	 * @return  {@code true} if the set is empty
	 */
	public default boolean isEmpty()
	{
		return Count() == 0;
	}
	
	/**
	 * Returns the size of the {@code CountableSet}.
	 * 
	 * @return  an item count
	 */
	public abstract int Count();
}