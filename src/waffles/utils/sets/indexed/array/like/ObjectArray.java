package waffles.utils.sets.indexed.array.like;

import waffles.utils.sets.indexed.array.ArrayLike;

/**
 * An {@code ObjectArray} manages an object array as an {@code ArrayLike}.
 *
 * @author Waffles
 * @since 12 Nov 2023
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see ArrayLike
 */
@FunctionalInterface
public interface ObjectArray<O> extends ArrayLike<Object[], O>
{
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