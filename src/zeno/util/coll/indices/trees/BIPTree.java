package zeno.util.coll.indices.trees;

import zeno.util.coll.indices.Index;
import zeno.util.coll.trees.binary.BiTree;

/**
 * The {@code BIPTree} class defines a binary index partition tree.
 *
 * @author Waffles
 * @since 27 Dec 2022
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see BiTree
 * @see Index
 */
public class BIPTree<O> extends BiTree implements Index<O>
{	
	/**
	 * The {@code QRYTool} retrieves an object from a {@code BIPNode}.
	 *
	 * @author Waffles
	 * @since 29 Dec 2022
	 * @version 1.0
	 *
	 *
	 * @param <O>  an object type
	 */
	@FunctionalInterface
	public static interface QRYTool<O>
	{
		/**
		 * Returns the value associated with a node.
		 * 
		 * @param node  a target node
		 * @return  a node value
		 * 
		 * 
		 * @see BIPNode
		 */
		public abstract O get(BIPNode node);
	}
	
	
	private int[] dims;
	private QRYTool<O> tool;
	
	/**
	 * Creates a new {@code BIPTree}.
	 * 
	 * @param dims    a tile dimension
	 */
	public BIPTree(int... dims)
	{
		int[] min = new int[dims.length];
		int[] max = new int[dims.length];
		for(int i = 0; i < dims.length; i++)
		{
			max[i] = dims[i] - 1;
		}

		tool = (node) -> null;
		setRoot(create(min, max));
		this.dims = dims;
	}

	/**
	 * Changes the query tool of the {@code BIPTree}.
	 * 
	 * @param tool  a query tool
	 * 
	 * 
	 * @see QRYTool
	 */
	public void setTool(QRYTool<O> tool)
	{
		this.tool = tool;
	}
	

	@Override
	public O get(int... coords)
	{
		if(!contains(coords)) return null;
		
		
		BIPNode node = Root();
		while(!node.isLeaf())
		{
			node = node.Child(coords);
		}
		
		return tool.get(node);
	}
		
	@Override
	public Iterable<BIPNode> BFSearch()
	{
		return super.BFSearch();
	}

	@Override
	public Iterable<BIPNode> DFSearch()
	{
		return super.DFSearch();
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
}