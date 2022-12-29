package zeno.util.coll.space.trees.bips;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.coll.indices.trees.BIPNode;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code BIPSNode} class defines a single node in a {@code BIPSpace}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 *
 * 
 * @see IBounded
 * @see BIPNode
 */
public class BIPSNode extends BIPNode implements IBounded
{		
	/**
	 * Creates a new {@code BIPSNode}.
	 * 
	 * @param tree  a parent tree
	 * @param min   a minimum index
	 * @param max   a maximum index
	 */
	public BIPSNode(BIPSTree<?> tree, int[] min, int[] max)
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
	public BIPSNode LChild()
	{
		return (BIPSNode) super.LChild();
	}
	
	@Override
	public BIPSNode RChild()
	{
		return (BIPSNode) super.RChild();
	}
	
	@Override
	public BIPSTree<?> Tree()
	{
		return (BIPSTree<?>) super.Tree();
	}
}