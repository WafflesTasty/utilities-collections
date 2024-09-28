package waffles.utils.sets.trees.binary.search;

import java.util.Comparator;

import waffles.utils.sets.mutable.AtomicSet;

/**
 * A {@code BSTree} defines a standard binary search tree.
 * It extends an {@code IOTree} with add and remove operations.
 *
 * @author Waffles
 * @since 01 Aug 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see AtomicSet
 * @see IOTree
 * @see BSNode
 */
public class BSTree<O> extends IOTree<BSNode<O>, O> implements AtomicSet<O>
{
	/**
	 * Creates a new {@code BSTree}.
	 * 
	 * @param c  a value comparator
	 * 
	 * 
	 * @see Comparator
	 */
	public BSTree(Comparator<O> c)
	{
		super(c);
	}
	
	/**
	 * Creates a new {@code BSTree}.
	 */
	public BSTree()
	{
		super();
	}
	
	
	@Override
	public BSNode<O> Root()
	{
		return (BSNode<O>) super.Root();
	}
	
	@Override
	public BSNode<O> search(O obj)
	{
		return (BSNode<O>) super.search(obj);
	}
	
	@Override
	public BSNode<O> createNode(Object... vals)
	{
		return new BSNode<>(this, (O) vals[0]);
	}
				
	
	@Override
	public void remove(O obj)
	{
		// If the tree has no nodes...
		if(Root() == null)
		{
			// It contains nothing.
			return;
		}
		
		// Search the closest node...
		BSNode<O> node = search(obj);
		int comp = compare(obj, node.Value());
		// If its value is equal...
		if(comp == 0)
		{
			// Delete the node.
			node.delete();
			onDelete(node);
		}
	}
	
	@Override
	public void add(O obj)
	{
		// Create a new child node.
		BSNode<O> child = createNode(obj);
		
		// If the tree has no nodes...
		if(Root() == null)
		{
			// Set it as root.
			setRoot(child);
			onInsert(child);
			return;
		}

		// Search the closest node...
		BSNode<O> node = search(obj);
		int comp = compare(obj, node.Value());
		// If its value is equal, discard the object.
		if(comp == 0) return;

		// Otherwise, set it as closest child.
		if(comp < 0)
		{
			node.setLChild(child);
			onInsert(child);
		}
		else
		{
			node.setRChild(child);
			onInsert(child);
		}
	}
}