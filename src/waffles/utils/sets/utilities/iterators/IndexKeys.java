package waffles.utils.sets.utilities.iterators;

import java.util.Iterator;

import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.indexed.MutableIndex.Order;
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
	private Order order;
	private int[] min, max;
	private int[] curr, next;
	private IndexedSet<?> index;

	/**
	 * Creates a new {@code IndexKeys}.
	 * 
	 * @param index  a target index
	 * @param order  an index order
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexKeys(IndexedSet<?> index, Order order)
	{
		this(index, order, index.Minimum(), index.Maximum());
	}
	
	/**
	 * Creates a new {@code IndexKeys}.
	 * 
	 * @param index  a target index
	 * @param order  an index order
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexKeys(IndexedSet<?> index, Order order, int[] min, int[] max)
	{
		this.index = index;
		this.order = order;
		
		this.min = min;
		this.max = max;

		validate();
	}
	
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
		this(index, Order.COL_MAJOR, min, max);
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
		this(index, Order.COL_MAJOR);
	}
	
		
	@Override
	public boolean hasNext()
	{
		return next != null;
	}
	
	@Override
	public int[] next()
	{
		curr = Array.copy.of(next);
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