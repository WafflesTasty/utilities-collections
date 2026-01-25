package waffles.utils.sets.utilities.rooted;

import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.arboreal.Tree;
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
	private Nodal[] children;
		
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
		super(n); children = new Nodal[0];
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
		super(r, n); children = new Nodal[0];
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
		super(r); children = new Nodal[0];
	}
	
	/**
	 * Creates a new {@code Node}.
	 */
	public Node()
	{
		super(); children = new Nodal[0];
	}
	
	
	/**
	 * Returns a child of the {@code Node}.
	 * 
	 * @param i    a nodal index
	 * @return  a child nodal
	 */
	public Nodal Child(int i)
	{
		if(0 <= i && i < children.length)
		{
			return children[i];
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
		for(int i = 0; i < children.length; i++)
		{
			if(children[i] == null)
			{
				children[i] = n;
				Node node = n.Arch();
				node.setParent(Delegate());
				return;
			}
		}
		
		children = Array.add.to(children, n);
		if(n != null)
		{
			Node node = n.Arch();
			node.setParent(Delegate());
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
		if(children.length <= i)
		{
			children = Array.copy.of(children, i + 1);
		}
		
		children[i] = n;
		if(n != null)
		{
			Node node = n.Arch();
			node.setParent(Delegate());
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
		for(int i = 0; i < children.length; i++)
		{
			if(children[i] == n)
			{
				children = Array.remove.from(children, i);
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
			Node pNode = Parent().Arch();
			pNode.setChild(TreeIndex(), n);
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
		return children;
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
			Node parent = Parent().Arch();
			parent.setChild(TreeIndex(), null);
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
		for(Nodal node : Children())
		{
			if(node != null)
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
		int count = 0;
		for(Nodal node : Children())
		{
			if(node != null)
			{
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * Returns the tree index of the {@code Node}.
	 * This is the index of the node as a child of its parent.
	 * 
	 * @return  a tree index
	 */
	public int TreeIndex()
	{
		int index = 0;
		// Assumes the iterator starts at zero.
		Node parent = (Node) Parent().Arch();
		for(Nodal nodal : parent.Children())
		{
			if(nodal != null)
			{
				if(this == nodal.Arch())
					return index;
			}

			index++;
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
		int count = 1;
		for(Nodal nodal : Children())
		{
			if(nodal != null)
			{
				Node child = (Node) nodal.Arch();
				count += child.TreeSize();
			}
		}
		
		return count;
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
			for(Nodal nodal : Children())
			{
				if(nodal != null)
				{
					Node child = (Node) nodal.Arch();
					max = Integers.max(max, child.Level());
				}
			}
			
			return 1 + max;
		}
		
		return 0;
	}

	
	@Override
	public void clear()
	{
		children = new Nodal[0];
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
	
	@Override
	public Tree Set()
	{
		return super.Set();
	}
}