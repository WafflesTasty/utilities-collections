package waffles.utils.sets.arboreal.binary.indexed;

import waffles.utils.sets.arboreal.binary.BiTree;
import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.utilities.rooted.iterators.binary.indexed.BIPNodes;

/**
 * The {@code BIPTree} class defines a binary index partition tree.
 * This allows a large indexed space to be partitioned into nodes
 * to improve node traversal speed in various applications.
 *
 * @author Waffles
 * @since 27 Dec 2022
 * @version 1.1
 * 
 * 
 * @param <O>  an index object type
 * @see IndexedSet
 * @see BiTree
 */
public abstract class BIPTree<O> extends BiTree implements IndexedSet<O>
{	
	/**
	 * A {@code BIPTree.Factory} generates {@code BIPNode} objects.
	 *
	 * @author Waffles
	 * @since 25 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see BiTree
	 */
	public static interface Factory extends BiTree.Factory
	{
		@Override
		public abstract BIPTree<?> Tree();
			
		@Override
		public default BIPNode node(Object... data)
		{
			int[] min = (int[]) data[0];
			int[] max = (int[]) data[1];
			
			return new BIPNode(Tree(), min, max);
		}
	}
	
	
	private int[] dims;
	
	/**
	 * Creates a new {@code BIPTree}.
	 * 
	 * @param d  tree dimensions
	 */
	public BIPTree(int... d)
	{
		dims = d;
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
	 * Iterates over a set of nodes in the {@code BIPTree}.
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
	public <N extends BIPNode> Iterable<N> Nodes(int[] min, int[] max)
	{
		return () -> new BIPNodes<>(this, min, max);
	}
	
	/**
	 * Returns a node at a given coordinate.
	 * 
	 * @param crds  an index coordinate
	 * @return  a tree node
	 * 
	 * 
	 * @see BIPNode
	 */
	public BIPNode nodeAt(int... crds)
	{
		BIPNode n = Root();
		while(!n.isLeaf())
		{
			n = n.childAt(crds);
		}

		return n;
	}
	
	
	@Override
	public BIPNode Root()
	{
		BIPNode root = (BIPNode) super.Root();
		if(root != null)
		{
			return root;
		}
		
		clear();
		return Root();
	}
	
	@Override
	public Factory Factory()
	{
		return () -> this;
	}
	
	@Override
	public O get(int... crds)
	{
		return valueOf(nodeAt(crds));
	}

	@Override
	public int[] Dimensions()
	{
		return dims;
	}
	
	@Override
	public int[] Minimum()
	{
		return new int[Order()];
	}
	
	@Override
	public int[] Maximum()
	{
		int[] max = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			max[i] = Dimensions()[i] - 1;
		}
		
		return max;
	}
	
	@Override
	public void clear()
	{	
		Factory fct = Factory();
		
		int[] min = Minimum();
		int[] max = Maximum();
		
		setRoot(fct.node(min, max));
	}
	
	@Override
	public int Count()
	{
		return IndexedSet.super.Count();
	}
}