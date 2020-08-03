package zeno.util.coll.trees.binary.trackers;

import zeno.util.coll.trees.binary.BSNode;
import zeno.util.coll.trees.binary.BSTree;

/**
 * The {@code Tracker} interface handles changes to a {@code BSTree}.
 *
 * @author Zeno
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
}