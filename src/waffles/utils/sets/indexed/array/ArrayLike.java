package waffles.utils.sets.indexed.array;

import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.sets.utilities.indexed.iterators.IndexKeys;
import waffles.utils.sets.utilities.indexed.iterators.IndexValues;

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
	/**
	 * A {@code Wrapper} defines a wrapper around another {@code ArrayLike}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <A>  an array type
	 * @param <O>  an object type
	 * @see MutableIndex
	 * @see ArrayLike
	 */
	public static interface Wrapper<A, O> extends MutableIndex.Wrapper<O>, ArrayLike<A, O>
	{		
		@Override
		public abstract ArrayLike<A, O> Delegate();
				
		@Override
		public default Order Ordering()
		{
			return Delegate().Ordering();
		}
		
		@Override
		public default void clear()
		{
			Delegate().clear();
		}
		
		@Override
		public default A Array()
		{
			return Delegate().Array();
		}
	}
	
	
	/**
	 * Returns the array of the {@code ArrayLike}.
	 * 
	 * @return  a data array
	 */
	public abstract A Array();
	
	/**
	 * Returns the ordering of the {@code ArrayLike}.
	 * 
	 * @return  an index order
	 */
	public default Order Ordering()
	{
		return Order.COL_MAJOR;
	}
	
	
	/**
	 * Iterates over the keys of the {@code ArrayLike}.
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
	 * Iterates over the values of the {@code ArrayLike}.
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
	
	
	@Override
	public default void clear()
	{
		for(int[] crd : Keys())
		{
			remove(crd);
		}
	}
}