package waffles.utils.sets.utilities.rooted;

import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.tools.patterns.basic.Clearable;
import waffles.utils.tools.primitives.Array;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Node} defines the atomic structure of a {@code Tree}.
 * The {@code Hierarchy} implementation is extended with an array of
 * child nodes. This class implements {@code Nodal} to allow nodes to serve
 * as tree objects themselves. Note that setting a child into the node
 * automatically assigns it as its parent.
 * 
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.0
 *
 *
 * @see Hierarchy
 * @see Clearable
 * @see Nodal
 */
public class Node extends Hierarchy implements Clearable, Nodal
{
	private Nodal[] chld;
		
	/**
	 * Creates a new {@code Node}.
	 * 
	 * @param n  a source nodal
	 * 
	 * 
	 * @see Nodal
	 */
	public Node(Nodal n)
	{
		super(n); chld = new Nodal[0];
	}
	
	/**
	 * Creates a new {@code Node}.
	 * 
	 * @param r  a parent rooted
	 * @param n  a source nodal
	 * 
	 * 
	 * @see Arboreal
	 * @see Nodal
	 */
	public Node(Arboreal r, Nodal n)
	{
		super(r, n); chld = new Nodal[0];
	}
		
	/**
	 * Creates a new {@code Node}.
	 * 
	 * @param r  a rooted set
	 * 
	 * 
	 * @see Arboreal
	 */
	public Node(Arboreal r)
	{
		super(r); chld = new Nodal[0];
	}
	
	/**
	 * Creates a new {@code Node}.
	 */
	public Node()
	{
		super(); chld = new Nodal[0];
	}
	
	
	/**
	 * Returns a child of the {@code Node}.
	 * 
	 * @param i    a nodal index
	 * @return  a child nodal
	 */
	public Nodal Child(int i)
	{
		if(0 <= i && i < chld.length)
		{
			return chld[i];
		}
		
		return null;
	}
		
	/**
	 * Adds a child node to the {@code Node},
	 * in the first null position of the array.
	 * 
	 * @param n  a child nodal
	 * 
	 * 
	 * @see Nodal
	 */
	public void addChild(Nodal n)
	{
		if(n != null)
		{
			Node a = n.Arch();
			Nodal d = Delegate();
			for(int k = 0; k < chld.length; k++)
			{
				if(chld[k] == null)
				{
					a.setParent(d);
					chld[k] = n;
					return;
				}
			}
			
			chld = Array.add.to(chld, n);
			a.setParent(d);
		}
	}
		
	/**
	 * Changes a child inside the {@code Node}.
	 * 
	 * @param i  a child index
	 * @param n  a child nodal
	 * 
	 * 
	 * @see Nodal
	 */
	public void setChild(int i, Nodal n)
	{
		if(chld.length <= i)
		{
			chld = Array.copy.of(chld, i + 1);
		}
		
		chld[i] = n;
		if(n != null)
		{
			Nodal d = Delegate();
			n.Arch().setParent(d);
		}
	}
		
	/**
	 * Removes a child from the {@code Node}.
	 * Specifically, the first occurrence of
	 * the nodal in the array is removed.
	 * 
	 * @param n  a child nodal
	 * 
	 * 
	 * @see Nodal
	 */
	public void removeChild(Nodal n)
	{
		for(int i = 0; i < chld.length; i++)
		{
			if(chld[i] == n)
			{
				chld = Array.remove.from(chld, i);
				return;
			}
		}
	}

	/**
	 * Replaces this node with a {@code Nodal}.
	 * 
	 * @param n  a replacement nodal
	 * 
	 * 
	 * @see Nodal
	 */
	public void replace(Nodal n)
	{
		if(isRoot())
			Set().setRoot(n);
		else
		{
			Node p = Parent().Arch();
			p.setChild(TreeIndex(), n);
			setParent(null);
		}
	}
	
	/**
	 * Returns the child nodal array.
	 * 
	 * @return  a child nodal array
	 */
	public Nodal[] Children()
	{
		return chld;
	}

	/**
	 * Detaches the {@code Node}.
	 */
	public void detach()
	{
		if(isRoot())
			Set().setRoot(null);
		else
		{
			int idx = TreeIndex();
			Node p = Parent().Arch();
			p.setChild(idx, null);
		}
	}
	
	
	/**
	 * Checks whether this is a leaf {@code Node}.
	 * This returns true when this node has no children.
	 * 
	 * @return  {@code true} if the node is a leaf
	 */
	public boolean isLeaf()
	{
		for(Nodal n : Children())
		{
			if(n != null)
			{
				return false;
			}
		}
		
		return true;
	}
		
	/**
	 * Returns the child count of the {@code Node}.
	 * This only counts direct descendants of the node.
	 * 
	 * @return  a child count
	 */
	public int ChildCount()
	{
		int cnt = 0;
		for(Nodal n : Children())
		{
			if(n != null)
			{
				cnt++;
			}
		}
		
		return cnt;
	}
	
	/**
	 * Returns the tree index of the {@code Node}.
	 * This is the index of the node as a child of its parent.
	 * 
	 * @return  a tree index
	 */
	public int TreeIndex()
	{
		int idx = 0;
		// Assumes the iterator starts at zero.
		Node p = Parent().Arch();
		for(Nodal n : p.Children())
		{
			if(n != null)
			{
				if(this == n.Arch())
				{
					return idx;
				}
			}

			idx++;
		}
		
		return -1;
	}
	
	/**
	 * Returns the tree size of the {@code Node}.
	 * This indicates the amount of nodes
	 * under this node plus one.
	 * 
	 * @return  a tree size
	 */
	public int TreeSize()
	{
		int cnt = 1;
		for(Nodal n : Children())
		{
			if(n != null)
			{
				Node c = n.Arch();
				cnt += c.TreeSize();
			}
		}
		
		return cnt;
	}
	
	/**
	 * Returns the level of the {@code Node}.
	 * This indicates the relative depth
	 * of the deepest child node.
	 * 
	 * @return  a tree level
	 */
	public int Level()
	{
		if(!isLeaf())
		{
			int max = 0;
			for(Nodal n : Children())
			{
				if(n != null)
				{
					int l = n.Arch().Level();
					max = Integers.max(max, l);
				}
			}
			
			return 1 + max;
		}
		
		return 0;
	}

	
	@Override
	public void clear()
	{
		chld = new Nodal[0];
	}

	@Override
	public Arboreal.Mutable Set()
	{
		return super.Set();
	}
	
	@Override
	public Nodal Delegate()
	{
		return (Nodal) super.Delegate();
	}
	
	@Override
	public Nodal Parent()
	{
		return (Nodal) super.Parent();
	}
	
	@Override
	public Nodal Root()
	{
		return (Nodal) super.Root();
	}
	
	@Override
	public Node Arch()
	{
		return this;
	}
}