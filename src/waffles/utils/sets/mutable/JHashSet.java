package waffles.utils.sets.mutable;

import java.util.Arrays;
import java.util.HashSet;

/**
 * A {@code JHashSet} defines a basic unordered collection backed by a Java {@code HashSet}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  a set object type
 * @see DelegateSet
 */
public class JHashSet<O> implements DelegateSet<O>
{
	private HashSet<O> data;
	
	/**
	 * Creates a new {@code JHashSet}.
	 * 
	 * @param set  a data set
	 */
	public JHashSet(O... set)
	{
		data = new HashSet<>(Arrays.asList(set));
	}
	
	/**
	 * Creates a new {@code JHashSet}.
	 */
	public JHashSet()
	{
		data = new HashSet<>();
	}

	
	@Override
	public int hashCode()
	{
		return data.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof JHashSet)
		{
			JHashSet<?> other = (JHashSet<?>) obj; 
			return data.equals(other.data);
		}
		
		return false;
	}
	
	@Override
	public HashSet<O> Delegate()
	{
		return data;
	}
}