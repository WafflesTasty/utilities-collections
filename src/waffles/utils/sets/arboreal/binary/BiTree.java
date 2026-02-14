package waffles.utils.sets.arboreal.binary;

import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.arboreal.Tree;

/**
 * The {@code BiTree} class defines a generic binary tree structure.
 *
 * @author Waffles
 * @since 03 Aug 2020
 * @version 1.0
 * 
 * 
 * @see BiArboreal
 * @see Tree
 */
public class BiTree extends Tree implements BiArboreal.Mutable
{
	/**
	 * A {@code BiTree.Factory} generates {@code BiNode} objects.
	 *
	 * @author Waffles
	 * @since 25 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Arboreal
	 */
	public static interface Factory extends Arboreal.Factory
	{			
		@Override
		public default BiNode node(Object... data)
		{
			return new BiNode(Tree());
		}
		
		@Override
		public abstract BiTree Tree();
	}
	
			
	@Override
	public Factory Factory()
	{
		return () -> this;
	}
	
	@Override
	public BiNodal Root()
	{
		return (BiNodal) super.Root();
	}
}