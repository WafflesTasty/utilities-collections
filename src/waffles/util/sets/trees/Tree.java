package waffles.util.sets.trees;

import waffles.util.sets.CountableSet;
import waffles.util.sets.trees.traversal.BreadthFirst;
import waffles.util.sets.trees.traversal.DepthFirst;

/**
 * The abstract {@code Tree} class defines a generic tree structure.
 * Each tree requires at least a root {@code Node}, and optionally allows
 * the {@link #create(Object...)} method to be overwritten to create
 * custom nodes for the {@code Tree}.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.0
 *
 *
 * @param <O>  a tree object type
 * @see CountableSet
 */
public abstract class Tree<O> implements CountableSet<O>
{
	private Nodal root;
	
	/**
	 * Changes the root of the {@code Tree}.
	 * 
	 * @param root  a root nodal
	 * 
	 * 
	 * @see Nodal
	 */
	public void setRoot(Nodal root)
	{
		this.root = root;
	}
		
	/**
	 * Creates a new node for the {@code Tree}.
	 * 
	 * @param vals  an array of values
	 * @return  a new tree node
	 * 
	 * 
	 * @see Node
	 */
	public Node create(Object... vals)
	{
		return new Node(this);
	}
		
	/**
	 * Returns the root of the {@code Tree}.
	 * 
	 * @return  a root nodal
	 * 
	 * 
	 * @see Nodal
	 */
	public Nodal Root()
	{
		return root;
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
	public <N extends Nodal> Iterable<N> DFSearch()
	{
		return () -> new DepthFirst<>((N) Root());
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
	public <N extends Nodal> Iterable<N> BFSearch()
	{
		return () -> new BreadthFirst<>((N) Root());
	}

	
	@Override
	public boolean isEmpty()
	{
		return root == null;
	}
}