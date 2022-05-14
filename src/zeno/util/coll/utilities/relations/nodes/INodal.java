package zeno.util.coll.utilities.relations.nodes;

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
	 * Checks whether this is a leaf {@code INode}.
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
	 * Returns the child count of the {@code INode}.
	 * </br> This only counts direct descendants of the node.
	 * 
	 * @return  a nodal child count
	 */
	public default int ChildCount()
	{
		int count = 0;
		for(INode child : Children())
		{
			if(child != null)
			{
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * Returns the node level of the {@code INode}.
	 * </br> This indicates the relative depth of the deepest child node.
	 * 
	 * @return  a node level
	 */
	public default int Level()
	{
		if(!isLeaf())
		{
			int max = 0;
			for(INode child : Children())
			{
				max = Integers.max(max, child.Relations().Level());
			}
			
			return 1 + max;
		}
		
		return 0;
	}

	/**
	 * Returns the size of the {@code INode}.
	 * </br> This indicates the amount of nodes under this node plus one.
	 * 
	 * @return  a node size
	 */
	public default int TreeSize()
	{
		int count = 1;
		for(INode child : Children())
		{
			if(child != null)
			{
				count += child.Relations().TreeSize();
			}
		}
		
		return count;
	}
}