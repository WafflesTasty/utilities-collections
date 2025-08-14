package waffles.utils.sets.countable.wrapper;

import java.util.ArrayList;

import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.indexed.AtomicIndex;
import waffles.utils.sets.indexed.array.like.ObjectArray;

/**
 * A {@code List} defines a one-dimensional index backed by a {@code java.util.ArrayList}.
 *
 * @author Waffles
 * @since Feb 08, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see ObjectArray
 * @see AtomicIndex
 * @see AtomicSet
 */
public class List<O> implements AtomicIndex.Java<O>, AtomicSet.Java<O>, ObjectArray<O>
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
	 * @param size  a list size
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
	public Object[] Array()
	{
		return data.toArray();
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
		return new int[]{Count()};
	}
	
	@Override
	public void clear()
	{
		Delegate().clear();
	}
}