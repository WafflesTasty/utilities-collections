package zeno.util.coll.spatial.surfaces.qtrees;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.coll.spatial.surfaces.Space2D;
import zeno.util.coll.tools.iterators.spatial.PQIterator;
import zeno.util.geom.shapes.surfaces.Rectangle;
import zeno.util.geom.tools.bounds.Bounded2D;

/**
 * The {@code PQTree} class defines a static-sized point quad tree.
 * <br> This type of tree stores a list of objects in each of its nodes.
 * <br> It is useful for broad phase collision detection for a 
 * large number of static two-dimensional objects.
 * 
 * @param <O>  the type of objects in the tree
 * 
 * @since Apr 2, 2017
 * @author Zeno
 *
 * @see Bounded2D
 * @see Space2D
 * @see PQNode
 */
public class PQTree<O extends Bounded2D> extends PQNode<O> implements Space2D<O>
{
	private static final int DEFAULT = 4;

	
	/**
	 * Creates a new {@code PQTree}.
	 */
	public PQTree()
	{
		setMaxDepth(DEFAULT);
		setObjectLimit(DEFAULT);
	}
	
	/**
	 * Creates a new {@code PQTree}.
	 * 
	 * @param w  the tree's width
	 * @param h  the tree's height
	 */
	public PQTree(float w, float h)
	{
		setMaxDepth(DEFAULT);
		setObjectLimit(DEFAULT);
		setCenter(w / 2, h / 2);
		setSize(w, h);
	}
	
	
	/**
	 * Changes the maximum depth of the {@code QTree}.
	 * 
	 * @param depth  a maximum depth
	 */
	@Override
	public void setMaxDepth(int depth)
	{
		super.setMaxDepth(depth);
	}
		
	/**
	 * Changes the object limit of the {@code QTree}.
	 * 
	 * @param limit  an object limit
	 */
	@Override
	public void setObjectLimit(int limit)
	{
		super.setObjectLimit(limit);
	}
	
	/**
	 * Changes the center of the {@code QTree}.
	 * 
	 * @param x  a new x-coördinate
	 * @param y  a new y-coördinate
	 */
	public void setCenter(float x, float y)
	{
		super.setCenter(x, y);
	}
	
	/**
	 * Changes the size of the {@code QTree}.
	 * 
	 * @param w  a new width
	 * @param h  a new height
	 */
	public void setSize(float w, float h)
	{
		super.setSize(w, h);
	}
	

	/**
	 * Checks if the {@code PQTree} contains an object.
	 * 
	 * @param object  an object to check
	 * @return  {@code true} if the object is contained
	 */
	public boolean contains(O object)
	{
		if(!contains(object.Bounds()))
		{
			return containsObject(object);
		}
		
		return test(object);
	}
	
	/**
	 * Removes an object from the {@code PQTree}.
	 * 
	 * @param object  an object to remove
	 * @return  {@code true} if the object is removed
	 */
	public boolean remove(O object)
	{
		if(!contains(object.Bounds()))
		{
			return removeObject(object);
		}
		
		return pull(object);
	}
	
	/**
	 * Adds an object to the {@code PQTree}.
	 * 
	 * @param object  an object to add
	 * @return  {@code true} if the object is added
	 */
	public boolean add(O object)
	{	
		if(!contains(object.Bounds()))
		{
			return addObject(object);
		}
		
		return push(object);
	}	
	
	
	@Override
	public Iterable<O> query(float x, float y, float w, float h)
	{
		Iterable<PQNode<O>> nodes = nodes(new Rectangle(x, y, w, h));
		return () -> new PQIterator<>(nodes.iterator());
	}

	@Override
	public Iterable<O> query(float x, float y)
	{
		Iterable<PQNode<O>> nodes = nodes(new Vector(x, y));
		return () -> new PQIterator<>(nodes.iterator());
	}
	
	@Override
	public PQTree<O> instance()
	{
		return new PQTree<>();
	}
	
	@Override
	public void clear()
	{
		super.clear();
	}
}