package zeno.util.coll.trees;

import zeno.util.algebra.intervals.Cut;
import zeno.util.coll.Tree;

/**
 * The {@code BiNode} class defines a single node in a {@link BiTree}.
 * 
 * @since Jul 1, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects in the node
 * @see Tree
 */
public class BiNode<O> extends Tree.Branch
{		
	private O object;

	/**
	 * Creates a new {@code BiNode}.
	 * 
	 * @param obj  the node's object
	 * @param tree  the node's tree
	 * @see BiTree
	 */
	public BiNode(O obj, BiTree<O> tree)
	{
		super(tree);
		object = obj;
	}
	
	
	/**
	 * Returns the sibling of the {@code BiNode}.
	 * 
	 * @return  the node's sibling
	 */
	public <N extends BiNode<O>> N Sibling()
	{
		BiNode<O> parent = Parent();
		if(parent == null)
		{
			return null;
		}
		
		if(equals(parent.LChild()))
		{
			return parent.RChild();
		}
		
		return parent.LChild();
	}
	
	/**
	 * Returns the next leaf of the {@code BiNode}.
	 * 
	 * @return  the node's next leaf
	 */
	public <N extends BiNode<O>> N nextLeaf()
	{
		BiNode<O> search = next();
		if(search == null)
		{
			return null;
		}
		
		if(search.ChildCount() != 0)
		{
			return search.next();
		}
		
		return (N) search;
	}
	
	/**
	 * Returns the previous leaf of the {@code BiNode}.
	 * 
	 * @return  the node's previous leaf
	 */
	public <N extends BiNode<O>> N prevLeaf()
	{
		BiNode<O> search = prev();
		if(search == null)
		{
			return null;
		}
		
		if(search.ChildCount() != 0)
		{
			return search.prev();
		}
		
		return (N) search;
	}

	/**
	 * Returns the right child of the {@code BiNode}.
	 * 
	 * @return  the node's right child
	 */
	public <N extends BiNode<O>> N RChild()
	{
		return (N) Child(1);
	}

	/**
	 * Returns the left child of the {@code BiNode}.
	 * 
	 * @return  the node's left child
	 */
	public <N extends BiNode<O>> N LChild()
	{
		return (N) Child(0);
	}
	
	/**
	 * Returns the right leaf of the {@code BiNode}.
	 * 
	 * @return  the node's right leaf
	 */
	public <N extends BiNode<O>> N RLeaf()
	{
		BiNode<O> rchild = RChild();
		if(rchild != null)
		{
			return rchild.RLeaf();
		}
		
		return (N) this;
	}
	
	/**
	 * Returns the left leaf of the {@code BiNode}.
	 * 
	 * @return  the node's left leaf
	 */
	public <N extends BiNode<O>> N LLeaf()
	{
		BiNode<O> lchild = LChild();
		if(lchild != null)
		{
			return lchild.LLeaf();
		}
		
		return (N) this;
	}

	/**
	 * Returns the previous ordered {@code BiNode}.
	 * 
	 * @return  the previous node
	 */
	public <N extends BiNode<O>> N prev()
	{
		BiNode<O> lchild = LChild();
		if(lchild != null)
		{
			return lchild.RLeaf();
		}
		
		return (N) findPrev();
	}

	/**
	 * Returns the next ordered {@code BiNode}.
	 * 
	 * @return  the next node
	 */
	public <N extends BiNode<O>> N next()
	{
		BiNode<O> rchild = RChild();
		if(rchild != null)
		{
			return rchild.LLeaf();
		}
		
		return (N) findNext();
	}
	
	
	/**
	 * Checks if the {@code BiNode} is a right child.
	 * 
	 * @return  {@code true} if it's a right child
	 */
	public boolean isRChild()
	{
		if(Parent() != null)
		{
			return ((BiNode<Cut>) Parent()).RChild() == this;
		}
		
		return false;
	}
	
	/**
	 * Checks if the {@code BiNode} is a left child.
	 * 
	 * @return  {@code true} if it's a left child
	 */
	public boolean isLChild()
	{
		if(Parent() != null)
		{
			return ((BiNode<Cut>) Parent()).LChild() == this;
		}
		
		return false;
	}
	
	/**
	 * Returns the object of the {@code BiNode}.
	 * 
	 * @return  the node's object
	 */
	public O Object()
	{
		return object;
	}
	
	
	/**
	 * Changes the left child of the {@code BiNode}.
	 * 
	 * @param node  a new left child
	 */
	public void setLChild(BiNode<O> node)
	{
		setChild(0, node);
	}

	/**
	 * Changes the right child of the {@code BiNode}.
	 * 
	 * @param node  a new right child
	 */
	public void setRChild(BiNode<O> node)
	{
		setChild(1, node);
	}

	/**
	 * Changes the object of the {@code BiNode}.
	 * 
	 * @param object  a new object
	 */
	public void setObject(O object)
	{
		this.object = object;
	}

	
	/**
	 * Deletes the {@code BiNode} from the tree.
	 * <br> This method maintains the order of the tree
	 * by redistributing the node's children.
	 */
	public void delete()
	{			
		// Check the node's contents.
		int count = ChildCount();
		BiNode<O> lchild = LChild();
		BiNode<O> rchild = RChild();
		
		// If the node has no children...
		if(count == 0)
		{
			// Remove it from its parent.
			replace(null);
			return;
		}
		
		// If the node has one child...
		if(count == 1)
		{
			// Replace it by its child.
			if(lchild != null) replace(lchild);
			if(rchild != null) replace(rchild);

			return;
		}
		
		// If the node has two children...
		if(count == 2)
		{
			// Find the next node in order.
			BiNode<O> next = next();
			
			// Update the node's children.
			next.setLChild(lchild);
			if(!next.equals(rchild))
			{
				next.setRChild(rchild);
			}
			

			// Replace it with the next.
			replace(next);
		}
	}

	/**
	 * Rotates the {@code BiNode} in the tree.
	 */
	public void rotate()
	{
		BiNode<O> parent = Parent();
		if(parent == null)
		{
			return;
		}
		
		if(equals(parent.LChild()))
		{
			replace(RChild());
			parent.replace(this);
			setRChild(parent);
			return;
		}
		
		replace(LChild());
		parent.replace(this);
		setLChild(parent);
		return;
	}
	
		
	@Override
	public BiNode<O> instance()
	{
		return new BiNode<>();
	}

	@Override
	public BiNode<O> Parent()
	{
		return (BiNode<O>) super.Parent();
	}
	
	@Override
	public BiNode<O> copy()
	{
		BiNode<O> copy = (BiNode<O>) super.copy();
		copy.setObject(object);
		return copy;
	}
	
	
	BiNode<O> findPrev()
	{
		BiNode<O> parent = Parent();
		if(parent == null)
		{
			return null;
		}
		
		if(equals(parent.RChild()))
		{
			return parent;
		}
		
		return parent.findPrev();
	}
	
	BiNode<O> findNext()
	{
		BiNode<O> parent = Parent();
		if(parent == null)
		{
			return null;
		}
		
		if(equals(parent.LChild()))
		{
			return parent;
		}
		
		return parent.findNext();
	}
	
	BiNode()
	{
		this(null, null);
	}
}