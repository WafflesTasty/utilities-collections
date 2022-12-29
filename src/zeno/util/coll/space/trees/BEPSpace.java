package zeno.util.coll.space.trees;

import java.util.Iterator;

import zeno.util.coll.Queue;
import zeno.util.coll.queues.FIFOQueue;
import zeno.util.coll.space.index.IndexSpace;
import zeno.util.coll.space.trees.bips.BEPSNode;
import zeno.util.coll.space.trees.bips.BEPSTree;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.geometry.generic.ICuboid;

/**
 * The {@code BEPSpace} class defines a binary enum partition space.
 *
 * @author Waffles
 * @since 28 Dec 2022
 * @version 1.0
 *
 *
 * @param <E>  an enum type
 * @see IndexSpace
 * @see BEPSNode
 */
public class BEPSpace<E extends Enum<E>> implements IndexSpace<BEPSNode<E>>
{	
	/**
	 * The {@code QRYNodes} class defines an iterator across a subsection of a {@code BEPSpace}.
	 *
	 * @author Waffles
	 * @since 29 Dec 2022
	 * @version 1.0
	 *
	 * 
	 * @see Iterator
	 */
	public class QRYNodes implements Iterator<BEPSNode<E>>
	{
		private int[] min, max;
		private Queue<BEPSNode<E>> queue;
		
		/**
		 * Creates a new {@code QRYNodes}.
		 * 
		 * @param min  a minimum index
		 * @param max  a maximum index
		 */
		public QRYNodes(int[] min, int[] max)
		{
			queue = new FIFOQueue<>();
			queue.push(Index().Root());
			
			this.min = min;
			this.max = max;
		}
		
		
		@Override
		public boolean hasNext()
		{
			return !queue.isEmpty();
		}

		@Override
		public BEPSNode<E> next()
		{
			BEPSNode<E> node = queue.pop();
			if(!node.isLeaf())
			{
				int sDim = node.DimSplit();
				BEPSNode<E> lchild = node.LChild();
				BEPSNode<E> rchild = node.RChild();
				
				if(min[sDim] < rchild.Minimum()[sDim])
					queue.push(lchild);
				if(max[sDim] > lchild.Maximum()[sDim])
					queue.push(rchild);
			}

			return (BEPSNode<E>) node;
		}
	}
	
	
	private float tSize;
	private BEPSTree<E> tree;
	
	/**
	 * Creates a new {@code BIPSpace}.
	 * 
	 * @param size  an index tile size
	 * @param dims  an index dimension
	 */
	public BEPSpace(float size, int... dims)
	{
		tree = new BEPSTree<>(this, dims);
		tSize = size;
	}
		

	@Override
	public BEPSTree<E> Index()
	{
		return tree;
	}

	@Override
	public Iterable<BEPSNode<E>> query(ICuboid c)
	{
		int[] min = Coordinate(c.Minimum());
		int[] max = Coordinate(c.Maximum());
		return () -> new QRYNodes(min, max);
	}

	@Override
	public Iterable<BEPSNode<E>> query(Point p)
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