package zeno.util.coll.space.trees.bips;

import zeno.util.coll.indices.trees.BEPTree;
import zeno.util.coll.space.trees.BEPSpace;
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
 * @param <E>  an enum type
 * @see IBounded
 * @see BEPTree
 */
public class BEPSTree<E extends Enum<E>> extends BEPTree<E> implements IBounded
{		
	private BEPSpace<E> space;
	
	/**
	 * Creates a new {@code BEPSTree}.
	 * 
	 * @param space  a parent space
	 * @param dims   an index dimension
	 * 
	 * 
	 * @see BEPSpace
	 */
	public BEPSTree(BEPSpace<E> space, int... dims)
	{
		this.space = space;
	}
	
	/**
	 * Returns the tile size of the {@code BEPSTree}.
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
	public BEPSNode<E> create(Object... vals)
	{
		int[] min = (int[]) vals[0];
		int[] max = (int[]) vals[1];
		
		return new BEPSNode<>(this, min, max);
	}
	
	@Override
	public BEPSNode<E> Root()
	{
		return (BEPSNode<E>) super.Root();
	}
}