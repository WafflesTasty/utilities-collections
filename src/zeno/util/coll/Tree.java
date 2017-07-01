package zeno.util.coll;

import zeno.util.tools.generic.properties.Copyable;
import zeno.util.tools.patterns.Node;

/**
 * The {@code Tree} class defines the base for data tree structures.
 * 
 * @since Jun 30, 2017
 * @author Zeno
 * 
 * @see Copyable
 */
public class Tree implements Copyable<Tree>
{		
	/**
	 * The {@code Branch} class defines a single node in a {@code Tree}.
	 * 
	 * @since Jun 30, 2017
	 * @author Zeno
	 * 
	 * @see Node
	 * @see Tree
	 */
	public static class Branch extends Node
	{
		private Tree tree;
				
		/**
		 * Creates a new {@code Branch}.
		 * 
		 * @param tree  a source tree
		 */
		public Branch(Tree tree)
		{
			this.tree = tree;
		}
				
		
		/**
		 * Replaces the {@code Branch}.
		 * 
		 * @param b  a branch to replace with
		 */
		public void replace(Branch b)
		{
			if(isRoot())
			{
				tree.setRoot(b);
				return;
			}
			
			int id = Index();
			Branch parent = Parent();
			parent.setChild(id, b);
			setParent(null);
			return;
		}
		
		/**
		 * Changes a child in the {@code Branch}.
		 * 
		 * @param i  the child's index
		 * @param b  a new child branch
		 */
		public void setChild(int i, Branch b)
		{
			Tracker track = tree.Tracker();
			Branch child = Child(i);
			if(child != null)
			{
				track.onDelete(child);
			}
			
			super.setChild(i, b);
			if(b != null)
			{
				track.onInsert(b);
				b.setTree(tree);
			}
		}
							
		/**
		 * Changes the tree of the {@code Branch}.
		 * 
		 * @param tree  a new tree
		 * @see Tree
		 */
		public void setTree(Tree tree)
		{
			this.tree = tree;
		}
				
		/**
		 * Returns the tree of the {@code Branch}.
		 * 
		 * @return  the branch's tree
		 * @see Tree
		 */
		public Tree Tree()
		{
			return tree;
		}
		
	
		@Override
		public Branch Child(int i)
		{
			return (Branch) super.Child(i);
		}
		
		@Override
		public Branch instance()
		{
			return new Branch();
		}
		
		@Override
		public Branch Parent()
		{
			return (Branch) super.Parent();
		}
		
		@Override
		public Branch copy()
		{
			return (Branch) super.copy();
		}
	
		
		Branch()
		{
			this(null);
		}
	}
	
	/**
	 * The {@code Tracker} class tracks changes made to a {@code Tree}.
	 * 
	 * @since Jul 1, 2017
	 * @author Zeno
	 */
	public static class Tracker
	{
		private int size;
		private Tree tree;
		
		/**
		 * Creates a new {@code Tracker}.
		 * 
		 * @param t  a source tree
		 * @see Tree
		 */
		public Tracker(Tree t)
		{
			size = t.Root().NodeCount();
			tree = t;
		}
		
		/**
		 * Returns the size in the {@code Tracker}.
		 * 
		 * @return  the tracker's tree size
		 */
		public int TreeSize()
		{
			return size;
		}
		
		/**
		 * Returns the tree of the {@code Tracker}.
		 * 
		 * @return  the tracker's tree
		 * @see Tree
		 */
		public Tree Tree()
		{
			return tree;
		}
		
		
		/**
		 * An event raised on deleting a {@code Branch}.
		 * 
		 * @param b  the branch being deleted
		 * @see Branch
		 */
		public void onDelete(Branch b)
		{
			size -= b.NodeCount();
		}
		
		/**
		 * An event raised on inserting a {@code Branch}.
		 * 
		 * @param b  the branch being inserted
		 * @see Branch
		 */
		public void onInsert(Branch b)
		{
			size += b.NodeCount();
		}
				
		/**
		 * An event raised on clearing the {@code Tree}.
		 */
		public void onClear()
		{
			size = 0;
		}
	
		/**
		 * Updates the {@code Tracker}.
		 */
		public void update()
		{
			// NOT APPLICABLE
		}
	}


	private Branch root;
	private Tracker tracker;
	
	/**
	 * Checks if the {@code Tree}'s empty.
	 * 
	 * @return  {@code true} if the tree is empty
	 */
	public boolean isEmpty()
	{
		return root == null;
	}
	
	/**
	 * Returns the {@code Tree}'s tracker.
	 * 
	 * @return  the tree's tracker
	 * @see Tracker
	 */
	public Tracker Tracker()
	{
		return tracker;
	}
			
	/**
	 * Returns the root of the {@code Tree}.
	 * 
	 * @return  the tree's root
	 * @see Branch
	 */
	public Branch Root()
	{
		return root;
	}

	/**
	 * Returns the size of the {@code Tree}.
	 * 
	 * @return  the tree's size
	 */
	public int Size()
	{
		return tracker.TreeSize();
	}
	
	
	/**
	 * Changes the tracker of the {@code Tree}.
	 * 
	 * @param t  a new tracker
	 * @see Tracker
	 */
	public void setTracker(Tracker t)
	{
		if(t == null)
			tracker = new Tracker(this);
		else
			tracker = t;
	}
	
	/**
	 * Changes the root of the {@code Tree}.
	 * 
	 * @param b  a new root
	 * @see Branch
	 */
	public void setRoot(Branch b)
	{
		clear();
		
		root = b;
		if(root != null)
		{
			root.setTree(this);
			root.setParent(null);
			tracker.onInsert(b);
		}
	}
	
	/**
	 * Clears the {@code Tree}.
	 */
	public void clear()
	{
		tracker.onClear();
		root = null;
	}
	
	
	@Override
	public Tree instance()
	{
		return new Tree();
	}
	
	@Override
	public Tree copy()
	{
		Tree copy = Copyable.super.copy();
		if(root != null)
		{
			copy.setRoot(root.copy());
		}
		
		return copy;
	}
}