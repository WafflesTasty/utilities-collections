package zeno.util.coll.trees;

import java.util.HashMap;
import java.util.Map;

import zeno.util.coll.trees.traversal.BreadthFirst;
import zeno.util.coll.trees.traversal.DepthFirst;
import zeno.util.coll.utilities.relations.nodes.INode;
import zeno.util.coll.utilities.relations.nodes.array.INodeArray;
import zeno.util.coll.utilities.relations.nodes.array.NodeArray;

/**
 * The {@code Tree} interface defines a generic tree structure.
 *
 * @author Zeno
 * @since 03 Aug 2020
 * @version 1.0
 */
public interface Tree
{	
	/**
	 * The {@code Node} class defines a single node in a tree.
	 * </br> It tracks its parent-child relationship in a {@code NodeArray}.
	 * 
	 * @author Zeno
	 * @since 30 Jul 2020
	 * @version 1.0
	 * 
	 * 
	 * @see INodeArray
	 * @see INode
	 */
	public static class Node implements INode, INodeArray
	{
		private Tree tree;
		private NodeArray nodes;
		private Map<String, Object> props;
		
		/**
		 * Creates a new {@code Node}.
		 * 
		 * @param t  a parent tree
		 * 
		 * 
		 * @see Tree
		 */
		public Node(Tree t)
		{
			props = new HashMap<>();
			nodes = new NodeArray(this);
			tree  = t;
		}

			
		/**
		 * Adds a child to the {@code Node}.
		 * 
		 * @param child  a child node
		 * 
		 * 
		 * @see Node
		 */
		public void addChild(Node child)
		{
			nodes.addChild(child);
		}
		
		/**
		 * Changes a child from the {@code Node}.
		 * 
		 * @param i  a child index
		 * @param child  a new child node
		 */
		public void setChild(int i, Node child)
		{
			nodes.setChild(i, child);
		}
		
		/**
		 * Changes the parent of the {@code Node}.
		 * 
		 * @param parent  a parent node
		 */
		public void setParent(Node parent)
		{
			nodes.setParent(parent);
		}
		
		/**
		 * Clears children from the {@code Node}.
		 */
		public void clearChildren()
		{
			nodes.clearChildren();
		}
		
		/**
		 * Returns the tree of the {@code Node}.
		 * 
		 * @return  a parent tree
		 * 
		 * 
		 * @see Tree
		 */
		public Tree Tree()
		{
			return tree;
		}
		
		
		@Override
		public <O, P> void setProperty(Class<O> type, P prop)
		{
			if(prop != null)
				props.put(type.getName(), prop);
			else
				props.remove(type.getName());
		}
		
		@Override
		public <O, P> P getProperty(Class<O> type)
		{
			return (P) props.get(type.getName());
		}
		
		@Override
		public INode[] Array()
		{
			return nodes.Array();
		}
		
		@Override
		public NodeArray Relations()
		{
			return nodes;
		}
		
		@Override
		public Node Delegate()
		{
			return this;
		}
				
		@Override
		public Node Parent()
		{
			return nodes.Parent();
		}
	}
	
	
	/**
	 * Returns a breadth-first iterable for the {@code Tree}.
	 * 
	 * @param <N>  a node type
	 * @return  a breadth-first iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default <N extends Node> Iterable<N> BFSearch()
	{
		return () -> new BreadthFirst<>((N) Root());
	}
	
	/**
	 * Returns a depth-first iterable for the {@code Tree}.
	 * 
	 * @param <N>  a node type
	 * @return  a depth-first iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default <N extends Node> Iterable<N> DFSearch()
	{
		return () -> new DepthFirst<>((N) Root());
	}

	
	/**
	 * Checks if there is data in the {@code Tree}.
	 * 
	 * @return  {@code true} if the tree is empty
	 */
	public abstract boolean isEmpty();
	
	/**
	 * Creates a new node for the {@code Tree}.
	 * 
	 * @param vals  a set of parameters
	 * @return  a new node
	 * 
	 * 
	 * @see Node
	 */
	public default Node create(Object... vals)
	{
		return new Node(this);
	}
	
	/**
	 * Returns the root of the {@code Tree}.
	 * 
	 * @return  a root node
	 * 
	 * 
	 * @see Node
	 */
	public abstract Node Root();
}