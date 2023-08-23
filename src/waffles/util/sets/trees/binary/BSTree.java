package waffles.util.sets.trees.binary;

import java.util.Comparator;
import java.util.Iterator;

import waffles.util.sets.atomic.MutableSet;
import waffles.util.sets.trees.binary.search.Tracker;
import waffles.util.sets.trees.binary.search.ValueIterator;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * The {@code BSTree} class defines a standard binary search tree.
 * </br> A comparator can be provided for the tree values. If no comparator is given, it assumes natural ordering.
 * </br> A tracker can be provided to perform dynamic rebalancing. If no tracker is given, no balancing is done.
 *
 * @author Waffles
 * @since 01 Aug 2020
 * @version 1.1
 * 
 * 
 * @param <O>  a tree object type
 * @see MutableSet
 * @see Comparator
 * @see Tracker
 * @see BiTree
 */
public class BSTree<O> extends BiTree<O> implements MutableSet<O>, Comparator<O>, Tracker<O>
{
	private Tracker<O> tracker;
	private Comparator<O> compare;
	
	/**
	 * Creates a new {@code BSTree}.
	 * 
	 * @param c  a value comparator
	 * @param t  a balance tracker
	 * 
	 * 
	 * @see Comparator
	 * @see Tracker
	 */
	public BSTree(Comparator<O> c, Tracker<O> t)
	{
		compare = c;
		tracker = t;
	}
	
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
		this(c, new Tracker<>(){});
	}
	
	/**
	 * Creates a new {@code BSTree}.
	 * 
	 * @param t  a balance tracker
	 * 
	 * 
	 * @see Tracker
	 */
	public BSTree(Tracker<O> t)
	{
		this(null, t);
	}
	
	/**
	 * Creates a new {@code BSTree}.
	 */
	public BSTree()
	{
		this(null, new Tracker<>(){});
	}

	
	/**
	 * Searches the {@code BSTree} for the node
	 * closest to a given object.
	 * 
	 * @param obj  an object to search with
	 * @return  the closest tree node
	 * 
	 * 
	 * @see BSNode
	 */
	public BSNode<O> search(O obj)
	{
		// No null refs allowed.
		if(obj == null)
		{
			return null;
		}
				
		BSNode<O> node = Root();
		// Start checking from root...
		while(true)
		{
			int comp = compare(obj, node.Value());
			
			// The value has been found.
			if(comp == 0)
			{
				return node;
			}
			
			// The value is lower than the node...
			if(comp < 0)
			{
				// ...but it's the closest one found.
				if(node.LChild() == null)
				{
					return node;
				}
				
				// ...so continue with its left child.
				node = node.LChild();
				continue;
			}
			
			// The value is higher than the node...
			if(comp > 0)
			{
				// ...but it's the closest one found.
				if(node.RChild() == null)
				{
					return node;
				}
				
				// ...so continue with its right child.
				node = node.RChild();
				continue;
			}
		}
	}
	
	@Override
	public Iterable<BSNode<O>> inorder()
	{
		return super.inorder();
	}
	
	@Override
	public Iterable<BSNode<O>> postorder()
	{
		return super.postorder();
	}
	
	@Override
	public Iterable<BSNode<O>> preorder()
	{
		return super.preorder();
	}
	
	@Override
	public Iterator<O> iterator()
	{
		if(Root() == null)
			return new EmptyIterator<>();
		return new ValueIterator<>(this);
	}
	
	
	@Override
	public BSNode<O> create(Object... vals)
	{
		return new BSNode<>(this, (O) vals[0]);
	}
	
	@Override
	public BSNode<O> Root()
	{
		return (BSNode<O>) super.Root();
	}

	
	@Override
	public void clear()
	{
		setRoot(null);
		tracker.onClear(this);
	}
	
	@Override
	public int compare(O o1, O o2)
	{
		if(compare != null)
		{
			return compare.compare(o1, o2);
		}
		
		return ((Comparable<O>) o1).compareTo(o2);
	}
	
	@Override
	public void onClear(BSTree<O> tree)
	{
		if(tracker != null)
		{
			tracker.onClear(tree);
		}
	}
	
	@Override
	public void onDelete(BSNode<O> node)
	{
		if(tracker != null)
		{
			tracker.onDelete(node);
		}
	}
	
	@Override
	public void onInsert(BSNode<O> node)
	{
		if(tracker != null)
		{
			tracker.onInsert(node);
		}
	}
	
	@Override
	public void onUpdate(BSTree<O> tree)
	{
		if(tracker != null)
		{
			tracker.onUpdate(tree);
		}
	}
	
	@Override
	public boolean contains(O obj)
	{
		// If the tree has no nodes...
		if(Root() == null)
		{
			// It contains nothing.
			return false;
		}
		
		// Search the closest node...
		BSNode<O> node = search(obj);
		int comp = compare(obj, node.Value());
		// And check if its value is equal.
		return comp == 0;
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
		// If the tree has no nodes...
		if(Root() == null)
		{
			// Create a root node.
			setRoot(create(obj));
			onInsert(Root());
			return;
		}
		
		// Search the closest node...
		BSNode<O> node = search(obj);
		int comp = compare(obj, node.Value());
		// If its value is equal, discard the object.
		if(comp == 0) return;
		// Otherwise, add it to the closest child.
		if(comp < 0)
		{
			node.setLChild(create(obj));
			onInsert(node.LChild());
		}
		else
		{
			node.setRChild(create(obj));
			onInsert(node.RChild());
		}
	}

	@Override
	public int Count()
	{
		if(Root() == null) return 0;
		return Root().TreeSize();
	}	
}