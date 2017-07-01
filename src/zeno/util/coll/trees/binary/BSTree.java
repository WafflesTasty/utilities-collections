package zeno.util.coll.trees.binary;

import java.util.Comparator;

import zeno.util.coll.trees.BiNode;
import zeno.util.coll.trees.BiTree;

/**
 * The {@code BSTree} class defines a basic binary search tree.
 * 
 * @since Jul 2, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects in the tree
 * @see BiTree
 */
public class BSTree<O> extends BiTree<O>
{
	/**
	 * Creates a new {@code BSTree}.
	 * <br> Assumes natural object order.
	 */
	public BSTree()
	{
		super();
	}
	
	/**
	 * Creates a new {@code BSTree}.
	 * 
	 * @param c  a comparator to use
	 * @see Comparator
	 */
	public BSTree(Comparator<O> c)
	{
		super(c);
	}

	
	/**
	 * Creates a new node for the {@code BSTree}.
	 * 
	 * @param object  a node object
	 * @return  a new node
	 * @see BiNode
	 */
	public BiNode<O> create(O object)
	{
		return new BiNode<>(object, this);
	}
	
	/**
	 * Deletes an object from the {@code BSTree}.
	 * 
	 * @param object  an object to delete
	 * @return  the object's old node
	 * @see BiNode
	 */
	public BiNode<O> delete(O object)
	{
		// No null refs allowed.
		if(object == null)
		{
			return null;
		}
				
		// Find the closest node...
		BiNode<O> search = search(object);
		if(search == null)
		{
			// The tree is empty.
			// Don't do anything.
			return null;
		}
				
		int comp = compare(object, search.Object());
		if(comp == 0)
		{
			// The node contains the object.
			// Delete the node.
			search.delete();
			return search;
		}
				
		return null;
	}
	
	/**
	 * Inserts an object into the {@code BSTree}.
	 * 
	 * @param object  an object to insert
	 * @return  the object's new node
	 * @see BiNode
	 */
	public BiNode<O> insert(O object)
	{
		// No null refs allowed.
		if(object == null)
		{
			return null;
		}

		// Create a new node.
		BiNode<O> node = create(object);
				
		// Find the closest node...
		BiNode<O> search = search(object);
		if(search == null)
		{
			// The tree is empty.
			// Add the object as root.
			setRoot(node);
			return node;
		}
				
		int comp = compare(object, search.Object());
		if(comp == 0)
		{
			// The object is already present.
			// Don't insert it.
			return null;
		}
				
		if(comp < 0)
		{
			// The object is lower than the node.
			// Insert it as a left child.
			search.setLChild(node);
			return node;
		}
				
		// The object is higher than the node.
		// Insert it as a right child.
		search.setRChild(node);	
		return node;
	}

		
	@Override
	public BSTree<O> instance()
	{
		return new BSTree<>();
	}
}