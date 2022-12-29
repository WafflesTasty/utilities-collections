package zeno.util.coll.space.trees.bips;

import zeno.util.coll.indices.trees.BIPTree;
import zeno.util.coll.space.trees.BIPSpace;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICuboid;

/**
 * The {@code BIPSTree} class defines the partition tree that backs the {@code BIPSpace}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 *
 * 
 * @param <N>  a node type
 * @see IBounded
 * @see BIPSNode
 * @see BIPTree
 */
public class BIPSTree<N extends BIPSNode> extends BIPTree<N> implements IBounded
{		
	private BIPSpace<?> space;
	
	/**
	 * Returns the tile size of the {@code BIPSTree}.
	 * 
	 * @return  a tile size
	 */
	public float TileSize()
	{
		return space.TileSize();
	}

	
	@Override
	public ICuboid Bounds()
	{
		return Root().Bounds();
	}
	
	@Override
	public BIPSNode create(Object... vals)
	{
		int[] min = (int[]) vals[0];
		int[] max = (int[]) vals[1];
		
		return new BIPSNode(this, min, max);
	}
	
	@Override
	public N Root()
	{
		return (N) super.Root();
	}
}