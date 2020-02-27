package zeno.util.coll.utilities.relations.nodes;

import zeno.util.coll.utilities.relations.IRelatable;
import zeno.util.tools.helper.Array;
import zeno.util.tools.helper.Iterables;

/**
 * The {@code NodeArray} defines parent-child relations with explicit references in an array.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see INodal
 */
public class NodeArray implements INodal
{
	private INode target, parent;
	private INode[] children;
		
	/**
	 * Creates a new {@code NodeArray}.
	 * 
	 * @param tgt  a target object
	 * 
	 * 
	 * @see INode
	 */
	public NodeArray(INode tgt)
	{
		children = new INode[0];
		target = tgt;
	}
	
	/**
	 * Adds a child to the {@code NodeArray}.
	 * 
	 * @param child  a child to add
	 * 
	 * 
	 * @see INode
	 */
	public void addChild(INode child)
	{
		NodeArray rel = (NodeArray) child.Relations();
		
		rel.setParent(target);		
		for(int i = 0; i < children.length; i++)
		{
			if(children[i] == null)
			{
				children[i] = child;
				return;
			}
		}
		
		children = Array.add.to(children, child);
	}
	
	/**
	 * Removes a child from the {@code NodeArray}.
	 * 
	 * @param child  a child to remove
	 * 
	 * 
	 * @see INode
	 */
	public void removeChild(INode child)
	{
		for(int i = 0; i < children.length; i++)
		{
			if(children[i] == child)
			{
				children[i] = null;
				return;
			}
		}
	}
	
	/**
	 * Changes a child in the {@code NodeArray}.
	 * 
	 * @param i  a child index
	 * @param child  a new child
	 * 
	 * 
	 * @see INode
	 */
	public void setChild(int i, INode child)
	{
		if(children.length <= i)
		{
			children = Array.copy.of(children, i + 1);
		}
		
		children[i] = child;
		if(child != null)
		{
			NodeArray rel = (NodeArray) child.Relations();
			rel.setParent(target);
		}
	}
	
	/**
	 * Changes the parent of the {@code NodeArray}.
	 * 
	 * @param parent  a new parent
	 * 
	 * 
	 * @see INode
	 */
	public void setParent(INode parent)
	{
		this.parent = parent;
	}
		
	/**
	 * Clears all children from the {@code NodeArray}.
	 */
	public void clearChildren()
	{
		children = null;
	}
	
	
	/**
	 * Returns a child from the {@code NodeArray}.
	 * 
	 * @param i  a child node index
	 * @return  a child node
	 * 
	 * 
	 * @see INode
	 */
	public <N extends INode> N Child(int i)
	{
		if(children != null 
		&& children.length > i)
		{
			return (N) children[i];
		}
		
		return null;
	}
	
	/**
	 * Returns the child count of the {@code NodeArray}.
	 * </br> This only counts direct descendants of the node.
	 * 
	 * @return  a nodal child count
	 */
	public int ChildCount()
	{
		int count = 0;
		
		NodeArray rel = (NodeArray) target.Relations();
		for(INode child : rel.Children())
		{
			if(child != null)
			{
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * Returns the index of the {@code NodeArray}.
	 * </br> This is the index of the node as a child of its parent.
	 * 
	 * @return  a nodal index
	 */
	public int Index()
	{
		int index = 0;
		// Assumes the iterator starts at zero.
		for(INode child : parent.Relations().Children())
		{
			if(child == this)
				return index;
			index++;
		}
		
		return -1;
	}

	
	@Override
	public <R extends IRelatable> R Parent()
	{
		return (R) parent;
	}
	
	@Override
	public <N extends INode> Iterable<N> Children()
	{
		return Iterables.of(children);
	}
	
	@Override
	public INode Delegate()
	{
		return target;
	}
}