package waffles.utils.sets.arrays;

/**
 * An {@code ArrayData} object maintains a one-dimensional {@code ArrayLike} object.
 *
 * @author Waffles
 * @since 13 Nov 2023
 * @version 1.1
 *
 *
 * @param <A>  an array type
 * @param <O>  an object type
 * @see ArrayLike
 */
public interface ArrayData<A, O> extends ArrayLike<A, O>
{	
	@Override
	public default int[] Dimensions()
	{
		return new int[]{DataSize()};
	}
	
	@Override
	public default int Count()
	{
		return DataSize();
	}
}