package zeno.util.coll;

import zeno.util.coll.trees.traversal.BreadthFirst;
import zeno.util.coll.trees.traversal.DepthFirst;
import zeno.util.coll.utilities.relations.nodes.INode;
import zeno.util.coll.utilities.relations.nodes.NodeArray;

/**
 * The {@code Node} class defines a single node in a tree structure.
 * </br> It tracks its parent-child relationship in a {@code NodeArray}.
 * 
 * @author Zeno
 * @since 30 Jul 2020
 * @version 1.0
 * 
 * 
 * @see INode
 */
public class Node implements INode
{
	private NodeArray nodes;
	
	/**
	 * Creates a new {@code Node}.
	 */
	public Node()
	{
		nodes = new NodeArray(this);
	}
	
	
	/**
	 * Returns a breadth-first iterable for the {@code Node}.
	 * 
	 * @param <N>  a node type
	 * @return  a breadth-first iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public <N extends Node> Iterable<N> BFSearch()
	{
		return () -> new BreadthFirst<>((N) this);
	}
	
	/**
	 * Returns a depth-first iterable for the {@code Node}.
	 * 
	 * @param <N>  a node type
	 * @return  a depth-first iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public <N extends Node> Iterable<N> DFSearch()
	{
		return () -> new DepthFirst<>((N) this);
	}
	
	
	@Override
	public NodeArray Relations()
	{
		return nodes;
	}
}