package waffles.utils.sets.indexed.array;

import waffles.utils.sets.indexed.AtomicIndex;

/**
 * An {@code ArraySet} object maintains a one-dimensional {@code ArrayLike} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 *
 *
 * @param <A>  an array type
 * @param <O>  an object type
 * @see AtomicIndex
 * @see ArrayLike
 */
public interface ArraySet<A, O> extends ArrayLike<A, O>, AtomicIndex<O>
{	
	@Override
	public default int[] Dimensions()
	{
		return new int[]{Count()};
	}
}