package waffles.utils.sets.rooted;

import waffles.utils.sets._old.trees.Nodal;
import waffles.utils.sets._old.trees.Node;

/**
 * The {@code Tree} class defines a generic node tree structure.
 * Each tree requires at least a root {@code Node}, and optionally
 * allows the {@link #createNode(Object...)} method to be overwritten
 * to create custom nodes for the {@code Tree}.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.1
 *
 *
 * @see Rooted
 */
public class Tree implements Rooted
{
	private Nodal root;
	
	/**
	 * Changes the root of the {@code Tree}.
	 * 
	 * @param r  a root nodal
	 * 
	 * 
	 * @see Nodal
	 */
	public void setRoot(Nodal r)
	{
		root = r;
		if(root != null)
		{
			Node node = root.Arch();
			node.setParent(null);
		}
	}

	
	@Override
	public void clear()
	{
		root = null;
	}

	@Override
	public Node createNode(Object... vals)
	{
		return new Node(this);
	}
	
	@Override
	public Nodal Root()
	{
		return root;
	}
}