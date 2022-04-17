package zeno.util.coll.space.trees;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.coll.space.Space;
import zeno.util.coll.trees.binary.BiNode;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

/**
 * The {@code KDNode} class defines a node in a {@code KDTree}.
 *
 * @author Waffles
 * @since 17 Jan 2022
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see IBounded
 * @see BiNode
 * @see Space
 */
public abstract class KDNode<O extends IBounded> extends BiNode implements Space<O>
{
	/**
	 * The {@code Cut} class defines a plane that splits a {@code KDNode}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2021
	 * @version 1.1
	 */
	public static class Cut
	{
		private int dim;
		private float val;
		
		/**
		 * Creates a new {@code Cut}.
		 * 
		 * @param dim  a cut dimension
		 * @param val  a cut value
		 */
		public Cut(int dim, float val)
		{
			this.dim = dim;
			this.val = val;
		}
		
		/**
		 * Returns the dimension of the {@code Cut}.
		 * 
		 * @return  a cut dimension
		 */
		public int Dimension()
		{
			return dim;
		}
		
		/**
		 * Returns the value of the {@code Cut}.
		 * 
		 * @return  a cut value
		 */
		public float Value()
		{
			return val;
		}
	}

	
	private Cut cut;
	
	/**
	 * Creates a new {@code KDNode}.
	 * 
	 * @param tree  a parent tree
	 * 
	 * 
	 * @see KDTree
	 */
	public KDNode(KDTree<O> tree)
	{
		super(tree);
	}

	/**
	 * Queries objects in the {@code KDNode}.
	 * 
	 * @return  an object iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public abstract Iterable<O> queryAll();
	
	/**
	 * Changes the cut of the {@code KDNode}.
	 * 
	 * @param dim  a cut dimension
	 * @param val  a cut value
	 */
	public void setCut(int dim, float val)
	{
		setCut(new Cut(dim, val));
	}
	
	/**
	 * Changes the cut of the {@code KDNode}.
	 * 
	 * @param cut  a target cut
	 * 
	 * 
	 * @see Cut
	 */
	public void setCut(Cut cut)
	{
		this.cut = cut;
	}
		
	/**
	 * Returns the cut of the {@code KDNode}.
	 * 
	 * @return  a plane cut
	 * 
	 * 
	 * @see Cut
	 */
	public Cut Cut()
	{
		return cut;
	}
		
	
	@Override
	public ICuboid Bounds()
	{
		Bounds bnd = Tree().Bounds();
		
		Vector min = bnd.Minimum();
		Vector max = bnd.Maximum();
		
		KDNode<O> node = this;
		while(node.Parent() != null)
		{
			KDNode<O> p = node.Parent();
			int dim = p.Cut().Dimension();
			float val = p.Cut().Value();
			
			
			if(node.equals(p.LChild()))
				max.set(Floats.min(val, max.get(dim)), dim);
			else
				min.set(Floats.max(val, min.get(dim)), dim);
			
			node = p;
		}

		
		Vector s = max.minus(min);
		Vector c = min.plus(max).times(0.5f);
		return Geometries.cuboid(c, s);
	}
	
	@Override
	public KDNode<O> Parent()
	{
		return (KDNode<O>) super.Parent();
	}
	
	@Override
	public KDNode<O> LChild()
	{
		return (KDNode<O>) super.LChild();
	}
	
	@Override
	public KDNode<O> RChild()
	{
		return (KDNode<O>) super.RChild();
	}
	
	@Override
	public KDTree<O> Tree()
	{
		return (KDTree<O>) super.Tree();
	}
}