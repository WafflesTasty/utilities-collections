package zeno.util.coll.trees;

import java.util.Iterator;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.coll.Collection;
import zeno.util.coll.Node;
import zeno.util.coll.hashed.HashedSet;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Integers;

/**
 * The {@code OrtNode} class defines a node in a orthogonally divided space.
 * </br> Each node divides k-dimensional space in 2^k equal sized child cuboids.
 * </br> The node's algorithm can be controlled by changing its maximum depth and object count. 
 *
 * @author Zeno
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Collection
 * @see IBounded
 * @see Node
 */
public class OrtNode<O extends IBounded> extends Node implements Collection<O>, IBounded
{		
	/**
	 * The {@code ObjectIterator} class iterates over all objects in a {@code OrtNode}.
	 *
	 * @author Zeno
	 * @since 30 Jul 2020
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 */
	public class ObjectIterator implements Iterator<O>
	{
		private Iterator<OrtNode<O>> iNodes;
		private Iterator<O> iObjects;
		
		/**
		 * Creates a new {@code ObjectIterator}.
		 */
		public ObjectIterator()
		{
			iObjects = Objects().iterator();
			iNodes = BFSearch().iterator();
			iNodes.next();
		}
		
		@Override
		public boolean hasNext()
		{
			if(iObjects.hasNext()) return true;
			if(iNodes.hasNext())
			{
				HashedSet<O> set = iNodes.next().Objects();
				iObjects = set.iterator();
				return hasNext();
			}
			
			return false;
		}

		@Override
		public O next()
		{
			return iObjects.next();
		}
	}
		
	
	private ICuboid bounds;
	private HashedSet<O> set;
			
	/**
	 * Creates a new {@code OrtNode}.
	 * 
	 * @param b  a bounds cuboid
	 * 
	 * 
	 * @see ICuboid
	 */
	public OrtNode(ICuboid b)
	{
		set = new HashedSet<>();
		bounds = b;
	}
	
	/**
	 * Creates a new {@code OrtNode}.
	 * 
	 * @param c  a bounds center
	 * @param s  a bounds size
	 * 
	 * 
	 * @see Vector
	 */
	public OrtNode(Vector c, Vector s)
	{
		this(Geometries.cuboid(c, s));
	}
	
	/**
	 * Returns the node's objects.
	 * 
	 * @return  an object set
	 * 
	 * 
	 * @see HashedSet
	 */
	public HashedSet<O> Objects()
	{
		return set;
	}
	
		
	@Override
	public OrtNode<O> Child(int i)
	{
		return super.Child(i);
	}
	
	@Override
	public Iterable<OrtNode<O>> Children()
	{
		return super.Children();
	}
	
	@Override
	public Iterable<OrtNode<O>> BFSearch()
	{
		return super.BFSearch();
	}
	
	@Override
	public Iterable<OrtNode<O>> DFSearch()
	{
		return super.DFSearch();
	}
	
	@Override
	public Iterator<O> iterator()
	{
		return new ObjectIterator();
	}
		
	@Override
	public ICuboid Bounds()
	{
		return bounds;
	}
	
		
	@Override
	public void add(O obj)
	{		
		int index = search(obj);
		if(index == -1)
			set.add(obj);
		else
		{
			if(ChildCount() == 0)
			{
				splitNode();
			}

			Child(index).add(obj);
		}
	}

	@Override
	public void remove(O obj)
	{
		if(set.contains(obj))
		{
			set.remove(obj);
			return;
		}
		
		int index = search(obj);
		if(index == -1)
			set.remove(obj);
		else
		{
			Child(index).remove(obj);
			if(Count() == set.Count())
			{
				clearChildren();
			}
		}
	}

	@Override
	public boolean contains(O obj)
	{
		int index = search(obj);
		if(index == -1)
			return set.contains(obj);
		else
		{
			return Child(index).contains(obj);
		}
	}

	@Override
	public int Count()
	{
		int count = set.Count();
		for(OrtNode<O> child : Children())
		{
			count += child.Count();
		}

		return count;
	}
	
	
	private int search(O obj)
	{
		Point min = new Point(obj.Bounds().Minimum(), 1f);
		Point max = new Point(obj.Bounds().Maximum(), 1f);
		Vector c = Bounds().Center();
		
		int index = 0;
		for(int i = 0; i < Bounds().Dimension(); i++)
		{
			if(c.get(i) < min.get(i))
			{
				index += Integers.pow(2, i);
				continue;
			}
			
			if(c.get(i) < max.get(i))
			{
				return -1;
			}
		}
		
		return index;
	}
	
	private void splitNode()
	{
		Vector c = Bounds().Center();
		Vector s = Bounds().Size().times(0.5f);
		for(int i = 0; i < Integers.pow(2, s.Size()); i++)
		{
			Vector v = Vectors.create(s.Size());
			for(int j = 0; j < s.Size(); j++)
			{
				if(Integers.bitAt(i, j) == 0)
					v.set(c.get(j) - s.get(j) / 2, j);
				else
					v.set(c.get(j) + s.get(j) / 2, j);
			}
			
			addChild(new OrtNode<>(v, s));
		}
	}
}