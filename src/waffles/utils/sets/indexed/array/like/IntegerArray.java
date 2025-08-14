package waffles.utils.sets.indexed.array.like;

import waffles.utils.sets.indexed.array.ArrayLike;
import waffles.utils.tools.patterns.properties.values.Copyable;

/**
 * An {@code IntegerArray} manages a primitive integer array as an {@code ArrayLike}.
 *
 * @author Waffles
 * @since 11 Nov 2023
 * @version 1.1
 * 
 * 
 * @see ArrayLike
 * @see Copyable
 */
public interface IntegerArray extends ArrayLike<int[], Integer>, Copyable<IntegerArray>
{
	@Override
	public default Integer get(int... crds)
	{
		return Array()[toIndex(Ordering(), crds)];
	}
	
	@Override
	public default Integer put(Integer val, int... crds)
	{
		int index = toIndex(Ordering(), crds);
		Integer prev = Array()[index];
		Array()[index] = val;
		return prev;
	}
	
	@Override
	public default Integer remove(int... crds)
	{
		return put(null, crds);
	}
	
	@Override
	public default IntegerArray instance()
	{
		return () -> null;
	}
	
	@Override
	public default IntegerArray copy()
	{
		return () -> Array();
	}
}