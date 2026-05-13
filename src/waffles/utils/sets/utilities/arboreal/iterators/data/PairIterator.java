package waffles.utils.sets.utilities.arboreal.iterators.data;

import java.util.Iterator;

import waffles.utils.sets.arboreal.data.DataNodal;
import waffles.utils.sets.utilities.arboreal.iterators.BreadthFirst;
import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code PairIterator} iterates over all nearest pairs in a {@code DataBoreal}.
 *
 * @author Waffles
 * @since May 10, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Iterator
 * @see Pair
 */
public class PairIterator<O> implements Iterator<Pair<O, O>>
{
	private int idx;
	private Pair<O, O> next;
	private Iterator<O> keys, vals;
	private Iterator<DataNodal<O>> nodes;
	private DataNodal<O> curr;
	
	/**
	 * Creates a new {@code PairIterator}.
	 * 
	 * @param r  a root nodal
	 * 
	 * 
	 * @see DataNodal
	 */
	public PairIterator(DataNodal<O> r)
	{
		keys = new EmptyIterator<>();
		nodes = new BreadthFirst<>(r);
		vals = new EmptyIterator<>();
		next = findNext();
	}
	
	
	Pair<O, O> findNext()
	{
		if(vals.hasNext())
		{
			O key = next.Key(); O val = vals.next();
			return new Pair.Base<>(key, val);
		}
		
		if(keys.hasNext())
		{
			next = new Pair.Base<>(keys.next(), null);
			vals = new LocalIterator<>(curr, idx++);
			return findNext();
		}
		
		if(nodes.hasNext())
		{
			idx = 0;
			curr = nodes.next();
			keys = curr.Data().iterator();
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
	public Pair<O, O> next()
	{
		Pair<O, O> curr = next;
		next = findNext();
		return curr;
	}
}