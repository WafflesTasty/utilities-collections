package zeno.util.coll.utilities.relations.nodes;

import zeno.util.coll.trees.Tree.Node;
import zeno.util.coll.utilities.relations.IRelatable;
import zeno.util.coll.utilities.relations.nodes.array.NodeArray;
import zeno.util.tools.patterns.Properties;

/**
 * The {@code INode} interface defines a relatable object with parent-child behavior.
 *
 * @author Waffles
 * @since Sep 22, 2019
 * @version 1.0
 * 
 * 
 * @see IRelatable
 * @see Properties
 */
public interface INode extends IRelatable, Properties
{
	@Override
	public default NodeArray Relations()
	{
		NodeArray nodes = getProperty(INodal.class);
		if(nodes == null)
		{
			nodes = new NodeArray(this);
			setProperty(INodal.class, nodes);
		}
		
		return nodes;
	}
	
	
	/**
	 * Adds a child to the {@code INode}.
	 * 
	 * @param child  a child node
	 * 
	 * 
	 * @see Node
	 */
	public default void addChild(Node child)
	{
		Relations().addChild(child);
	}
	
	/**
	 * Changes a child from the {@code INode}.
	 * 
	 * @param i  a child index
	 * @param child  a new child node
	 */
	public default void setChild(int i, Node child)
	{
		Relations().setChild(i, child);
	}
	
	/**
	 * Changes the parent of the {@code INode}.
	 * 
	 * @param parent  a parent node
	 */
	public default void setParent(Node parent)
	{
		Relations().setParent(parent);
	}
	
	/**
	 * Clears the children of the {@code INode}.
	 */
	public default void clearChildren()
	{
		Relations().clearChildren();
	}
	
	/**
	 * Returns the parent of the {@code INode}.
	 * 
	 * @return  a parent node
	 * 
	 * 
	 * @see INode
	 */
	public default INode Parent()
	{
		return Relations().Parent();
	}
}