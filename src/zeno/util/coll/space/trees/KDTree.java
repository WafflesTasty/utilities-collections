package zeno.util.coll.space.trees;

import java.util.Iterator;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.coll.Queue;
import zeno.util.coll.queues.FIFOQueue;
import zeno.util.coll.space.Space;
import zeno.util.coll.space.trees.KDNode.Cut;
import zeno.util.coll.trees.binary.BiTree;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;
import zeno.util.tools.helper.iterators.EmptyIterator;

/**
 * The {@code KDTree} class defines an axis-aligned binary partition space.
 *
 * @author Waffles
 * @since 17 Jan 2022
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see IBounded
 * @see Iterable
 * @see BiTree
 * @see Space
 */
public class KDTree<O extends IBounded> extends BiTree implements Iterable<O>, Space<O>
{
	/**
	 * The {@code QRYCuboid} class queries an {@code ICuboid} into the {@code KDTree}.
	 *
	 * @author Waffles
	 * @since 04 Apr 2022
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 */
	public class QRYCuboid implements Iterator<O>
	{
		private ICuboid cube;
		private Queue<KDNode<O>> nodes;
		private Iterator<O> objects;
		private IBounded next;
		
		/**
		 * Creates a new {@code QRYCuboid}.
		 * 
		 * @param c  a target cuboid
		 * 
		 * 
		 * @see ICuboid
		 */
		public QRYCuboid(ICuboid c)
		{
			cube = c;

			objects = new EmptyIterator<>();
			nodes = new FIFOQueue<>();
			nodes.push(Root());

			next = findNext();
		}
		
		
		private O findNext()
		{
			if(objects.hasNext())
			{
				return objects.next();
			}
			
			if(!nodes.isEmpty())
			{
				KDNode<O> node = nodes.pop();
				objects = node.query(cube).iterator();
				if(!node.isLeaf())
				{
					Cut cut = node.Cut();
					float val = cut.Value();
					int dim = cut.Dimension();

					Vector min = cube.Minimum();
					Vector max = cube.Maximum();
					
					if(min.get(dim) < val)
						nodes.push(node.LChild());
					if(max.get(dim) > val)
						nodes.push(node.RChild());
					return findNext();
				}
			}
			
			return null;
		}
		
		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public O next()
		{
			O curr = (O) next;
			next = findNext();
			return curr;
		}
	}
	
	/**
	 * The {@code QRYPoint} class queries a {@code Point} into the {@code KDTree}.
	 *
	 * @author Waffles
	 * @since 04 Apr 2022
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 */
	public class QRYPoint implements Iterator<O>
	{
		private Point pt;
		private KDNode<O> node;
		private Iterator<O> objects;
		private IBounded next;
		
		/**
		 * Creates a new {@code QRYPoint}.
		 * 
		 * @param p  a target point
		 * 
		 * 
		 * @see Point
		 */
		public QRYPoint(Point p)
		{
			objects = new EmptyIterator<>();
			node = Root(); pt = p;
			next = findNext();
		}
		
		
		private O findNext()
		{
			if(objects.hasNext())
			{
				return objects.next();
			}
			
			objects = node.query(pt).iterator();
			if(!node.isLeaf())
			{
				Cut cut = node.Cut();
				float val = cut.Value();
				int dim = cut.Dimension();

				
				if(pt.get(dim) < val)
					node = node.LChild();
				else
					node = node.RChild();
				
				return findNext();
			}
			
			return null;
		}
		
		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public O next()
		{
			O curr = (O) next;
			next = findNext();
			return curr;
		}
	}
	
	/**
	 * The {@code QRYAll} class queries all objects in the {@code KDTree}.
	 *
	 * @author Waffles
	 * @since 04 Apr 2022
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 */
	public class QRYAll implements Iterator<O>
	{
		private O next;
		private Iterator<O> objects;
		private Iterator<KDNode<O>> nodes;
		
		/**
		 * Creates a new {@code QRYAll}.
		 */
		public QRYAll()
		{
			Iterable<KDNode<O>> search = DFSearch();
			objects = new EmptyIterator<>();
			nodes = search.iterator();
			next = findNext();
		}
		
		
		private O findNext()
		{
			if(objects.hasNext())
			{
				return objects.next();
			}
			
			if(nodes.hasNext())
			{
				objects = nodes.next().queryAll().iterator();
				return findNext();
			}
			
			return null;
		}
		
		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public O next()
		{
			O curr = (O) next;
			next = findNext();
			return curr;
		}
	}
	
	
	
	private int order;
	
	/**
	 * Creates a new {@code KDTree}.
	 * 
	 * @param order  a dimension order
	 */
	public KDTree(int order)
	{
		this.order = order;
	}
	
	/**
	 * Returns the order of the {@code KDTree}.
	 * 
	 * @return  a dimension order
	 */
	public int Order()
	{
		return order;
	}
	
	
	@Override
	public Iterable<O> query(ICuboid c)
	{
		return () -> new QRYCuboid(c);
	}

	@Override
	public Iterable<O> query(Point p)
	{
		return () -> new QRYPoint(p);
	}
	
	@Override
	public Iterator<O> iterator()
	{
		return new QRYAll();
	}

	
	@Override
	public KDNode<O> Root()
	{
		return (KDNode<O>) super.Root();
	}
	
	@Override
	public Bounds Bounds()
	{
		Vector min = Vectors.create(Floats.MAX_VALUE, order);
		Vector max = Vectors.create(Floats.MAX_VALUE, order);
		
		for(O obj : KDTree.this)
		{
			Vector oMin = obj.Bounds().Minimum();
			Vector oMax = obj.Bounds().Maximum();
			for(int i = 0; i < order; i++)
			{
				if(oMin.get(i) < min.get(i))
				{
					min.set(oMin.get(i), i);
				}
				
				if(oMax.get(i) < max.get(i))
				{
					max.set(oMax.get(i), i);
				}
			}
		}
		
		return Geometries.bounds(min, max);
	}
}