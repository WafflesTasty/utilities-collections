package waffles.utils.sets.utilities.rooted.iterators;

import java.util.Iterator;

import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.utilities.rooted.Nodal;
import waffles.utils.sets.utilities.rooted.Node;

/**
 * A {@code LeafIterator} iterates over all leaves of a {@code Rooted}.
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
public class LeafIterator<N extends Nodal> implements Iterator<N>
{
	private Nodal next;
	private Iterator<Nodal> nodes;
	
	/**
	 * Creates a new {@code LeafIterator}.
	 * 
	 * @param tree  a source tree
	 * 
	 * 
	 * @see Arboreal
	 */
	public LeafIterator(Arboreal tree)
	{
		nodes = tree.BFSearch().iterator();
		next = findNext();
	}

	
	private Nodal findNext()
	{
		if(nodes.hasNext())
		{
			next = nodes.next();
			
			Node n = (Node) next.Arch();
			if(!n.isLeaf())
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