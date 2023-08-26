package waffles.util.sets.utilities.iterators;

import java.util.Iterator;

import waffles.util.sets.indexed.IndexedSet;
import waffles.util.sets.indexed.MutableIndex.Order;
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
	private int[] next;
	private int[] min, max;
	private IndexedSet<O> index;
	private Order order;
	
	/**
	 * Creates a new {@code IndexValues}.
	 * 
	 * @param index  a target index
	 * @param order  an index order
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexValues(IndexedSet<O> index, Order order)
	{
		this(index, order, index.Minimum(), index.Maximum());
	}
	
	/**
	 * Creates a new {@code IndexValues}.
	 * 
	 * @param index  a target index
	 * @param order  an index order
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexValues(IndexedSet<O> index, Order order, int[] min, int[] max)
	{
		this.index = index;
		this.order = order;
		
		this.min = min;
		this.max = max;

		validate();
		if(index.get(next) == null)
		{
			find();
		}
	}
	
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
		this(index, Order.COL_MAJOR, min, max);
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
		this(index, Order.COL_MAJOR);
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
		
		next = Array.copy.of(min);
		return true;
	}
	
	private void findColMajor()
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
			findColMajor();
		}
	}
	
	private void findRowMajor()
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
					next = null;
					return;
				}
			}
		}
		
		if(index.get(next) == null)
		{
			findRowMajor();
		}
	}
	
	private void find()
	{
		switch(order)
		{
		case COL_MAJOR:
			findColMajor(); break;
		case ROW_MAJOR:
			findRowMajor(); break;
		default:
			break;
		}
	}
}