package waffles.utils.sets.indexed;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.IterableSet;

/**
 * An {@code AtomicIndex} defines a {@code MutableIndex} with unique objects.
 *
 * @author Waffles
 * @since 24 Aug 2023
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see MutableIndex
 * @see IterableSet
 */
public interface AtomicIndex<O> extends MutableIndex<O>, IterableSet<O>
{
	/**
	 * A {@code Wrapper} defines a wrapper around another {@code MutableIndex}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <O>  an object type
	 * @see MutableIndex
	 * @see IterableSet
	 * @see AtomicIndex
	 */
	public static interface Wrapper<O> extends MutableIndex.Wrapper<O>, IterableSet.Wrapper<O>, AtomicIndex<O>
	{		
		@Override
		public abstract AtomicIndex<O> Delegate();

		@Override
		public default int[] indexOf(O obj)
		{
			return Delegate().indexOf(obj);
		}
		
		@Override
		public default int Count()
		{
			return AtomicIndex.super.Count();
		}
	}
	
	/**
	 * A {@code Java AtomicIndex} defines a wrapper around a {@code Collection}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see CountableSet
	 * @see IterableSet
	 */
	public static interface Java<O> extends IterableSet.Java<O>, AtomicIndex<O>
	{
		@Override
		public default int Count()
		{
			return Delegate().size();
		}
	}
	
	
	/**
	 * Returns an object index the {@code AtomicIndex}.
	 * 
	 * @param val  an object value
	 * @return  an index coordinate
	 */
	public abstract int[] indexOf(O val);

	
	@Override
	public default int Count()
	{
		return MutableIndex.super.Count();
	}
}