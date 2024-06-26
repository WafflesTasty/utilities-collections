package waffles.utils.sets.indexed.delegate;

import java.util.ArrayList;

import waffles.utils.sets.indexed.mutable.AtomicIndex;
import waffles.utils.sets.mutable.DelegateSet;

/**
 * A {@code List} defines a one-dimensional index backed by a Java {@code ArrayList}.
 *
 * @author Waffles
 * @since Feb 08, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  a list object type
 * @see AtomicIndex
 * @see DelegateSet
 */
public class List<O> implements DelegateSet<O>, AtomicIndex<O>
{
	private ArrayList<O> data;
	
	/**
	 * Creates a new {@code List}.
	 * 
	 * @param set  a data set
	 */
	public List(O... set)
	{
		data = new ArrayList<>();
		for(O obj : set)
		{
			data.add(obj);
		}
	}
	
	/**
	 * Creates a new {@code List}.
	 * 
	 * @param size  an initial size
	 */
	public List(int size)
	{
		data = new ArrayList<>(size);
	}
	
	/**
	 * Creates a new {@code List}.
	 */
	public List()
	{
		data = new ArrayList<>();
	}

	
	@Override
	public O get(int... coords)
	{
		if(contains(coords))
		{
			return Delegate().get(coords[0]);
		}
		
		return null;
	}
	
	@Override
	public O remove(int... coords)
	{
		if(contains(coords))
		{
			return Delegate().remove(coords[0]);
		}
		
		return null;
	}
	
	@Override
	public O put(O val, int... coords)
	{
		while(Count() <= coords[0])
		{
			Delegate().add(null);
		}
		
		Delegate().set(coords[0], val);
		return val;
	}
	
	@Override
	public ArrayList<O> Delegate()
	{
		return data;
	}

	@Override
	public int[] indexOf(O val)
	{
		int index = Delegate().indexOf(val);
		if(index != -1)
		{
			return new int[]{index};
		}
		
		return null;
	}
	
	@Override
	public int[] Dimensions()
	{
		return new int[]{Delegate().size()};
	}

	@Override
	public int Count()
	{
		return Delegate().size();
	}
}