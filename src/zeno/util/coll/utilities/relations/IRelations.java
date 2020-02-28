package zeno.util.coll.utilities.relations;

import zeno.util.tools.patterns.Decorator;

/**
 * The {@code IRelations} interface governs the behavior of an {@code IRelatable}.
 *
 * @author Zeno
 * @since Sep 22, 2019
 * @version 1.0
 * 
 * 
 * @see IRelatable
 * @see Decorator
 */
public interface IRelations extends Decorator<IRelatable>
{	
	/**
	 * The {@code Orphan} class defines an empty {@code IRelations}.
	 *
	 * @author Zeno
	 * @since Sep 27, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see IRelations
	 */
	public static class Orphan implements IRelations
	{		
		private IRelatable target;
		
		/**
		 * Creates a new {@code Orphan}.
		 * 
		 * @param tgt  a target relatable
		 * 
		 * 
		 * @see IRelatable
		 */
		public Orphan(IRelatable tgt)
		{
			target = tgt;
		}
		
		
		@Override
		public <R extends IRelatable> R Parent()
		{
			return null;
		}
		
		@Override
		public IRelatable Delegate()
		{
			return (IRelatable) target;
		}
	}
	
	
	/**
	 * Returns the parent of the {@code IRelations}.
	 * 
	 * @return  an object parent
	 * 
	 * 
	 * @see IRelatable
	 */
	public abstract <R extends IRelatable> R Parent();
		
	/**
	 * Returns the root of the {@code IRelations}.
	 * 
	 * @return  the root object
	 * 
	 * 
	 * @see IRelatable
	 */
	public default IRelatable Root()
	{
		IRelatable parent = Parent();
		if(parent != null)
		{
			return parent.Relations().Root();
		}
		
		return Delegate();
	}
	
	
	/**
	 * Checks whether this {@code IRelatable} is a root.
	 * 
	 * @return  {@code true} if the node is a root
	 */
	public default boolean isRoot()
	{
		return Parent() == null;
	}

	/**
	 * Checks if this {@code IRelatable} is a node's descendant.
	 * 
	 * @param node  a node to check for
	 * @return  {@code true} if this node is a descendant
	 */
	public default boolean isInside(IRelatable node)
	{
		if(Delegate().equals(node))
		{
			return true;
		}
		
		IRelatable parent = Parent();
		if(parent == null)
		{
			return false;
		}
		
		IRelations rel = parent.Relations();
		return rel.isInside(node);
	}
		
	/**
	 * Returns the depth of the {@code IRelatable}.
	 * <br> This indicates the height of the highest parent object.
	 * 
	 * @return  a relation depth
	 */
	public default int Depth()
	{
		IRelatable parent = Parent();
		if(parent != null)
		{
			return parent.Relations().Depth() + 1;
		}
		
		return 0;
	}
}