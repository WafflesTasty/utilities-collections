package waffles.utils.sets.utilities.indexed.iterators.arrays;

import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.utilities.indexed.iterators.IndexKeys;

/**
 * An {@code ArrayKeys} iterates over an {@code ArraySet} and returns non-null indices.
 *
 * @author Waffles
 * @since 12 Feb 2026
 * @version 1.1
 *
 * 
 * @see IndexKeys
 */
public class ArrayKeys extends IndexKeys
{
	/**
	 * Creates a new {@code ArrayKeys}.
	 * 
	 * @param set  a target set
	 * @param min  a minimum index
	 * @param max  a maximum index
	 * 
	 * 
	 * @see ArraySet
	 */
	public ArrayKeys(ArraySet<?, ?> set, int min, int max)
	{
		super(set, new int[]{min}, new int[]{max});
	}
	
	/**
	 * Creates a new {@code ArrayKeys}.
	 * 
	 * @param set  a target set
	 * 
	 * 
	 * @see ArraySet
	 */
	public ArrayKeys(ArraySet<?, ?> set)
	{
		super(set);
	}
}