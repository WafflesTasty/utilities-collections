package waffles.utils.sets.arboreal.binary;

import waffles.utils.sets.utilities.rooted.Node;

/**
 * A {@code BiNode} defines the atomic structure of a binary tree.
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
	 * @param a  a parent arboreal
	 * 
	 * 
	 * @see BiArboreal
	 */
	public BiNode(BiArboreal a)
	{
		super(a);
	}
	
	/**
	 * Creates a new {@code BiNode}.
	 * 
	 * @param a  a parent arboreal
	 * @param n  a source object
	 * 
	 * 
	 * @see BiArboreal
	 * @see BiNodal
	 */
	public BiNode(BiArboreal a, BiNodal n)
	{
		super(a, n);
	}
		
	
	/**
	 * Changes the  left child of the {@code BiNode}.
	 * 
	 * @param c  a child nodal
	 * 
	 * 
	 * @see BiNodal
	 */
	public void setLChild(BiNodal c)
	{
		setChild(0, c);
	}
	
	/**
	 * Changes the right child of the {@code BiNode}.
	 * 
	 * @param c  a child nodal
	 * 
	 * 
	 * @see BiNodal
	 */
	public void setRChild(BiNodal c)
	{
		setChild(1, c);
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
			// Detach it from its parent.
			detach(); return;
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
			if(!next.equals(rchild))
			{
				next.Arch().setRChild(rchild);
			}
			
			// Replace it with the next.
			next.Arch().detach();
			replace(next);
		}
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

		BiNode p = Parent().Arch();
		if(Delegate().equals(p.LChild()))
		{
			replace(Parent());
			p.setLChild(RChild());
			setRChild(Parent());
			return;
		}
				
		replace(Parent());
		p.setRChild(LChild());
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
	 * Returns the left child of the {@code BiNode}.
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
	public BiArboreal.Mutable Set()
	{
		return (BiArboreal.Mutable) super.Set();
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