package zeno.util.coll.utilities.relations.nodes.array;

import zeno.util.coll.utilities.relations.IRelatable;
import zeno.util.coll.utilities.relations.nodes.INode;
import zeno.util.tools.helper.Array;

/**
 * The {@code NodeArray} defines parent-child relations with explicit references in an array.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see INodeArray
 */
public class NodeArray implements INodeArray
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
				children = Array.remove.from(children, i);
				return;
			}
		}
	}
	
	/**
	 * Changes a child inside the {@code NodeArray}.
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
	 * Clears children from the {@code NodeArray}.
	 */
	public void clearChildren()
	{
		children = new INode[0];
	}

	
	@Override
	public INode[] Array()
	{
		return children;
	}
	
	@Override
	public <R extends IRelatable> R Parent()
	{
		return (R) parent;
	}
	
	@Override
	public INode Delegate()
	{
		return target;
	}
}