package waffles.utils.sets.rooted.binary.search;

import java.util.Comparator;
import java.util.Iterator;

import waffles.utils.sets.IterableSet;
import waffles.utils.sets.rooted.binary.BiTree;
import waffles.utils.sets.rooted.binary.balance.Balance;
import waffles.utils.sets.rooted.binary.balance.BalanceTree;
import waffles.utils.sets.utilities.rooted.Nodal;
import waffles.utils.tools.collections.iterators.EmptyIterator;
import waffles.utils.tools.collections.iterators.ValueIterator;

/**
 * An {@code IOTree} defines a binary tree which keeps a set of values in order.
 * A {@code Balance} can be provided, which can optimize binary tree searches.
 * A {@code Comparator} can be provided, to assign an order to the values.
 * If no {@code Comparator} is given, natural ordering is assumed.
 *
 * @author Waffles
 * @since 01 Aug 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @param <N>  a node type
 * @see BalanceTree
 * @see IterableSet
 * @see Comparator
 */
public class IOTree<N extends IONode<O>, O> extends BiTree implements BalanceTree<N>, Comparator<O>, IterableSet<O>
{
	private Balance<N> balance;
	private Comparator<O> comp;

	/**
	 * Creates a new {@code IOTree}.
	 * 
	 * @param c  a value comp
	 * 
	 * 
	 * @see Comparator
	 */
	public IOTree(Comparator<O> c)
	{
		comp = c;
	}
	
	/**
	 * Creates a new {@code IOTree}.
	 */
	public IOTree()
	{
		this(null);
	}

			
	/**
	 * Searches the {@code IOTree} for the node
	 * closest to a given object.
	 * 
	 * @param obj  an object to search with
	 * @return  the closest tree node
	 */
	public N search(O obj)
	{
		// No null refs allowed.
		if(obj == null)
		{
			return null;
		}
				
		IONode<O> node = Root();
		// Start checking from root...
		while(node != null)
		{
			int comp = compare(obj, node.Value());
			
			// The value has been found.
			if(comp == 0)
			{
				return (N) node;
			}
			
			// The value is lower than the node...
			if(comp < 0)
			{
				// ...but it's the closest one found.
				if(node.LChild() == null)
				{
					return (N) node;
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
					return (N) node;
				}
				
				// ...so continue with its right child.
				node = node.RChild();
				continue;
			}
		}
		
		return null;
	}	
				
	/**
	 * Changes the balance of the {@code IOTree}.
	 * 
	 * @param bal  a tree balance
	 * 
	 * 
	 * @see Balance
	 */
	public void setBalance(Balance<N> bal)
	{
		balance = bal;
	}	
	
	/**
	 * Compares two nodes in the {@code IOTree}.
	 * 
	 * @param n1  a tree node
	 * @param n2  a tree node
	 * @return  a comparator
	 * 
	 * 
	 * @see IONode
	 */
	public int compare(IONode<O> n1, IONode<O> n2)
	{
		return compare(n1.Value(), n2.Value());
	}
	
	
	@Override
	public IONode<O> createNode(Object... vals)
	{
		return new IONode<>(this, (O) vals[0]);
	}
	
	@Override
	public void setRoot(Nodal root)
	{
		// Fire the clear event,
		// before firing the
		// insert event.
		
		onClear();
		super.setRoot(root);
		onInsert((N) root);
	}
	
	@Override
	public N Root()
	{
		return (N) super.Root();
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
		IONode<O> node = search(obj);
		int comp = compare(obj, node.Value());
		// And check if its value is equal.
		return comp == 0;
	}

	@Override
	public int compare(O o1, O o2)
	{
		if(comp != null)
		{
			return comp.compare(o1, o2);
		}
		
		return ((Comparable<O>) o1).compareTo(o2);
	}

	@Override
	public Iterator<O> iterator()
	{
		if(Root() == null)
		{
			return new EmptyIterator<>();
		}
		
		Iterable<IONode<O>> nodes = inorder();
		return new ValueIterator<>(nodes);
	}
	
	@Override
	public Balance<N> Balance()
	{
		return balance;
	}
	
	
	@Override
	public void clear()
	{
		super.clear();
		onClear();
	}
	
	@Override
	public int Count()
	{
		if(Root() == null) return 0;
		return Root().TreeSize();
	}	
}