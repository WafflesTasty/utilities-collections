package zeno.util.coll.trees;

import java.util.HashMap;
import java.util.Map;

import zeno.util.coll.trees.traversal.BreadthFirst;
import zeno.util.coll.trees.traversal.DepthFirst;
import zeno.util.coll.utilities.relations.nodes.INode;
import zeno.util.coll.utilities.relations.nodes.array.INodeArray;

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
			tree  = t;
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
			return Relations().Array();
		}
		
		@Override
		public Node Delegate()
		{
			return this;
		}
	
		@Override
		public Node Parent()
		{
			return Relations().Parent();
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
	 * Checks if there is data in the {@code Tree}.
	 * 
	 * @return  {@code true} if the tree is empty
	 */
	public abstract boolean isEmpty();
		
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