package waffles.util.sets.indexed;

import waffles.utils.tools.patterns.semantics.Clearable;

/**
 * A {@code MutableIndex} defines an index with directly mutable values.
 * </br> Objects can be added and removed through coordinate access.
 *
 * @author Waffles
 * @since 16 Nov 2021
 * @version 1.0
 *
 *
 * @param <T>  an index object type
 * @see IndexedSet
 * @see Clearable
 */
public interface MutableIndex<T> extends IndexedSet<T>, Clearable
{
	/**
	 * Returns a coordinate in the {@code Mutable.Index}.
	 * 
	 * @param val  a target value
	 * @return  a value coordinate
	 */
	public abstract int[] indexOf(T val);
	
	/**
	 * Changes a value from the {@code Atomic Index}.
	 * 
	 * @param val  an index value
	 * @param coords  index coordinates
	 * @return  a previous index value
	 */
	public abstract T put(T val, int... coords);
	
	/**
	 * Removes a value from the {@code Mutable.Index}.
	 * 
	 * @param coords  index coordinates
	 * @return  a previous index value
	 */
	public abstract T remove(int... coords);
}