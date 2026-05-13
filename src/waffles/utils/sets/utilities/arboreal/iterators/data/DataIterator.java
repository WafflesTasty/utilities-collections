package waffles.utils.sets.utilities.arboreal.iterators.data;

import java.util.Iterator;

import waffles.utils.sets.arboreal.data.DataNodal;
import waffles.utils.sets.utilities.arboreal.iterators.BreadthFirst;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code DataIterator} iterates over all data in a {@code DataBoreal}.
 *
 * @author Waffles
 * @since 21 Mar 2025
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see Iterator
 */
public class DataIterator<O> implements Iterator<O>
{
	private O next;
	private Iterator<O> data;
	private Iterator<? extends DataNodal<O>> nodes;
	
	/**
	 * Creates a new {@code DataIterator}.
	 * 
	 * @param n  a node iterator
	 * 
	 * 
	 * @see DataNodal
	 * @see Iterator
	 */
	public DataIterator(Iterator<? extends DataNodal<O>> n)
	{
		nodes = n;
		data = new EmptyIterator<>();
		next = findNext();
	}
	
	/**
	 * Creates a new {@code DataIterator}.
	 * 
	 * @param r  a root nodal
	 * 
	 * 
	 * @see DataNodal
	 */
	public DataIterator(DataNodal<O> r)
	{
		this(new BreadthFirst<>(r));
	}

	
	private O findNext()
	{
		if(data.hasNext())
		{
			return data.next();
		}
		
		if(nodes.hasNext())
		{
			DataNodal<O> n = nodes.next();
			data = n.Data().iterator();
			return findNext();			
		}

		return null;
	}

	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public O next()
	{
		O curr = (O) next;
		next = findNext();
		return curr;
	}
}