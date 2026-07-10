package waffles.utils.sets.utilities.queues;

import java.util.Iterator;

import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.wrapper.FIFOQueue;

/**
 * A {@code CyclicIterator} infinitely cycles another iterator.
 *
 * @author Waffles
 * @since Jul 10, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Iterator
 */
public class CyclicIterator<O> implements Iterator<O>
{
	private Queue<O> set;
	private Iterator<O> src;
	
	/**
	 * Creates a new {@code CyclicIterator}.
	 * 
	 * @param s  a source iterator
	 * 
	 * 
	 * @see Iterator
	 */
	public CyclicIterator(Iterator<O> s)
	{
		set = new FIFOQueue<>();
		src = s;
	}
	
	
	@Override
	public boolean hasNext()
	{
		return !(set.isEmpty() && !src.hasNext());
	}
	
	@Override
	public O next()
	{
		if(src.hasNext())
		{
			O next = src.next();
			set.push(next);
			return next;
		}
		
		src = set.iterator();
		return next();
	}
}