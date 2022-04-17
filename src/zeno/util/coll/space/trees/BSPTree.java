package zeno.util.coll.space.trees;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.coll.Collection;
import zeno.util.coll.space.trees.KDNode.Cut;
import zeno.util.geom.collidables.ICollision.Response;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICuboid;

/**
 * The {@code BSPTree} class defines a dynamic binary partition tree.
 *
 * @author Waffles
 * @since 06 Apr 2022
 * @version 1.0
 *
 *
 * @param <O>  an object type
 * @see Collection
 * @see IBounded
 * @see KDTree
 */
public class BSPTree<O extends IBounded> extends KDTree<O> implements Collection<O>
{
	/**
	 * Creates a new {@code BSPTree}.
	 * 
	 * @param dim  a space dimension
	 */
	public BSPTree(int dim)
	{
		super(dim);
	}
		

	@Override
	public void clear()
	{
		setRoot(null);
	}

	@Override
	public void remove(O obj)
	{
		BSPNode<O> node = search(obj);
		if(node != null)
		{
			node.remove(obj);
		}
	}

	@Override
	public boolean contains(O obj)
	{
		BSPNode<O> node = search(obj);
		if(node == null) return false;
		return node.contains(obj);
	}

	@Override
	public void add(O obj)
	{
		// Find the closest tree node.
		BSPNode<O> node = search(obj);
		// If none exists...
		if(node == null)
		{
			// Add the object to the root.
			node = new BSPNode<>(this);
			node.add(obj); setRoot(node);
			return;
		}
		
		ICuboid cb1 = obj.Bounds().Box();

		
		O abj = null;
		Vector dst = null;
		// Otherwise, find a new neighbor.
		if(node.isLeaf())
		{
			for(O o : node.queryAll())
			{
				ICuboid cb2 = o.Bounds().Box();
				Response rsp = cb1.intersect(cb2);
				dst = rsp.Distance();
				if(dst != null)
				{
					abj = o;
					break;
				}
			}
		}
		
		// If no neighbor exists...
		if(abj == null)
		{
			// Add the object to the node.
			node.add(obj);
			return;
		}
		
		
		// Otherwise, compute the new cut.
		int dim = 0;
		for(int i = 0; i < dst.Size(); i++)
		{
			if(dst.get(dim) < dst.get(i))
			{
				dim = i;
			}
		}
		
		float val = dst.get(dim);
		if(val < 0)
			val = cb1.Minimum().get(dim);
		else
			val = cb1.Maximum().get(dim);
		
		
		// Move the objects to the child nodes.
		BSPNode<O> lchild = new BSPNode<>(this);
		if(val > 0) lchild.add(obj);
		else lchild.add(abj);
		BSPNode<O> rchild = new BSPNode<>(this);
		if(val > 0) lchild.add(abj);
		else lchild.add(obj);
		
		
		node.remove(abj);
		node.setCut(dim, val);
		node.setLChild(lchild);
		node.setRChild(rchild);
	}

	
	BSPNode<O> search(O obj)
	{
		Vector min = obj.Bounds().Minimum();
		Vector max = obj.Bounds().Maximum();
		
		BSPNode<O> node = Root();
		while(!node.isLeaf())
		{
			Cut cut = node.Cut();
			int dim = cut.Dimension();
			float val = cut.Value();
			
			if(max.get(dim) <= val)
			{
				node = node.LChild();
				continue;
			}

			if(val <= min.get(dim))
			{
				node = node.RChild();
				continue;
			}

			return node;
		}
		
		return node;
	}
	
	@Override
	public BSPNode<O> Root()
	{
		return (BSPNode<O>) super.Root();
	}
	
	@Override
	public int Count()
	{
		int count = 0;
		
		Iterable<BSPNode<O>> nodes = BFSearch();
		for(BSPNode<O> node : nodes)
		{
			count += node.Count();
		}
		
		return count;
	}
}