package zeno.util.coll;

/**
 * The {@code Collection} interface defines a set containing similar objects.
 *
 * @author Zeno
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  a collection object type
 * @see Iterable
 */
public interface Collection<O> extends Iterable<O>
{
	/**
	 * Adds a set of objects to the {@code Collection}.
	 * 
	 * @param vals  an object set
	 * 
	 * 
	 * @see Collection
	 */
	public default void add(Collection<O> vals)
	{
		for(O obj : vals)
		{
			add(obj);
		}
	}
	
	
	/**
	 * Checks if an object is contained in the {@code Collection}.
	 * 
	 * @param obj  an object to check
	 * @return  {@code true} if the object is contained
	 */
	public abstract boolean contains(O obj);
	
	/**
	 * Removes an object from the {@code Collection}.
	 * 
	 * @param obj  an object to remove
	 */
	public abstract void remove(O obj);
	
	/**
	 * Adds an object to the {@code Collection}.
	 * 
	 * @param obj  an object to add
	 */
	public abstract void add(O obj);
}