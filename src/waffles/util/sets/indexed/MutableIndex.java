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
 * @param <O>  an index object type
 * @see IndexedSet
 * @see Clearable
 */
public interface MutableIndex<O> extends IndexedSet<O>, Clearable
{
	/**
	 * Changes a value in the {@code MutableIndex}.
	 * 
	 * @param val  an index value
	 * @param coords  index coordinates
	 * @return  a previous index value
	 */
	public abstract O put(O val, int... coords);
	
	/**
	 * Removes a value from the {@code MutableIndex}.
	 * 
	 * @param coords  index coordinates
	 * @return  a previous index value
	 */
	public abstract O remove(int... coords);
}