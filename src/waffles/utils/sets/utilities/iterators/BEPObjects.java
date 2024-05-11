package waffles.utils.sets.utilities.iterators;

import java.util.Iterator;

import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.trees.indexed.BEPTree;

/**
 * A {@code BEPObjects} iterator iterates over all objects along a {@code BEPTree} of the same value.
 *
 * @author Waffles
 * @since 21 Sep 2023
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see Iterator
 */
public class BEPObjects<O> implements Iterator<O>
{
	private BEPKeys keys;
	private IndexedSet<O> src;
	
	/**
	 * Creates a new {@code BEPKeys}.
	 * 
	 * @param s  an indexed set
	 * @param tree  an enum tree
	 * @param val   an enum value
	 * 
	 * 
	 * @see IndexedSet
	 * @see BEPTree
	 */
	public BEPObjects(IndexedSet<O> s, BEPTree<?> tree, Enum<?> val)
	{
		keys = new BEPKeys(tree, val);
		src = s;
	}


	@Override
	public boolean hasNext()
	{
		return keys.hasNext();
	}

	@Override
	public O next()
	{
		int[] key = keys.next();
		return src.get(key);
	}
}