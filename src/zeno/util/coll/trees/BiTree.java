package zeno.util.coll.trees;

import java.util.Comparator;
import java.util.Iterator;

import zeno.util.coll.Tree;
import zeno.util.coll.tools.iterators.binary.InOrder;
import zeno.util.coll.tools.iterators.binary.PostOrder;
import zeno.util.coll.tools.iterators.binary.PreOrder;

/**
 * The {@code BiTree} class defines a basic binary tree structure.
 * <br> The tree is governed by a {@link Comparator} to perform searches
 * through its nodes. If no comparator is provided, natural ordering in
 * its object type is assumed instead.
 * 
 * @since Jul 1, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects in the tree
 * @see Comparator
 * @see Iterable
 * @see Tree
 */
public class BiTree<O> extends Tree implements Comparator<O>, Iterable<O>
{	
	private Comparator<O> comparator;

	/**
	 * Creates a new {@code BiTree}.
	 * 
	 * @param c  a comparator to use
	 * @see Comparator
	 */
	public BiTree(Comparator<O> c)
	{
		comparator = c;
	}

	/**
	 * Creates a new {@code BiTree}.
	 * <br> Assumes natural object order.
	 */
	public BiTree()
	{
		comparator = this;
	}
	
	
	/**
	 * Performs in-order iteration over the {@code BiTree}.
	 * 
	 * @return  an in-order tree iterable
	 * @see Iterable
	 * @see BiNode
	 */
	public Iterable<BiNode<O>> inorder()
	{
		return () -> new InOrder<>(this);
	}
	
	/**
	 * Performs post-order iteration over the {@code BiTree}.
	 * 
	 * @return  a post-order tree iterable
	 * @see Iterable
	 * @see BiNode
	 */
	public Iterable<BiNode<O>> postorder()
	{
		return () -> new PostOrder<>(this);
	}

	/**
	 * Performs pre-order iteration over the {@code BiTree}.
	 * 
	 * @return  a pre-order tree iterable
	 * @see Iterable
	 * @see BiNode
	 */
	public Iterable<BiNode<O>> preorder()
	{
		return () -> new PreOrder<>(this);
	}

	/**
	 * Searches the {@code BiTree} for an object.
	 * <br> The node returned either contains or
	 * is the closest one to the object.
	 * 
	 * @param object  an object to search
	 * @return  the closest node
	 * @see BiNode
	 */
	public BiNode<O> search(O object)
	{
		// No null refs allowed.
		if(object == null)
		{
			return null;
		}
		
		BiNode<O> node = Root();
		
		// The tree is empty.
		if(node == null)
		{
			return null;
		}
		
		// Start checking from root...
		while(true)
		{
			int comp = compare(object, node.Object());
			
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
	public BiNode<O> Root()
	{
		return (BiNode<O>) super.Root();
	}

	@Override
	public Iterator<O> iterator()
	{
		return new Iterator<O>()
		{
			private InOrder<O> inorder = new InOrder<>(BiTree.this);

			@Override
			public boolean hasNext()
			{
				return inorder.hasNext();
			}

			@Override
			public O next()
			{
				return inorder.next().Object();
			}
		};
	}
	
	@Override
	public int compare(O o1, O o2)
	{
		if(comparator != this)
		{
			return comparator.compare(o1, o2);
		}
		
		return ((Comparable<O>) o1).compareTo(o2);
	}
	
	@Override
	public BiTree<O> instance()
	{
		return new BiTree<>();
	}
	
	@Override
	public BiTree<O> copy()
	{
		BiTree<O> copy = (BiTree<O>) super.copy();
		copy.comparator = comparator;
		
		return copy;
	}
}