package waffles.utils.sets;

import waffles.utils.sets.indexed.MutableIndex;

/**
 * An {@code ArraySet} defines a generic data array.
 *
 * @author Waffles
 * @since 12 Nov 2023
 * @version 1.1
 *
 *
 * @param <O>  an object type
 */
public interface ArraySet<O> extends MutableIndex<O>
{
	/**
	 * Returns the array of the {@code ArraySet}.
	 * 
	 * @return  a data array
	 */
	public abstract O[] Array();
	
	/**
	 * Returns the ordering of the {@code ArraySet}.
	 * 
	 * @return  an index order
	 */
	public abstract Order Ordering();
	
	
	@Override
	public default O get(int... coords)
	{
		return Array()[toIndex(Ordering(), coords)];
	}
	
	@Override
	public default O put(O val, int... coords)
	{
		int index = toIndex(Ordering(), coords);
		O prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default O remove(int... coords)
	{
		return put(null, coords);
	}
	
	@Override
	public default int Count()
	{
		return Array().length;
	}
}