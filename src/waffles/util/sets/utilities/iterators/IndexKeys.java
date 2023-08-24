package waffles.util.sets.utilities.iterators;

import java.util.Iterator;

import waffles.util.sets.indexed.IndexedSet;
import waffles.utils.tools.primitives.Array;

/**
 * An {@code IndexKeys} iterates over a subsection of an {@code Index} and returns non-null indices.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.1
 *
 *
 * @see Iterator
 */
public class IndexKeys implements Iterator<int[]>
{
	private IndexedSet<?> index;
	private int[] curr, next;
	private int[] min, max;
	
	/**
	 * Creates a new {@code IndexKeys}.
	 * 
	 * @param index  a target index
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexKeys(IndexedSet<?> index, int[] min, int[] max)
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
	 * Creates a new {@code IndexKeys}.
	 * 
	 * @param index  a target index
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexKeys(IndexedSet<?> index)
	{
		this(index, index.Minimum(), index.Maximum());
	}
	
		
	@Override
	public boolean hasNext()
	{
		return next != null;
	}
	
	@Override
	public int[] next()
	{
		curr = next;
		find();
		
		return curr;
	}
	
	
	protected int[] Current()
	{
		return curr;
	}
	
	protected IndexedSet<?> Index()
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