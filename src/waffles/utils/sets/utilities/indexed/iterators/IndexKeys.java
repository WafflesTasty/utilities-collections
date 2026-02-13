package waffles.utils.sets.utilities.indexed.iterators;

import java.util.Iterator;

import waffles.utils.sets.indexed.MutableIndex.Order;
import waffles.utils.sets.utilities.indexed.coords.Coordination;
import waffles.utils.tools.primitives.Array;

/**
 * An {@code IndexKeys} iterates over an {@code Index} and returns non-null indices.
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
	private int[] iMin, iMax;
	private int[] curr, next;
	private Coordination index;

	/**
	 * Creates a new {@code IndexKeys}.
	 * 
	 * @param idx  an indexed set
	 * @param ord  an index order
	 * 
	 * 
	 * @see Coordination
	 * @see Order
	 */
	public IndexKeys(Coordination idx, Order ord)
	{
		this(idx, ord, idx.Minimum(), idx.Maximum());
	}
	
	/**
	 * Creates a new {@code IndexKeys}.
	 * 
	 * @param idx  an indexed set
	 * @param ord  an index order
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * 
	 * 
	 * @see Coordination
	 * @see Order
	 */
	public IndexKeys(Coordination idx, Order ord, int[] min, int[] max)
	{		
		index = idx;
		order = ord;
		
		iMin = min;
		iMax = max;
		
		validate();
	}
	
	/**
	 * Creates a new {@code IndexKeys}.
	 * 
	 * @param idx  a target index
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * 
	 * 
	 * @see Coordination
	 */
	public IndexKeys(Coordination idx, int[] min, int[] max)
	{
		this(idx, Order.COL_MAJOR, min, max);
	}
	
	/**
	 * Creates a new {@code IndexKeys}.
	 * 
	 * @param idx  a target index
	 * 
	 * 
	 * @see Coordination
	 */
	public IndexKeys(Coordination idx)
	{
		this(idx, Order.COL_MAJOR);
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
		next = findNext();
		return curr;
	}
	
	
	int[] Current()
	{
		return curr;
	}
	
	Coordination Index()
	{
		return index;
	}
	
	private boolean validate()
	{
		for(int i = 0; i < index.Order(); i++)
		{
			if(iMin[i] > iMax[i])
			{
				next = null;
				return false;
			}
		}
		
		next = Array.copy.of(iMin);
		return true;
	}
	
	private int[] findColMajor()
	{
		for(int i = 0; i < index.Order(); i++)
		{
			next[i]++;
			if(next[i] <= iMax[i])
				break;
			else
			{
				next[i] = iMin[i];
				if(i == index.Order() - 1)
				{
					next = null;
					break;
				}
			}
		}
		
		return next;
	}
	
	private int[] findRowMajor()
	{
		for(int i = index.Order()-1; i >= 0; i--)
		{
			next[i]++;
			if(next[i] <= iMax[i])
				break;
			else
			{
				next[i] = iMin[i];
				if(i == 0)
				{
					next = null;
					break;
				}
			}
		}
		
		return next;
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
}