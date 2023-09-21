package waffles.utils.sets.trees;

/**
 * The abstract {@code Tree} class defines a generic tree structure.
 * Each tree requires at least a root {@code Node}, and optionally allows
 * the {@link #create(Object...)} method to be overwritten to create
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


	@Override
	public Nodal Root()
	{
		return root;
	}
}