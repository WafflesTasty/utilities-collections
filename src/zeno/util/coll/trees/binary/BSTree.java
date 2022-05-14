package zeno.util.coll.trees.binary;

import java.util.Comparator;
import java.util.Iterator;

import zeno.util.coll.Collection;
import zeno.util.coll.trees.binary.trackers.Tracker;
import zeno.util.tools.helper.iterators.EmptyIterator;

/**
 * The {@code BSTree} class defines a standard binary search tree.
 * </br> A comparator can be provided for the tree values. If no comparator is given, it assumes natural ordering.
 * </br> A tracker can be provided to perform dynamic rebalancing. If no tracker is given, on balancing is done.
 *
 * @author Zeno
 * @since 01 Aug 2020
 * @version 1.0
 * 
 * 
 * @param <V>  a value type
 * @see Collection
 * @see Comparator
 * @see Tracker
 * @see BiTree
 */
public class BSTree<V> extends BiTree implements Collection<V>, Comparator<V>, Tracker<V>
{
	/**
	 * The {@code Values} class iterates over all values in a {@code BSTree}.
	 *
	 * @author Zeno
	 * @since 01 Aug 2020
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 */
	public class Values implements Iterator<V>
	{
		private Iterator<BSNode<V>> iNodes = inorder().iterator();
		
		@Override
		public boolean hasNext()
		{
			return iNodes.hasNext();
		}

		@Override
		public V next()
		{
			return iNodes.next().Value();
		}
	}
	
	
	private Tracker<V> tracker;
	private Comparator<V> compare;
	
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
	public BSTree(Comparator<V> c, Tracker<V> t)
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
	public BSTree(Comparator<V> c)
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
	public BSTree(Tracker<V> t)
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

	
	protected BSNode<V> search(V obj)
	{
		// No null refs allowed.
		if(obj == null)
		{
			return null;
		}
				
		BSNode<V> node = Root();
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
	public Iterable<BSNode<V>> inorder()
	{
		return super.inorder();
	}
	
	@Override
	public Iterable<BSNode<V>> postorder()
	{
		return super.postorder();
	}
	
	@Override
	public Iterable<BSNode<V>> preorder()
	{
		return super.preorder();
	}
	
	@Override
	public Iterator<V> iterator()
	{
		if(Root() == null)
			return new EmptyIterator<>();
		return new Values();
	}
	
	
	@Override
	public BSNode<V> create(Object... vals)
	{
		return new BSNode<>(this, (V) vals[0]);
	}
	
	@Override
	public BSNode<V> Root()
	{
		return (BSNode<V>) super.Root();
	}

	
	@Override
	public void clear()
	{
		setRoot(null);
		tracker.onClear(this);
	}
	
	@Override
	public int compare(V o1, V o2)
	{
		if(compare != null)
		{
			return compare.compare(o1, o2);
		}
		
		return ((Comparable<V>) o1).compareTo(o2);
	}
	
	@Override
	public void onClear(BSTree<V> tree)
	{
		if(tracker != null)
		{
			tracker.onClear(tree);
		}
	}
	
	@Override
	public void onDelete(BSNode<V> node)
	{
		if(tracker != null)
		{
			tracker.onDelete(node);
		}
	}
	
	@Override
	public void onInsert(BSNode<V> node)
	{
		if(tracker != null)
		{
			tracker.onInsert(node);
		}
	}
	
	@Override
	public void onUpdate(BSTree<V> tree)
	{
		if(tracker != null)
		{
			tracker.onUpdate(tree);
		}
	}
	
	@Override
	public boolean contains(V obj)
	{
		if(Root() == null)
		{
			return false;
		}
		
		BSNode<V> node = search(obj);
		int comp = compare(obj, node.Value());
		return comp == 0;
	}
	
	@Override
	public void remove(V obj)
	{
		if(Root() == null)
		{
			return;
		}
		
		BSNode<V> node = search(obj);
		int comp = compare(obj, node.Value());
		if(comp == 0)
		{
			node.delete();
			onDelete(node);
		}
	}
	
	@Override
	public void add(V obj)
	{
		if(Root() == null)
		{
			setRoot(create(obj));
			onInsert(Root());
			return;
		}
		
		BSNode<V> node = search(obj);
		int comp = compare(obj, node.Value());
		if(comp == 0) return;
		
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