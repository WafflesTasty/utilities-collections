package waffles.utils.sets.trees;

/**
 * The abstract {@code Tree} class defines a generic tree structure.
 * Each tree requires at least a root {@code Node}, and optionally allows
 * the {@link #createNode(Object...)} method to be overwritten to create
 * custom nodes for the {@code Tree}.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.1
 *
 *
 * @see Rooted
 */
public abstract class Tree implements Rooted
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