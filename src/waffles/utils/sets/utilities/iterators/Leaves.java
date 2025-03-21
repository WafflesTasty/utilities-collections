package waffles.utils.sets.utilities.iterators;

import java.util.Iterator;

import waffles.utils.sets.trees.Nodal;
import waffles.utils.sets.trees.Rooted;

/**
 * The {@code Leaves} class iterates over all leaves of a {@code Rooted} tree.
 *
 * @author Waffles
 * @since 21 Mar 2025
 * @version 1.1
 *
 * 
 * @param <N>  a node type
 * @see Iterator
 * @see Nodal
 */
public class Leaves<N extends Nodal> implements Iterator<N>
{
	private Nodal next;
	private Iterator<Nodal> nodes;
	
	/**
	 * Creates a new {@code Leaves}.
	 * 
	 * @param tree  a source tree
	 * 
	 * 
	 * @see Rooted
	 */
	public Leaves(Rooted tree)
	{
		nodes = tree.BFSearch().iterator();
		next = findNext();
	}

	
	Nodal findNext()
	{
		if(nodes.hasNext())
		{
			next = nodes.next();
			if(!next.Arch().isLeaf())
			{
				return findNext();
			}
			
			return next;
		}
		
		return null;
	}
		
	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public N next()
	{
		N curr = (N) next;
		next = findNext();
		return curr;
	}
}