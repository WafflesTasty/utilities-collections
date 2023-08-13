package waffles.util.sets.trees.binary.search;

import java.util.Iterator;

import waffles.util.sets.trees.binary.BSNode;
import waffles.util.sets.trees.binary.BSTree;

/**
 * A {@code ValueIterator} iterates over the values of a {@code BSTree}.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.0
 *
 *
 * @param <O>  an index object type
 * @see Iterator
 */
public class ValueIterator<O> implements Iterator<O>
{
	private Iterator<BSNode<O>> nodes;
	
	/**
	 * Creates a new {@code ValueIterator}.
	 * 
	 * @param tree  a target tree
	 * 
	 * 
	 * @see BSTree
	 */
	public ValueIterator(BSTree<O> tree)
	{
		nodes = tree.inorder().iterator();
	}

		
	@Override
	public boolean hasNext()
	{
		return nodes.hasNext();
	}
	
	@Override
	public O next()
	{
		return nodes.next().Value();
	}
}