package waffles.utils.sets.arrays;

import waffles.utils.sets.ArrayLike;
import waffles.utils.sets.indexed.MutableIndex;

/**
 * An {@code ObjectArray} manages an object array as a {@code MutableIndex}.
 *
 * @author Waffles
 * @since 12 Nov 2023
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see MutableIndex
 * @see ArrayLike
 */
public interface ObjectArray<O> extends ArrayLike<Object[]>, MutableIndex<O>
{
	/**
	 * Returns the ordering of the {@code ObjectArray}.
	 * 
	 * @return  an index order
	 */
	public abstract Order Ordering();
	
	
	@Override
	public default O get(int... coords)
	{
		return (O) Array()[toIndex(Ordering(), coords)];
	}
	
	@Override
	public default O put(O val, int... coords)
	{
		int index = toIndex(Ordering(), coords);
		O prev = (O) Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default O remove(int... coords)
	{
		return put(null, coords);
	}
}