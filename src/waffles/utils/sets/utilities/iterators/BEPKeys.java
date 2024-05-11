package waffles.utils.sets.utilities.iterators;

import java.util.Iterator;

import waffles.utils.sets.trees.indexed.BEPNode;
import waffles.utils.sets.trees.indexed.BEPTree;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code BEPKeys} iterator iterates over all keys in a {@code BEPTree} of the same value.
 *
 * @author Waffles
 * @since 21 Sep 2023
 * @version 1.1
 *
 *
 * @see Iterator
 */
public class BEPKeys implements Iterator<int[]>
{
	private int[] next;
	private BEPNodes<?> nodes;
	private Iterator<int[]> keys;
	
	/**
	 * Creates a new {@code BEPKeys}.
	 * 
	 * @param s  a source tree
	 * @param t  a target value
	 * 
	 * 
	 * @see BEPTree
	 */
	public BEPKeys(BEPTree<?> s, Enum<?> t)
	{
		keys = new EmptyIterator<>();
		nodes = new BEPNodes<>(s, t);
		next = findNext();
	}
	
	
	private int[] findNext()
	{
		if(keys.hasNext())
		{
			return keys.next();
		}
		
		if(nodes.hasNext())
		{
			BEPNode<?> node = nodes.next();
			if(node.isLeaf())
			{
				keys = node.Keys().iterator();
			}
			
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
	public int[] next()
	{
		int[] curr = next;
		next = findNext();
		return curr;
	}
}