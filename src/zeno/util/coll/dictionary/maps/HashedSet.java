package zeno.util.coll.dictionary.maps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import zeno.util.coll.Collection;

/**
 * The {@code HashedSet} class defines a basic collection backed by a hash.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Collection
 */
public class HashedSet<O> implements Collection<O>
{
	private Set<O> data;
	
	/**
	 * Creates a new {@code HashedSet}.
	 * 
	 * @param set  a data set
	 */
	public HashedSet(O... set)
	{
		data = new HashSet<>(Arrays.asList(set));
	}
	
	/**
	 * Creates a new {@code HashedSet}.
	 */
	public HashedSet()
	{
		data = new HashSet<>();
	}

	
	@Override
	public int hashCode()
	{
		return data.hashCode();
	}
	
	@Override
	public Iterator<O> iterator()
	{
		return data.iterator();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof HashedSet)
		{
			return data.equals(((HashedSet<?>) obj).data);
		}
		
		return false;
	}
	
	@Override
	public boolean contains(O obj)
	{
		return data.contains(obj);
	}

	@Override
	public void remove(O obj)
	{
		data.remove(obj);
	}

	@Override
	public void add(O obj)
	{
		data.add(obj);
	}

	@Override
	public void clear()
	{
		data.clear();
	}
	
	@Override
	public int Count()
	{
		return data.size();
	}
}