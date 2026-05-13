package waffles.utils.sets.arboreal.data;

import waffles.utils.sets.IterableSet;
import waffles.utils.sets.utilities.arboreal.Nodal;

/**
 * A {@code DataNodal} defines a tree node with a data set.
 *
 * @author Waffles
 * @since May 10, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Nodal
 */
public interface DataNodal<O> extends Nodal
{
	/**
	 * Returns the data of the {@code DataNodal}.
	 * 
	 * @return  an iterable set
	 * 
	 * 
	 * @see IterableSet
	 */
	public abstract IterableSet<O> Data();
}