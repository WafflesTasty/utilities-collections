package zeno.util.coll.grids;

import java.util.ArrayList;
import java.util.Iterator;

import zeno.util.coll.Collection;
import zeno.util.coll.Index;

/**
 * The {@code List} class defines a one-dimensional collection backed by an {@code ArrayList}.
 *
 * @author Zeno
 * @since Feb 08, 2020
 * @version 1.0
 * 
 * 
 * @param <V>  a collection object type
 * @see Collection
 * @see Index
 */
public class List<V> implements Collection<V>, Index<V>
{
	private ArrayList<V> source;
	
	/**
	 * Creates a new {@code List}.
	 */
	public List()
	{
		source = new ArrayList<>();
	}
	
	
	@Override
	public void add(V value)
	{
		source.add(value);
	}
	
	@Override
	public boolean contains(V val)
	{
		return source.contains(val);
	}
	
	@Override
	public void remove(V val)
	{
		source.remove(val);
	}

	
	@Override
	public boolean contains(int... coords)
	{
		// If coordinates exceed the grid order...
		for(int i = Order(); i < coords.length; i++)
		{
			// The remainder have to be zero.
			if(coords[i] != 0)
			{
				return false;
			}
		}
		
		return 0 <= coords[0] && coords[0] < source.size();
	}

	@Override
	public V put(V val, int... coords)
	{
		return source.set(coords[0], val);
	}

	@Override
	public V get(int... coords)
	{
		if(0 <= coords[0] && coords[0] < source.size())
		{
			return source.get(coords[0]);
		}
		
		return null;
	}

	
	@Override
	public Iterator<V> iterator()
	{
		return source.iterator();
	}
	
	@Override
	public int[] Dimensions()
	{
		return new int[]{source.size()};
	}
}