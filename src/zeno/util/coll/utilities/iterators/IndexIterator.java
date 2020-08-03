package zeno.util.coll.utilities.iterators;

import java.util.Iterator;

import zeno.util.coll.indices.Index;

/**
 * The {@code IndexIterator} class iterators over a subsection of an {@code Index}.
 *
 * @author Zeno
 * @since 28 Feb 2020
 * @version 1.0
 *
 *
 * @param <V>  an index value type
 * @see Iterator
 */
public class IndexIterator<V> implements Iterator<V>
{
	private Index<V> index;
	private int[] curr, next;
	private int[] min, max;
	
	/**
	 * Creates a new {@code IndexIterator}.
	 * 
	 * @param index  a target index
	 * @param min  a minimum coordinate set
	 * @param max  a maximum coordinate set
	 * 
	 * 
	 * @see Index
	 */
	public IndexIterator(Index<V> index, int[] min, int[] max)
	{
		this.index = index;
		
		this.min = min;
		this.max = max;
		
		if(validate())
		{
			find();
		}
	}
	
	/**
	 * Creates a new {@code IndexIterator}.
	 * 
	 * @param index  a target index
	 * 
	 * 
	 * @see Index
	 */
	public IndexIterator(Index<V> index)
	{
		this.index = index;
		
		this.min = index.Minimum();
		this.max = index.Maximum();
	}
	
	
	@Override
	public void remove()
	{
		index.remove(curr);
	}
	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}
	
	@Override
	public V next()
	{
		V val = index.get(next);
		curr = next;
		find();
		
		return val;
	}
	
	
	private boolean validate()
	{
		for(int i = 0; i < index.Order(); i++)
		{
			if(min[i] > max[i])
			{
				next = null;
				return false;
			}
		}
		
		next = min;
		return true;
	}
	
	private void find()
	{
		for(int i = 0; i < index.Order(); i++)
		{
			next[i]++;
			if(next[i] <= max[i])
				break;
			else
			{
				next[i] = min[i];
				if(i == index.Order() - 1)
				{
					next = null;
					return;
				}
			}
		}
		
		if(index.get(next) == null)
		{
			find();
		}
	}
}