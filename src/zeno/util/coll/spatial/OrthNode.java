package zeno.util.coll.spatial;

import java.util.Iterator;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.coll.tools.iterators.spatial.QIterator;
import zeno.util.geom.shapes.ICuboid;
import zeno.util.geom.tools.bounds.Bounded;
import zeno.util.tools.generic.INode;
import zeno.util.tools.primitives.Integers;

/**
 * The {@code OrthNode} class defines a node fit for orthtree structures.
 * 
 * @since Apr 3, 2017
 * @author Zeno
 * 
 * @see ICuboid
 * @see INode
 */
public abstract class OrthNode implements ICuboid, INode
{	
	private OrthNode parent;
	private OrthNode[] children;
	private ICuboid bounds;
	
	/**
	 * Creates a new {@code OrthNode}.
	 * 
	 * @param i  a child array index
	 * @param node  a parent node
	 */
	public OrthNode(int i, OrthNode node)
	{
		parent = node;
		
		Vector size = node.Size().times(0.5f);
		Vector center = Vector.create(node.Dimension());
		
		int index = i;
		for(int j = 0; j < node.Dimension(); j++)
		{
			float coord = size.get(j) * (index % 2 - 0.5f);
			center.set(coord + node.Center().get(j), j);
			index = index / 2;
		}

		bounds = ICuboid.create(center, size);
	}
	
	/**
	 * Creates a new {@code OrthNode}.
	 * 
	 * @param dim  the node dimension
	 */
	public OrthNode(int dim)
	{
		bounds = ICuboid.create(dim);
	}
	
		
	/**
	 * Creates a new {@code OrthNode} child.
	 * 
	 * @param i  the child's array index
	 * @return  a new child node
	 */
	public abstract OrthNode createChild(int i);
	
	
	/**
	 * Returns the {@code OrthNode} containing a point.
	 * 
	 * @param v  the requested point
	 * @return  the containing node
	 * @see Vector
	 */
	public <N extends OrthNode> N getNode(Vector v)
	{
		OrthNode child = find(v);
		if(equals(child))
		{
			return (N) this;
		}
		
		return child.getNode(v);
	}
	
	/**
	 * Returns the {@code OrthNode} containing an object.
	 * 
	 * @param object  the requested object
	 * @return  the containing node
	 * @see Bounded
	 */
	public <N extends OrthNode> N getNode(Bounded object)
	{
		OrthNode child = find(object);
		if(equals(child))
		{
			return (N) this;
		}
		
		return child.getNode(object);
	}
	
	/**
	 * Iterates over all intersecting nodes in the {@code OrthNode}.
	 * <br> All nodes iterated contain the requested point.
	 * 
	 * @param v  the requested point
	 * @return  a node iterator
	 * @see Iterable
	 * @see Vector
	 */
	public <N extends OrthNode> Iterable<N> nodes(Vector v)
	{
		return () -> new Iterator<N>()
		{
			private OrthNode curr = OrthNode.this;
						
			@Override
			public boolean hasNext()
			{
				return curr != null;
			}

			@Override
			public N next()
			{
				OrthNode next = curr;
				OrthNode node = curr.find(v);
				
				if(!curr.equals(node))
					curr = node;
				else
					curr = null;
				
				return (N) next;
			}	
		};
	}
	
	/**
	 * Iterates over all intersecting nodes in the {@code OrthNode}.
	 * <br> All nodes iterated intersect the requested object.
	 * 
	 * @param object  the requested object
	 * @return  a node iterator
	 * @see Iterable
	 * @see Bounded
	 */
	public <N extends OrthNode> Iterable<N> nodes(Bounded object)
	{
		return () -> new Iterator<N>()
		{
			private OrthNode curr = OrthNode.this;
			private QIterator<N> iterator;
						
			@Override
			public boolean hasNext()
			{
				return curr != null || iterator.hasNext();
			}

			@Override
			public N next()
			{
				if(iterator != null)
				{
					return iterator.next();
				}
				
				OrthNode next = curr;
				OrthNode node = curr.find(object);
				
				if(!curr.equals(node))
					curr = node;
				else
				{
					iterator = new QIterator<>(curr);
					iterator.next();
					curr = null;
				}

				return (N) next;
			}	
		};
	}

	/**
	 * Iterates over all nodes in the {@code OrthNode}.
	 * 
	 * @return  a node iterator
	 * @see Iterable
	 */
	public <N extends OrthNode> Iterable<N> nodes()
	{
		return () -> new QIterator<>(this);
	}

	
	@Override
	public ICuboid Bounds()
	{
		return bounds;
	}
	
	@Override
	public OrthNode[] Children()
	{
		return children;
	}
	
	@Override
	public OrthNode Parent()
	{
		return parent;
	}
	
	@Override
	public OrthNode copy()
	{
		OrthNode copy = (OrthNode) INode.super.copy();

		copy.bounds = bounds;
		
		if(children != null)
		{
			for(int i = 0; i < children.length; i++)
			{
				copy.set(i, children[i].copy());
			}
		}
		
		return copy;
	}
	
	
	
	protected OrthNode find(Vector v)
	{
		if(children != null)
		{
			int min = 0;
			int max = Integers.pow(2, Dimension()) - 1;
			for(int i = Dimension() - 1; i >= 0; i--)
			{
				if(v.get(i) < Center().get(i))
				{
					max -= Integers.pow(2, i);
					continue;
				}
				
				if(v.get(i) > Center().get(i))
				{
					min += Integers.pow(2, i);
					continue;
				}
				
				return this;
			}
			
			if(min == max)
			{
				return children[min];
			}
		}
		
		return this;
	}

	protected OrthNode find(Bounded object)
	{
		if(children != null)
		{
			Vector oMin = object.Minimum();
			Vector oMax = object.Maximum();
			
			int min = 0;
			int max = Integers.pow(2, Dimension()) - 1;
			for(int i = Dimension() - 1; i >= 0; i--)
			{
				if(oMax.get(i) < Center().get(i))
				{
					max -= Integers.pow(2, i);
					continue;
				}
				
				if(oMin.get(i) > Center().get(i))
				{
					min += Integers.pow(2, i);
					continue;
				}
				
				return this;
			}
			
			if(min == max)
			{
				return children[min];
			}
		}
		
		return this;
	}
	
	
	protected void set(int i, OrthNode child)
	{
		if(children == null)
		{
			children = new OrthNode[Integers.pow(2, Dimension())];
		}
		
		children[i] = child;
		children[i].parent = this;
	}
	
	protected void setCenter(float... vals)
	{
		bounds = ICuboid.create(Vector.create(vals), bounds.Size());
	}
	
	protected void setSize(float... vals)
	{
		bounds = ICuboid.create(bounds.Center(), Vector.create(vals));
	}
			
	
	protected void merge()
	{
		children = null;
	}
	
	protected void split()
	{
		int count = Integers.pow(2, Dimension());
		
		children = new OrthNode[count];
		for(int i = 0; i < count; i++)
		{
			children[i] = createChild(i);
		}
	}
}