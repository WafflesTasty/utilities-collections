package zeno.util.coll.trees;

import java.util.Iterator;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.coll.Queue;
import zeno.util.coll.Tree;
import zeno.util.coll.geom.Space;
import zeno.util.coll.indices.List;
import zeno.util.geom.collidables.IGeometrical;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.tools.Integers;

/**
 * The {@code OrtTree} class defines the root of an orthogonal tree.
 *
 * @author Zeno
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see IGeometrical
 * @see OrtNode
 * @see Space
 * @see Tree
 */
public class OrtTree<O extends IGeometrical> extends OrtNode<O> implements Space<O>, Tree
{		
	/**
	 * The {@code PointSearch} class searches for potential collision detection with a point.
	 *
	 * @author Zeno
	 * @since 31 Jul 2020
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 */
	public class PointSearch implements Iterator<O>
	{
		private Point point;
		private OrtNode<O> curr;
		private Iterator<O> objects;

		/**
		 * Creates a new {@code PointSearch}.
		 * 
		 * @param p  a target point
		 * 
		 * 
		 * @see Point
		 */
		public PointSearch(Point p)
		{
			curr = OrtTree.this; point = p;
			objects = Objects().iterator();
		}
		
				
		@Override
		public boolean hasNext()
		{
			if(objects.hasNext())
			{
				return true;
			}

			if(curr.isLeaf())
			{
				return false;
			}
			
			int index = 0;
			Vector c = curr.Bounds().Center();
			for(int i = 0; i < curr.Bounds().Dimension(); i++)
			{
				if(c.get(i) < point.get(i) / point.Mass())
				{
					index += Integers.pow(2, i);
				}
			}
			
			curr = curr.Child(index);
			objects = curr.Objects().iterator();
			return hasNext();
		}
		
		@Override
		public O next()
		{
			return objects.next();
		}
	}
	
	/**
	 * The {@code BoxSearch} class searches for potential collisions with a cuboid.
	 *
	 * @author Zeno
	 * @since 31 Jul 2020
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 */
	public class BoxSearch implements Iterator<O>
	{
		private OrtNode<O> curr;
		private Queue<OrtNode<O>> queue;
		private Iterator<O> objects;
		private Point min, max;

		/**
		 * Creates a new {@code BoxSearch}.
		 * 
		 * @param b  a target box
		 * 
		 * 
		 * @see ICuboid
		 */
		public BoxSearch(ICuboid b)
		{
			queue = new Queue<>();
			objects = Objects().iterator();
			min = new Point(b.Minimum(), 1f);
			max = new Point(b.Maximum(), 1f);
			curr = OrtTree.this;
		}
		
		
		private void findChildren()
		{
			List<Integer> indices = new List<>(0);
			
			Vector c = curr.Bounds().Center();
			for(int i = 0; i < curr.Bounds().Dimension(); i++)
			{
				if(c.get(i) < min.get(i))
				{
					int count = indices.Count();
					for(int j = 0; j < count; j++)
					{
						int index = indices.get(j);
						index += Integers.pow(2, i);
						indices.put(index, j);
					}
					
					continue;
				}
				
				if(c.get(i) < max.get(i))
				{
					int count = indices.Count();
					for(int j = 0; j < count; j++)
					{
						int index = indices.get(j);
						index += Integers.pow(2, i);
						indices.add(index);
					}
					
					continue;
				}
			}
			
			for(Integer i : indices)
			{
				queue.pushFirst(curr.Child(i));
			}
		}
				
		@Override
		public boolean hasNext()
		{
			if(objects.hasNext())
			{
				return true;
			}

			if(!curr.isLeaf())
				findChildren();
			else if(queue.isEmpty())
			{
				return false;
			}
			
			curr = queue.popFirst();
			objects = curr.Objects().iterator();
			return hasNext();
		}
		
		@Override
		public O next()
		{
			return objects.next();
		}
	}
	
	
	/**
	 * Creates a new {@code OrtTree}.
	 * 
	 * @param b  a bounds cuboid
	 * 
	 * 
	 * @see ICuboid
	 */
	public OrtTree(ICuboid b)
	{
		super(b);
	}
	
	/**
	 * Creates a new {@code OrtTree}.
	 * 
	 * @param c  a bounds center
	 * @param s  a bounds size
	 * 
	 * 
	 * @see Vector
	 */
	public OrtTree(Vector c, Vector s)
	{
		super(c, s);
	}

	
	@Override
	public Iterable<OrtNode<O>> BFSearch()
	{
		return Tree.super.BFSearch();
	}
	
	@Override
	public Iterable<OrtNode<O>> DFSearch()
	{
		return Tree.super.DFSearch();
	}

	@Override
	public Iterable<O> query(ICuboid c)
	{
		if(!Bounds().contains(c))
			return Objects();
		else
		{
			return () -> new BoxSearch(c);
		}
	}

	@Override
	public Iterable<O> query(Point p)
	{
		if(!Bounds().contains(p))
			return Objects();
		else
		{
			return () -> new PointSearch(p);
		}
	}

	@Override
	public OrtNode<O> Root()
	{
		return this;
	}
	
	
	@Override
	public boolean contains(O obj)
	{
		if(!Bounds().contains(obj))
			return Objects().contains(obj);
		else
			return super.contains(obj);
	}

	@Override
	public void remove(O obj)
	{
		if(!Bounds().contains(obj))
			Objects().remove(obj);
		else
			super.remove(obj);
	}
	
	@Override
	public void add(O obj)
	{
		if(!Bounds().contains(obj))
			Objects().add(obj);
		else
			super.add(obj);
	}
}