package waffles.utils.sets.countable.wrapper;

import java.util.Arrays;
import java.util.HashSet;

import waffles.utils.sets.countable.AtomicSet;

/**
 * A {@code JavaSet} defines an {@code AtomicSet} backed by a {@code java.util.HashSet}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see AtomicSet
 */
public class JavaSet<O> implements AtomicSet.Java<O>
{
	private HashSet<O> data;
	
	/**
	 * Creates a new {@code JavaSet}.
	 * 
	 * @param set  a data set
	 */
	public JavaSet(O... set)
	{
		data = new HashSet<>(Arrays.asList(set));
	}
	
	/**
	 * Creates a new {@code JavaSet}.
	 */
	public JavaSet()
	{
		data = new HashSet<>();
	}


	@Override
	public HashSet<O> Delegate()
	{
		return data;
	}
}