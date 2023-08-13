package waffles.util.sets.utilities.iterators;

import java.util.Iterator;

import waffles.util.sets.indexed.IndexedSet;

/**
 * An {@code IndexIterator} iterates over a subsection of an {@code Index}.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.0
 *
 *
 * @param <O>  an index object type
 * @see Iterator
 */
public class IndexIterator<O> implements Iterator<O>
{
	private IndexedSet<O> index;
	private int[] curr, next;
	private int[] min, max;
	
	/**
	 * Creates a new {@code IndexIterator}.
	 * 
	 * @param index  a target index
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexIterator(IndexedSet<O> index, int[] min, int[] max)
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
	 * @see IndexedSet
	 */
	public IndexIterator(IndexedSet<O> index)
	{
		this.index = index;
		
		this.min = index.Minimum();
		this.max = index.Maximum();
	}
	
		
	@Override
	public boolean hasNext()
	{
		return next != null;
	}
	
	@Override
	public O next()
	{
		O val = index.get(next);
		curr = next;
		find();
		
		return val;
	}
	
	
	protected int[] Current()
	{
		return curr;
	}
	
	protected IndexedSet<O> Index()
	{
		return index;
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