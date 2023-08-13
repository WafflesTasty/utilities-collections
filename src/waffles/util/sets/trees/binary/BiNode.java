package waffles.util.sets.trees.binary;

import waffles.util.sets.trees.Node;

/**
 * The {@code BiNode} class defines a generic binary tree node.
 * It comes equipped with a variety of useful properties and modifiers
 * that are used in many of the common binary tree algorithms.
 * 
 * @author Waffles
 * @since 01 Aug 2020
 * @version 1.0
 * 
 * 
 * @see BiNodal
 * @see Node
 */
public class BiNode extends Node implements BiNodal
{	
	/**
	 * Creates a new {@code BiNode}.
	 * 
	 * @param tree  a target tree
	 * @param del   a delegate object
	 * 
	 * 
	 * @see BiNodal
	 * @see BiTree
	 */
	public BiNode(BiTree<?> tree, BiNodal del)
	{
		super(tree, del);
	}
	
	/**
	 * Creates a new {@code BiNode}.
	 * 
	 * @param tree  a target tree
	 * 
	 * 
	 * @see BiTree
	 */
	public BiNode(BiTree<?> tree)
	{
		super(tree);
	}
			
	
	/**
	 * Changes the  left child of the {@code BiNode}.
	 * 
	 * @param child  a child nodal
	 * 
	 * 
	 * @see BiNodal
	 */
	public void setLChild(BiNodal child)
	{
		setChild(0, child);
	}
	
	/**
	 * Changes the right child of the {@code BiNode}.
	 * 
	 * @param child  a child nodal
	 * 
	 * 
	 * @see BiNodal
	 */
	public void setRChild(BiNodal child)
	{
		setChild(1, child);
	}
		
	
	/**
	 * Deletes the {@code BiNode}.
	 */
	public void delete()
	{
		// Check the node's contents.
		int count = ChildCount();
		BiNodal lchild = LChild();
		BiNodal rchild = RChild();
		
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
			BiNodal next = next();
			
			// Update the node's children.
			next.Arch().setLChild(lchild);
			lchild.Arch().setParent(next);
			if(!next.equals(rchild))
			{
				next.Arch().setRChild(rchild);
				rchild.Arch().setParent(next);
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
			Parent().Arch().setChild(TreeIndex(), null);
		else
			Set().setRoot(null);
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

		BiNode arch = Parent().Arch();
		if(Delegate().equals(arch.LChild()))
		{
			
			replace(RChild());
			arch.replace(Delegate());
			setRChild(Parent());
			return;
		}
				
		replace(LChild());
		arch.replace(Delegate());
		setLChild(Parent());
		return;
	}
		
	
	/**
	 * Returns the sibling of the {@code BiNode}.
	 * 
	 * @return  a sibling node
	 * 
	 * 
	 * @see BiNodal
	 */
	public BiNodal Sibling()
	{
		if(Parent() != null)
		{
			BiNode arch = Parent().Arch();
			if(Delegate().equals(arch.LChild()))
			{
				return arch.RChild();
			}
			
			return arch.LChild();
		}
		
		return null;
	}
	
	/**
	 * Returns the  left child of the {@code BiNode}.
	 * 
	 * @return  a left child
	 * 
	 * 
	 * @see BiNodal
	 */
	public BiNodal LChild()
	{
		return (BiNodal) Child(0);
	}
	
	/**
	 * Returns the right child of the {@code BiNode}.
	 * 
	 * @return  a right child
	 * 
	 * 
	 * @see BiNodal
	 */
	public BiNodal RChild()
	{
		return (BiNodal) Child(1);
	}
	
	/**
	 * Returns the left leaf of the {@code BiNode}.
	 * 
	 * @return  a child binodal
	 * 
	 * 
	 * @see BiNodal
	 */
	public BiNodal LLeaf()
	{
		if(LChild() != null)
		{
			return LChild().Arch().LLeaf();
		}
		
		return Delegate();
	}
		
	/**
	 * Returns the right leaf of the {@code BiNode}.
	 * 
	 * @return  a child binodal
	 * 
	 * 
	 * @see BiNodal
	 */
	public BiNodal RLeaf()
	{
		if(RChild() != null)
		{
			return RChild().Arch().RLeaf();
		}
		
		return Delegate();
	}
	
	/**
	 * Returns the prev ordered {@code BiNode}.
	 * 
	 * @return  a previous binodal
	 * 
	 * 
	 * @see BiNodal
	 */
	public BiNodal prev()
	{
		if(LChild() != null)
		{
			return LChild().Arch().RLeaf();
		}
		
		BiNodal c = Delegate(), p = Parent();
		while(p != null)
		{
			if(c.equals(p.Arch().LChild()))
			{
				c = p; p = c.Arch().Parent();
				continue;
			}
			
			return p;
		}
		
		return null;
	}

	/**
	 * Returns the next ordered {@code BiNode}.
	 * 
	 * @return  a next node
	 * 
	 * 
	 * @see BiNodal
	 */
	public BiNodal next()
	{
		if(RChild() != null)
		{
			return RChild().Arch().LLeaf();
		}
		
		BiNodal c = Delegate(), p = Parent();
		while(p != null)
		{
			if(c.equals(p.Arch().RChild()))
			{
				c = p; p = c.Arch().Parent();
				continue;
			}
			
			return p;
		}
		
		return null;
	}
	
	
	@Override
	public BiTree<?> Set()
	{
		return (BiTree<?>) super.Set();
	}
	
	@Override
	public BiNodal Delegate()
	{
		return (BiNodal) super.Delegate();
	}
	
	@Override
	public BiNodal Parent()
	{
		return (BiNodal) super.Parent();
	}
	
	@Override
	public BiNodal Root()
	{
		return (BiNodal) super.Root();
	}
	
	@Override
	public BiNode Arch()
	{
		return this;
	}
}