package zeno.util.coll.spatial.surfaces.qtrees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import zeno.util.coll.spatial.OrthNode;
import zeno.util.coll.tools.iterators.spatial.PQIterator;
import zeno.util.geom.IGeometry2D;
import zeno.util.geom.shapes.surfaces.Rectangle;
import zeno.util.geom.tools.bounds.Bounded2D;

/**
 * The {@code PQNode} class defines a single node in a point quad tree.
 * 
 * @param <O>  the type of objects in the tree
 * 
 * @since Apr 2, 2017
 * @author Zeno
 *
 * @see IGeometry2D
 * @see Bounded2D
 * @see Iterable
 * @see OrthNode
 */
public class PQNode<O extends Bounded2D> extends OrthNode implements IGeometry2D, Iterable<O>
{	
	private int dMax, oMax;
	private Set<O> objects;
	
	/**
	 * Creates a new {@code PQNode}.
	 * 
	 * @param i  the node's array index
	 * @param parent  the node's parent
	 */
	public PQNode(int i, PQNode<O> parent)
	{
		super(i, parent);
		
		objects = new HashSet<>();
		dMax = parent.dMax - 1;
		oMax = parent.oMax;
	}
	
	/**
	 * Creates a new {@code PQNode}.
	 */
	public PQNode()
	{
		super(2); objects = new HashSet<>();
	}
	
	
	/**
	 * Returns the objects in the {@code PQNode}.
	 * 
	 * @return  the node's objects
	 * @see Set
	 */
	public Set<O> Objects()
	{
		return objects;
	}
		
	/**
	 * Returns the object limit of the {@code PQNode}.
	 * 
	 * @return  the node's object limit
	 */
	public int ObjectLimit()
	{
		return oMax;
	}
	
	/**
	 * Returns the maximum depth of the {@code PQNode}.
	 * 
	 * @return  the node's maximum depth
	 */
	public int MaxDepth()
	{
		return dMax;
	}
	
	/**
	 * Returns the count of the {@code PQNode}.
	 * 
	 * @return  the node's object count
	 */
	public int Count()
	{
		int count = 0;
		for(OrthNode node : nodes())
		{
			PQNode<O> curr = (PQNode<O>) node;
			count += curr.Objects().size();
		}
	
		return count;
	}
	
	
	
	@Override
	public PQNode<O> copy()
	{
		PQNode<O> copy = (PQNode<O>) super.copy();
		
		copy.objects = new HashSet<>(objects);
		copy.dMax = dMax; copy.oMax = oMax;
		
		return copy;
	}
		
	@Override
	public PQNode<O> Parent()
	{
		return (PQNode<O>) super.Parent();
	}
	
	@Override
	public PQNode<O> instance()
	{
		return new PQNode<>();
	}
			
	@Override
	public Rectangle Bounds()
	{
		return (Rectangle) super.Bounds();
	}
	
	
	@Override
	public PQNode<O> createChild(int i)
	{
		return new PQNode<>(i, this);
	}

	@Override
	public Iterator<O> iterator()
	{
		return new PQIterator<>(this);
	}
		
	@Override
	public boolean isEmpty()
	{
		if(objects.isEmpty())
		{
			return super.isEmpty();
		}
		
		return false;
	}

	
	
	protected boolean addObject(O object)
	{
		return objects.add(object);
	}
	
	protected boolean removeObject(O object)
	{
		return objects.remove(object);
	}
	
	protected boolean containsObject(O object)
	{
		return objects.contains(object);
	}
	
	
	protected void setObjectLimit(int limit)
	{
		oMax = limit;
	}

	protected void setMaxDepth(int depth)
	{
		dMax = depth;
	}	
	
		
	protected boolean pull(O object)
	{
		PQNode<O> child = getNode(object);
		if(child.removeObject(object))
		{
			PQNode<O> parent = child.Parent();
			if(parent != null)
			{				
				parent.merge();
			}
			
			return true;
		}
		
		return false;
	}
	
	protected boolean push(O object)
	{
		PQNode<O> child = getNode(object);
		if(child.hasSpace() || !child.isLeaf())
		{
			return child.addObject(object);
		}
		
		child.split();
		return child.push(object);
	}
	
	protected boolean test(O object)
	{
		PQNode<O> child = getNode(object);
		return child.containsObject(object);
	}
	
	protected boolean hasSpace()
	{
		return objects.size() < oMax|| dMax == 0;
	}
	
	
	protected void clear()
	{
		objects = null;
		merge();
	}
	
	@Override
	protected void merge()
	{	
		for(OrthNode sibling : Children())
		{
			if(!sibling.isEmpty())
			{
				return;
			}
		}
		
		super.merge();
		
		PQNode<O> parent = Parent();
		if(parent != null)
		{
			parent.merge();
		}
	}
	
	@Override
	protected void split()
	{
		super.split();
		
		List<O> queue = new ArrayList<>(objects);
		
		objects.clear();
		for(O object : queue)
		{
			push(object);
		}
	}
}