package waffles.utils.sets.arboreal.binary;

import waffles.utils.sets.arboreal.Tree;

/**
 * A {@code BiTree} implements a basic {@code BiArboreal}.
 *
 * @author Waffles
 * @since 03 Aug 2020
 * @version 1.0
 * 
 * 
 * @see BiArboreal
 * @see Tree
 */
public abstract class BiTree extends Tree implements BiArboreal
{
	/**
	 * Creates a new {@code BiTree}.
	 */
	public BiTree()
	{
		super();
	}
	
	/**
	 * Creates a new {@code BiTree}.
	 * 
	 * @param r  a root nodal
	 */
	public BiTree(BiNodal r)
	{
		super(r);
	}

	
	@Override
	@Deprecated
	public BiArboreal.Query<?> Query()
	{
		return null;
	}
				
	@Override
	public BiNodal Root()
	{
		return (BiNodal) super.Root();
	}
}