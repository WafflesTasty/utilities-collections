package waffles.utils.sets;

/**
 * A {@code MutableSet} is a countable set which allows the adding and removing of objects.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.1
 * 
 * 
 * @param <O>  a set object type
 * @see CountableSet
 */
public interface MutableSet<O> extends CountableSet
{	
	/**
	 * Removes an object from the {@code MutableSet}.
	 * 
	 * @param obj  an object to remove
	 */
	public abstract void remove(O obj);
	
	/**
	 * Adds an object to the {@code MutableSet}.
	 * 
	 * @param obj  an object to add
	 */
	public abstract void add(O obj);
}