package zeno.util.coll.utilities.iterators;

import java.util.Iterator;

import zeno.util.coll.indices.Index;
import zeno.util.coll.indices.Index.Atomic;

/**
 * The {@code AtomicIterator} class iterators over a subsection of an {@code Atomic}.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.0
 *
 *
 * @param <V>  an index value type
 * @see Iterator
 */
public class AtomicIterator<V> implements Iterator<V>
{
	private Atomic<V> atomic;
	private int[] curr, next;
	private int[] min, max;
	
	/**
	 * Creates a new {@code AtomicIterator}.
	 * 
	 * @param atomic  an atomic index
	 * @param min  a minimum coordinate set
	 * @param max  a maximum coordinate set
	 * 
	 * 
	 * @see Index
	 */
	public AtomicIterator(Atomic<V> atomic, int[] min, int[] max)
	{
		this.atomic = atomic;
		
		this.min = min;
		this.max = max;
		
		if(validate())
		{
			find();
		}
	}
	
	/**
	 * Creates a new {@code AtomicIterator}.
	 * 
	 * @param atomic  an atomic index
	 * 
	 * 
	 * @see Index
	 */
	public AtomicIterator(Atomic<V> atomic)
	{
		this.atomic = atomic;
		
		this.min = atomic.Minimum();
		this.max = atomic.Maximum();
	}
	
	
	@Override
	public void remove()
	{
		atomic.remove(curr);
	}
	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}
	
	@Override
	public V next()
	{
		V val = atomic.get(next);
		curr = next;
		find();
		
		return val;
	}
	
	
	private boolean validate()
	{
		for(int i = 0; i < atomic.Order(); i++)
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
		for(int i = 0; i < atomic.Order(); i++)
		{
			next[i]++;
			if(next[i] <= max[i])
				break;
			else
			{
				next[i] = min[i];
				if(i == atomic.Order() - 1)
				{
					next = null;
					return;
				}
			}
		}
		
		if(atomic.get(next) == null)
		{
			find();
		}
	}
}