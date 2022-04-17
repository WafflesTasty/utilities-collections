package zeno.util.coll.space.trees;

import zeno.util.coll.indices.List;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICuboid;

/**
 * The {@code BSPNode} defines a single node in a {@code BSPTree}.
 * </br> It extends the {@code KDNode} with a variable list of items.
 *
 * @author Waffles
 * @since 05 Apr 2022
 * @version 1.0
 *
 *
 * @param <O>  an object type
 * @see IBounded
 * @see KDNode
 */
public class BSPNode<O extends IBounded> extends KDNode<O>
{
	private List<O> items;
	
	/**
	 * Creates a new {@code BSPNode}.
	 * 
	 * @param tree  a target tree
	 */
	public BSPNode(BSPTree<O> tree)
	{
		super(tree);
		items = new List<>();
	}
	
	/**
	 * Checks for an item in the {@code BSPNode}.
	 * 
	 * @param item  an item to check
	 * @return  {@code true} if the item is here
	 */
	public boolean contains(O item)
	{
		return items.contains(item);
	}
	
	/**
	 * Removes an item from the {@code BSPNode}.
	 * 
	 * @param item  an item to remove
	 */
	public void remove(O item)
	{
		items.remove(item);
	}
	
	/**
	 * Adds an item to the {@code BSPNode}.
	 * 
	 * @param item  an item to add
	 */
	public void add(O item)
	{
		items.add(item);
	}

	/**
	 * Counts items in the {@code BSPNode}.
	 * 
	 * @return  an item count
	 */
	public int Count()
	{
		return items.Count();
	}
	
	
	@Override
	public Iterable<O> query(ICuboid c)
	{
		return items;
	}

	@Override
	public Iterable<O> query(Point p)
	{
		return items;
	}

	@Override
	public Iterable<O> queryAll()
	{
		return items;
	}


	@Override
	public BSPNode<O> Parent()
	{
		return (BSPNode<O>) super.Parent();
	}
	
	@Override
	public BSPNode<O> LChild()
	{
		return (BSPNode<O>) super.LChild();
	}
	
	@Override
	public BSPNode<O> RChild()
	{
		return (BSPNode<O>) super.RChild();
	}
}