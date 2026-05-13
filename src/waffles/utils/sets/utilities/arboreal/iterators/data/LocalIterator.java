package waffles.utils.sets.utilities.arboreal.iterators.data;

import java.util.Iterator;

import waffles.utils.sets.arboreal.data.DataNodal;
import waffles.utils.sets.utilities.arboreal.iterators.BreadthFirst;

/**
 * A {@code LocalIterator} iterates a {@code DataBoreal} in the region of one of its objects.
 * Iteration starts after the index object of the current node, and proceeds to its children.
 * This iterator is designed to piece together the {@code PairIterator} in a clear way.
 *
 * @author Waffles
 * @since May 10, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Iterator
 */
public class LocalIterator<O> implements Iterator<O>
{
	private O next;
	private Iterator<O> objects;
	private Iterator<DataNodal<O>> nodes;
	
	/**
	 * Creates a new {@code LocalIterator}.
	 * 
	 * @param r  a root nodal
	 * @param i  a data index
	 * 
	 * 
	 * @see DataNodal
	 */
	public LocalIterator(DataNodal<O> r, int i)
	{
		nodes = new BreadthFirst<>(r);
		nodes.next();
		
		objects = r.Data().iterator();
		for(int k = 0; k < i; k++)
		{
			if(objects.hasNext())
			{
				objects.next();
			}
		}

		next = findNext();
	}
	
	
	private O findNext()
	{
		if(objects.hasNext())
		{
			return objects.next();
		}
		
		if(nodes.hasNext())
		{
			DataNodal<O> node = nodes.next();
			objects = node.Data().iterator();
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
		O curr = next;
		next = findNext();
		return curr;
	}
}