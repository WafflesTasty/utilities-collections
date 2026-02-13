package waffles.utils.sets.arboreal;

import waffles.utils.sets.utilities.rooted.Nodal;
import waffles.utils.sets.utilities.rooted.Node;

/**
 * A {@code Tree} implements a basic {@code Rooted}.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.1
 *
 *
 * @see Arboreal
 */
public class Tree implements Arboreal.Mutable
{
	private Nodal root;

	/**
	 * Creates a new {@code Tree}.
	 * 
	 * @param r  a root node
	 * 
	 * 
	 * @see Nodal
	 */
	public Tree(Nodal r)
	{
		root = r;
	}
	
	/**
	 * Creates a new {@code Tree}.
	 */
	public Tree()
	{
		// NOT APPLICABLE
	}
	
	
	@Override
	public Nodal Root()
	{
		return root;
	}
	
	@Override
	public void setRoot(Nodal r)
	{
		root = r;
		if(r != null)
		{
			Node n = (Node) r.Arch();
			n.setParent(null);
		}
	}
	
	@Override
	public void clear()
	{
		root = null;
	}
}