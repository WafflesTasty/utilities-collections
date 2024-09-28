package waffles.utils.sets.trees.indexed.enums;

import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.trees.indexed.BEPNode;
import waffles.utils.sets.trees.indexed.BEPTree;
import waffles.utils.sets.utilities.Activity;

/**
 * An {@code ActionTree} implements a {@code BEPTree} with the two-state {@code Activity} enum.
 * This tree can be used to quickly search over indexed sets which are
 * divided into active and idle objects.
 *
 * @author Waffles
 * @since 11 May 2024
 * @version 1.1
 *
 * 
 * @see Activity
 * @see BEPTree
 */
public class ActionTree extends BEPTree<Activity>
{
	/**
	 * Creates a new {@code ActionTree}.
	 * 
	 * @param dims  an index dimension
	 */
	public ActionTree(int... dims)
	{
		super(dims);
		clear();
	}
	
	
	/**
	 * Iterates over active nodes in the {@code ActionTree}.
	 * 
	 * @return  a node iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BEPNode
	 */
	public <N extends BEPNode<Activity>> Iterable<N> ActiveNodes()
	{
		return Nodes(Activity.ACTIVE);
	}
	
	/**
	 * Iterates over active objects along the {@code ActionTree}.
	 * 
	 * @param <O>  an object type
	 * @param src  an indexed set
	 * @return  an object iterable
	 * 
	 * 
	 * @see IndexedSet
	 * @see Iterable
	 */
	public <O> Iterable<O> ActiveObjects(IndexedSet<O> src)
	{
		return Objects(src, Activity.ACTIVE);
	}
	
	/**
	 * Iterates over active keys in the {@code ActionTree}.
	 * 
	 * @return  a key iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public Iterable<int[]> ActiveKeys()
	{
		return Keys(Activity.ACTIVE);
	}

	
	@Override
	public void clear()
	{
		super.clear();
		fill(Activity.IDLE);
	}
}