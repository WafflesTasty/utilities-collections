package waffles.utils.sets.indexed.mutable;

import waffles.utils.sets.indexed.MutableIndex;

/**
 * An {@code AtomicIndex} is a mutable index which defines a unique object at every coordinate.
 *
 * @author Waffles
 * @since 24 Aug 2023
 * @version 1.1
 *
 *
 * @param <O>  an index object type
 * @see MutableIndex
 * @see Iterable
 */
public interface AtomicIndex<O> extends MutableIndex<O>, Iterable<O>
{
	/**
	 * Returns a coordinate in the {@code AtomicIndex}.
	 * 
	 * @param val  a target value
	 * @return  a value coordinate
	 */
	public abstract int[] indexOf(O val);
}