package waffles.utils.sets.trees.indexed;

import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.trees.binary.BiTree;

/**
 * The {@code BIPTree} class defines a binary index partition tree.
 * This allows a large indexed space to be partitioned into nodes
 * to improve node traversal speed in various applications.
 *
 * @author Waffles
 * @since 27 Dec 2022
 * @version 1.0
 * 
 * 
 * @param <O>  an index object type
 * @see IndexedSet
 * @see BiTree
 */
public abstract class BIPTree<O> extends BiTree<O> implements IndexedSet<O>
{	
	private int[] dims;
	
	/**
	 * Creates a new {@code BIPTree}.
	 * 
	 * @param dims  a tree dimension
	 */
	public BIPTree(int... dims)
	{
		this.dims = dims;
	}
	
	/**
	 * Returns the value of a {@code BIPNode}.
	 * 
	 * @param node  a tree node
	 * @return  a node value
	 * 
	 * 
	 * @see BIPNode
	 */
	public abstract O valueOf(BIPNode node);
			
	
	@Override
	public O get(int... coords)
	{
		// If the coordinates are out of bounds...
		if(!contains(coords))
		{
			// Don't return anything.
			return null;
		}
		
		
		// Otherwise, start from the root...
		BIPNode node = Root();
		while(!node.isLeaf())
		{
			// And find the closest node.
			node = node.Child(coords);
		}
		
		return valueOf(node);
	}

	@Override
	public int[] Dimensions()
	{
		return dims;
	}
	
	@Override
	public int[] Minimum()
	{
		return Root().Minimum();
	}
	
	@Override
	public int[] Maximum()
	{
		return Root().Maximum();
	}


	@Override
	public BIPNode create(Object... vals)
	{
		int[] min = (int[]) vals[0];
		int[] max = (int[]) vals[1];
		
		return new BIPNode(this, min, max);
	}
	
	@Override
	public BIPNode Root()
	{
		return (BIPNode) super.Root();
	}
}