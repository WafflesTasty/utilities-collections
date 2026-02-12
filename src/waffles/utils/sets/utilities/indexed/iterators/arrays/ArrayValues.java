package waffles.utils.sets.utilities.indexed.iterators.arrays;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.utilities.indexed.iterators.IndexValues;

/**
 * An {@code ArrayKeys} iterates over an {@code ArraySet} and returns non-null values.
 *
 * @author Waffles
 * @since 12 Feb 2026
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see IndexValues
 */
public class ArrayValues<O> extends IndexValues<O>
{
	/**
	 * Creates a new {@code ArrayValues}.
	 * 
	 * @param set  a target set
	 * @param min  a minimum index
	 * @param max  a maximum index
	 * 
	 * 
	 * @see ArraySet
	 */
	public ArrayValues(ArraySet<?, O> set, int min, int max)
	{
		super(set, new int[]{min}, new int[]{max});
	}
	
	/**
	 * Creates a new {@code ArrayValues}.
	 * 
	 * @param set  a target set
	 * 
	 * 
	 * @see ArraySet
	 */
	public ArrayValues(ArraySet<?, O> set)
	{
		super(set);
	}
}