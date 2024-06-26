package waffles.utils.sets.trees.indexed;

import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.trees.binary.BiTree;
import waffles.utils.sets.utilities.iterators.BIPNodes;

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
public abstract class BIPTree<O> extends BiTree implements IndexedSet<O>
{	
	/**
	 * Creates a new {@code BIPTree}.
	 * 
	 * @param dims  a tree dimension
	 */
	public BIPTree(int... dims)
	{
		int[] min = new int[dims.length];
		int[] max = new int[dims.length];
		for(int i = 0; i < dims.length; i++)
		{
			max[i] = dims[i] - 1;
		}
		
		setRoot(createNode(min, max));
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
	
	/**
	 * Iterates over the nodes of the {@code BIPTree}.
	 * 
	 * @param min  an index minimum
	 * @param max  an index maximum
	 * @return  a node iterable
	 * 
	 * 
	 * @param <N>  a node type
	 * @see Iterable
	 * @see BIPNode
	 */
	public <N extends BIPNode> Iterable<N> nodes(int[] min, int[] max)
	{
		return () -> new BIPNodes<>(this, min, max);
	}
	
	/**
	 * Returns a node at a given coordinate.
	 * 
	 * @param coords  an index coordinate
	 * @return  a tree node
	 * 
	 * 
	 * @see BIPNode
	 */
	public BIPNode nodeAt(int... coords)
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
			node = node.get(coords);
		}
		
		return node;
	}
			
	
	@Override
	public O get(int... coords)
	{
		return valueOf(nodeAt(coords));
	}

	@Override
	public int[] Dimensions()
	{
		return Root().Dimensions();
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
	public BIPNode createNode(Object... vals)
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