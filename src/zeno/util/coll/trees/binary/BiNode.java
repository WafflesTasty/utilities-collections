package zeno.util.coll.trees.binary;

import zeno.util.coll.trees.Tree;

/**
 * The {@code BiNode} class defines a generic binary tree node.
 *
 * @author Zeno
 * @since 01 Aug 2020
 * @version 1.0
 * 
 * 
 * @see Tree
 */
public class BiNode extends Tree.Node
{	
	private BiTree tree;
	
	/**
	 * Creates a new {@code BiNode}.
	 * 
	 * @param tree  a source tree
	 * 
	 * 
	 * @see BiTree
	 */
	public BiNode(BiTree tree)
	{
		this.tree = tree;
	}
	
	
	/**
	 * Returns the  left leaf of the {@code BiNode}.
	 * 
	 * @return  a child node
	 */
	public <N extends BiNode> N LLeaf()
	{
		BiNode lchild = LChild();
		if(lchild != null)
		{
			return lchild.LLeaf();
		}
		
		return (N) this;
	}
		
	/**
	 * Returns the right leaf of the {@code BiNode}.
	 * 
	 * @return  a child node
	 */
	public <N extends BiNode> N RLeaf()
	{
		BiNode rchild = RChild();
		if(rchild != null)
		{
			return rchild.RLeaf();
		}
		
		return (N) this;
	}
	
	
	/**
	 * Returns the prev ordered {@code BiNode}.
	 * 
	 * @return  a previous node
	 */
	public <N extends BiNode> N prev()
	{
		if(LChild() != null)
		{
			return LChild().RLeaf();
		}
		
		BiNode c = this, p = Parent();
		while(p != null)
		{
			if(c.equals(p.LChild()))
			{
				c = p; p = c.Parent();
				continue;
			}
			
			return (N) p;
		}
		
		return null;
	}

	/**
	 * Returns the next ordered {@code BiNode}.
	 * 
	 * @return  a next node
	 */
	public <N extends BiNode> N next()
	{
		if(RChild() != null)
		{
			return RChild().LLeaf();
		}
		
		BiNode c = this, p = Parent();
		while(p != null)
		{
			if(c.equals(p.RChild()))
			{
				c = p; p = c.Parent();
				continue;
			}
			
			return (N) p;
		}
		
		return null;
	}
	
	
	/**
	 * Changes the  left child of the {@code BiNode}.
	 * 
	 * @param child  a child node
	 */
	public void setLChild(BiNode child)
	{
		setChild(0, child);
	}
	
	/**
	 * Changes the right child of the {@code BiNode}.
	 * 
	 * @param child  a child node
	 */
	public void setRChild(BiNode child)
	{
		setChild(1, child);
	}
	
	/**
	 * Replaces this node with a {@code BiNode}.
	 * 
	 * @param node  a replacement node
	 * 
	 * 
	 * @see BiNode
	 */
	public void replace(BiNode node)
	{
		if(!isRoot())
			Parent().setChild(Index(), node);
		else
		{
			node.setParent(null);
			tree.setRoot(node);
		}
	}
	
	
	/**
	 * Deletes the {@code BiNode}.
	 */
	public void delete()
	{
		// Check the node's contents.
		int count = ChildCount();
		BiNode lchild = LChild();
		BiNode rchild = RChild();
		
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
			BiNode next = next();
			
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
	 * Detaches the {@code BiNode}.
	 */
	public void detach()
	{
		if(!isRoot())
			Parent().setChild(Index(), null);
		else
			tree.setRoot(null);
	}
	
	/**
	 * Rotates the {@code BiNode}.
	 */
	public void rotate()
	{
		if(Parent() == null)
		{
			return;
		}

		BiNode parent = Parent();
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
		
	
	/**
	 * Returns the sibling of the {@code BiNode}.
	 * 
	 * @return  a sibling node
	 */
	public BiNode Sibling()
	{
		if(Parent() != null)
		{
			if(equals(Parent().LChild()))
			{
				return Parent().RChild();
			}
			
			return Parent().LChild();
		}
		
		return null;
	}
	
	/**
	 * Returns the  left child of the {@code BiNode}.
	 * 
	 * @return  a left child
	 */
	public BiNode LChild()
	{
		return Child(0);
	}
	
	/**
	 * Returns the right child of the {@code BiNode}.
	 * 
	 * @return  a right child
	 */
	public BiNode RChild()
	{
		return Child(1);
	}
		
	
	@Override
	public BiNode Parent()
	{
		return (BiNode) super.Parent();
	}

	@Override
	public BiNode Root()
	{
		return (BiNode) super.Root();
	}
}