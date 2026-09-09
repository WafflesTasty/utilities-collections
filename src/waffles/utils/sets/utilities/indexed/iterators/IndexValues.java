package waffles.utils.sets.utilities.indexed.iterators;

import java.util.Iterator;

import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.indexed.MutableIndex.Order;
import waffles.utils.tools.primitives.Array;

/**
 * An {@code IndexValues} iterates over a subsection of an {@code Index} and returns non-null objects.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Iterator
 */
public class IndexValues<O> implements Iterator<O>
{
	private int[] next;
	private int[] min, max;
	private IndexedSet<O> index;
	private Order order;
	
	/**
	 * Creates a new {@code IndexValues}.
	 * 
	 * @param set  an indexed set
	 * @param ord  an index order
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexValues(IndexedSet<O> set, Order ord)
	{
		this(set, ord, set.Minimum(), set.Maximum());
	}
	
	/**
	 * Creates a new {@code IndexValues}.
	 * 
	 * @param set  an indexed set
	 * @param ord  an index order
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexValues(IndexedSet<O> set, Order ord, int[] min, int[] max)
	{
		this.index = set;
		this.order = ord;

		this.min = min;
		this.max = max;

		if(validate())
		{
			if(index.get(next) == null)
			{
				next = findNext();
			}			
		}
	}
	
	/**
	 * Creates a new {@code IndexValues}.
	 * 
	 * @param set  an indexed set
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexValues(IndexedSet<O> set, int[] min, int[] max)
	{
		this(set, Order.COL_MAJOR, min, max);
	}
	
	/**
	 * Creates a new {@code IndexValues}.
	 * 
	 * @param set  an indexed set
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexValues(IndexedSet<O> set)
	{
		this(set, Order.COL_MAJOR);
	}
	

	private int[] findNext()
	{
		switch(order)
		{
		case COL_MAJOR:
			return findColMajor();
		case ROW_MAJOR:
			return findRowMajor();
		default:
			return null;
		}
	}
	
	private int[] findColMajor()
	{
		O obj = null;
		while(obj == null)
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
						return null;
					}
				}
			}
			
			obj = index.get(next);			
		}

		return next;
	}
	
	private int[] findRowMajor()
	{
		O obj = null;
		while(obj == null)
		{
			for(int i = index.Order()-1; i >= 0; i--)
			{
				next[i]++;
				if(next[i] <= max[i])
					break;
				else
				{
					next[i] = min[i];
					if(i == 0)
					{
						return null;
					}
				}
			}
			
			obj = index.get(next);			
		}
		
		return next;
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
		
	@Override
	public boolean hasNext()
	{
		return next != null;
	}
	
	@Override
	public O next()
	{
		O obj = index.get(next);
		next = findNext();
		return obj;
	}
}