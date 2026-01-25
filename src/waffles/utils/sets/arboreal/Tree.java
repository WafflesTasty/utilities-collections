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
public class Tree implements Arboreal
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
	public Nodal Root()
	{
		return root;
	}
	
	@Override
	public void clear()
	{
		root = null;
	}
}