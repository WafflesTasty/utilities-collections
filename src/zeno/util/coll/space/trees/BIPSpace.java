package zeno.util.coll.space.trees;

import java.util.Iterator;

import zeno.util.coll.Queue;
import zeno.util.coll.queues.FIFOQueue;
import zeno.util.coll.space.index.IndexSpace;
import zeno.util.coll.space.trees.bips.BIPSNode;
import zeno.util.coll.space.trees.bips.BIPSTree;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.geometry.generic.ICuboid;

/**
 * The {@code BIPSpace} class defines a binary index partition space.
 *
 * @author Waffles
 * @since 28 Dec 2022
 * @version 1.0
 *
 *
 * @param <N>  a node type
 * @see IndexSpace
 * @see BIPSNode
 */
public abstract class BIPSpace<N extends BIPSNode> implements IndexSpace<N>
{	
	/**
	 * The {@code QRYNodes} class defines an iterator across a subsection of a {@code BIPSpace}.
	 *
	 * @author Waffles
	 * @since 29 Dec 2022
	 * @version 1.0
	 *
	 * 
	 * @see Iterator
	 */
	public class QRYNodes implements Iterator<N>
	{
		private int[] min, max;
		private Queue<BIPSNode> queue;
		
		/**
		 * Creates a new {@code QRYNodes}.
		 * 
		 * @param min  a minimum index
		 * @param max  a maximum index
		 */
		public QRYNodes(int[] min, int[] max)
		{
			queue = new FIFOQueue<>();
			queue.push(tree.Root());
			
			this.min = min;
			this.max = max;
		}
		
		
		@Override
		public boolean hasNext()
		{
			return !queue.isEmpty();
		}

		@Override
		public N next()
		{
			BIPSNode node = queue.pop();
			if(!node.isLeaf())
			{
				int sDim = node.DimSplit();
				BIPSNode lchild = node.LChild();
				BIPSNode rchild = node.RChild();
				
				if(min[sDim] < rchild.Minimum()[sDim])
					queue.push(lchild);
				if(max[sDim] > lchild.Maximum()[sDim])
					queue.push(rchild);
			}

			return (N) node;
		}
	}
	
	
	private float tSize;
	private BIPSTree<N> tree;
	
	/**
	 * Creates a new {@code BIPSpace}.
	 * 
	 * @param size  an index tile size
	 */
	public BIPSpace(float size)
	{
		tree = new BIPSTree<>();
		tSize = size;
	}
		

	@Override
	public BIPSTree<N> Index()
	{
		return tree;
	}

	@Override
	public Iterable<N> query(ICuboid c)
	{
		int[] min = Coordinate(c.Minimum());
		int[] max = Coordinate(c.Maximum());
		return () -> new QRYNodes(min, max);
	}

	@Override
	public Iterable<N> query(Point p)
	{
		int[] crds = Coordinate(p.asVector());
		return () -> new QRYNodes(crds, crds);
	}
	
	@Override
	public float TileSize()
	{
		return tSize;
	}
}