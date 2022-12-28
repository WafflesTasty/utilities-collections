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
 * @param <V>  a value type
 * @see BiTree
 * @see Index
 */
public class BIPTree<V> extends BiTree implements Index<V>
{	
	private int[] dims;
	
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

		setRoot(create(min, max));
		this.dims = dims;
	}
	
	
	@Override
	public V get(int... coords)
	{
		if(!contains(coords)) return null;
		
		
		BIPNode<V> node = Root();
		while(!node.isLeaf())
		{
			node = node.Child(coords);
		}
		
		return node.Value();
	}
	
	@Override
	public BIPNode<V> create(Object... vals)
	{
		int[] min = (int[]) vals[0];
		int[] max = (int[]) vals[1];
		
		return new BIPNode<>(this, min, max);
	}
	
	@Override
	public Iterable<BIPNode<V>> BFSearch()
	{
		return super.BFSearch();
	}

	@Override
	public Iterable<BIPNode<V>> DFSearch()
	{
		return super.DFSearch();
	}
	
	@Override
	public BIPNode<V> Root()
	{
		return (BIPNode<V>) super.Root();
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