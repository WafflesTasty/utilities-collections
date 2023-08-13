package waffles.util.sets.trees.binary.search;

import waffles.util.sets.trees.binary.BSNode;
import waffles.util.sets.trees.binary.BSTree;

/**
 * The {@code Tracker} interface handles changes to a {@code BSTree}.
 *
 * @author Waffles
 * @since 02 Aug 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 */
public interface Tracker<O>
{	
	/**
	 * An event raised when a node is deleted.
	 * 
	 * @param node  a target node
	 * 
	 * 
	 * @see BSNode
	 */
	public default void onDelete(BSNode<O> node)
	{
		// NOT APPLICABLE
	}
	
	/**
	 * An event raised when a node is inserted.
	 * 
	 * @param node  a target node
	 * 
	 * 
	 * @see BSNode
	 */
	public default void onInsert(BSNode<O> node)
	{
		// NOT APPLICABLE
	}

	/**
	 * An event raised when a tree is updated.
	 * 
	 * @param tree  a target tree
	 * 
	 * 
	 * @see BSTree
	 */
	public default void onUpdate(BSTree<O> tree)
	{
		// NOT APPLICABLE
	}

	/**
	 * An event raised when a tree is cleared.
	 * 
	 * @param tree  a target tree
	 * 
	 * 
	 * @see BSTree
	 */
	public default void onClear(BSTree<O> tree)
	{
		// NOT APPLICABLE
	}
}