package zeno.util.coll.utilities.relations.nodes;

import zeno.util.coll.utilities.relations.IConnected;
import zeno.util.coll.utilities.relations.IRelations;
import zeno.util.tools.Integers;
import zeno.util.tools.helper.Iterables;

/**
 * The {@code INodal} interface defines relations of an {@code INode}.
 *
 * @author Zeno
 * @since Sep 22, 2019
 * @version 1.0
 * 
 * 
 * @see IRelations
 */
public interface INodal extends IRelations
{	
	/**
	 * The {@code Orphan} class defines an empty {@code INodal}.
	 *
	 * @author Zeno
	 * @since Sep 27, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see IRelations
	 * @see INodal
	 */
	public static class Orphan extends IRelations.Orphan implements INodal
	{
		/**
		 * Creates a new {@code Orphan}.
		 * 
		 * @param tgt  a target relatable
		 * 
		 * 
		 * @see INode
		 */
		public Orphan(INode tgt)
		{
			super(tgt);
		}

		
		@Override
		public <N extends INode> Iterable<N> Children()
		{
			return Iterables.empty();
		}
	
		@Override
		public INode Delegate()
		{
			return (INode) super.Delegate();
		}
	}

	
	@Override
	public abstract INode Delegate();
	
	@Override
	public default <R extends IConnected> Iterable<R> Neighbors()
	{
		Iterable<R> children = (Iterable<R>) Children();
		Iterable<R> parent = IRelations.super.Neighbors();
		
		return (Iterable<R>) Iterables.compose(parent, children);
	}
	
	
	/**
	 * Returns the children of the {@code INodal}.
	 * 
	 * @return  a set of child nodes
	 * 
	 * 
	 * @param <N> an object type
	 * @see Iterable
	 * @see INode
	 */
	public abstract <N extends INode> Iterable<N> Children();
	
	
	/**
	 * Checks whether this {@code INode} is a leaf.
	 * 
	 * @return  {@code true} if the node is a leaf
	 */
	public default boolean isLeaf()
	{
		for(INode child : Children())
		{
			if(child != null)
			{
				return false;
			}
		}
		
		return true;
	}
		
	/**
	 * Returns the descendant count of the {@code INode}.
	 * </br> This indicates the amount of nodes under this node plus one.
	 * 
	 * @return  a descendant count
	 */
	public default int Descendants()
	{
		int count = 1;
		for(INode child : Children())
		{
			if(child != null)
			{
				count += child.Relations().Descendants();
			}
		}
		
		return count;
	}

	/**
	 * Returns the node height of the {@code INode}.
	 * </br> This indicates the relative depth of the deepest child node.
	 * 
	 * @return  a node height
	 */
	public default int Height()
	{
		if(!isLeaf())
		{
			int max = 0;
			for(INode child : Children())
			{
				max = Integers.max(max, child.Relations().Height());
			}
			
			return 1 + max;
		}
		
		return 0;
	}
}