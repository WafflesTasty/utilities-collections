package waffles.util.sets.utilities.iterators;

import java.util.Iterator;

import waffles.util.sets.indexed.IndexedSet;
import waffles.utils.tools.primitives.Array;

/**
 * An {@code IndexValues} iterates over a subsection of an {@code Index} and returns non-null objects.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.1
 *
 *
 * @param <O>  an index object type
 * @see Iterator
 */
public class IndexValues<O> implements Iterator<O>
{
	private IndexedSet<O> index;
	private int[] curr, next;
	private int[] min, max;
	
	/**
	 * Creates a new {@code IndexValues}.
	 * 
	 * @param index  a target index
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexValues(IndexedSet<O> index, int[] min, int[] max)
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
	 * Creates a new {@code IndexValues}.
	 * 
	 * @param index  a target index
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexValues(IndexedSet<O> index)
	{
		this(index, index.Minimum(), index.Maximum());
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
		
		next = Array.copy.of(min);
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