package zeno.util.coll.space.trees.bips;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.coll.indices.trees.BEPNode;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code BEPSNode} class defines a single node in a {@code BEPSpace}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 *
 * 
 * @param <E>  an enum type
 * @see IBounded
 * @see BEPNode
 */
public class BEPSNode<E extends Enum<E>> extends BEPNode<E> implements IBounded
{		
	/**
	 * Creates a new {@code BEPSNode}.
	 * 
	 * @param tree  a parent tree
	 * @param min   a minimum index
	 * @param max   a maximum index
	 * 
	 * 
	 * @see BEPSTree
	 */
	public BEPSNode(BEPSTree<E> tree, int[] min, int[] max)
	{
		super(tree, min, max);
	}

					
	@Override
	public ICuboid Bounds()
	{
		int[] min = Minimum();
		int[] max = Maximum();
		
		Vector cen = Vectors.create(min.length);
		Vector siz = Vectors.create(min.length);
		for(int i = 0; i < min.length; i++)
		{
			cen.set((min[i] + max[i] + 1) * Tree().TileSize() / 2, i);
			siz.set((max[i] - min[i] + 1) * Tree().TileSize(), i);
		}
		
		return Geometries.cuboid(cen, siz);
	}

	@Override
	public BEPSNode<E> LChild()
	{
		return (BEPSNode<E>) super.LChild();
	}
	
	@Override
	public BEPSNode<E> RChild()
	{
		return (BEPSNode<E>) super.RChild();
	}
	
	@Override
	public BEPSTree<E> Tree()
	{
		return (BEPSTree<E>) super.Tree();
	}
}