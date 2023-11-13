package waffles.utils.sets;

import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.sets.utilities.iterators.IndexKeys;
import waffles.utils.sets.utilities.iterators.IndexValues;

/**
 * An {@code ArrayLike} defines a generic array-like structure.
 *
 * @author Waffles
 * @since 12 Nov 2023
 * @version 1.1
 *
 *
 * @param <A>  an array type
 * @param <O>  an object type
 * @see MutableIndex
 */
public interface ArrayLike<A, O> extends MutableIndex<O>
{	
	@Override
	public default void clear()
	{
		for(int[] crd : Keys())
		{
			remove(crd);
		}
	}
	
	
	/**
	 * Iterates over the keys of the {@code ArraySet}.
	 * 
	 * @return  a key iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<int[]> Keys()
	{
		return () -> new IndexKeys(this, Ordering());
	}

	/**
	 * Iterates over the values of the {@code ArraySet}.
	 * 
	 * @return  a value iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<O> Values()
	{
		return () -> new IndexValues<>(this, Ordering());
	}
	
	
	/**
	 * Returns the ordering of the {@code ArraySet}.
	 * 
	 * @return  an index order
	 */
	public abstract Order Ordering();
	
	/**
	 * Returns the array of the {@code ArraySet}.
	 * 
	 * @return  a data array
	 */
	public abstract A Array();
}