package zeno.util.coll.utilities.relations;

import zeno.util.tools.helper.Iterables;
import zeno.util.tools.patterns.Decorator;

/**
 * The {@code IConnections} interface governs the behavior of an {@code IConnected}.
 *
 * @author Zeno
 * @since 28 Feb 2020
 * @version 1.0
 * 
 * 
 * @see IConnected
 * @see Decorator
 */
public interface IConnections extends Decorator<IConnected>
{
	/**
	 * The {@code Orphan} class defines an empty {@code IConnections}.
	 *
	 * @author Zeno
	 * @since 28 Feb 2020
	 * @version 1.0
	 * 
	 * 
	 * @see IConnections
	 */
	public static class Orphan implements IConnections
	{
		private IConnected target;
		
		/**
		 * Creates a new {@code Orphan}.
		 * 
		 * @param tgt  a target object
		 * 
		 * 
		 * @see IConnected
		 */
		public Orphan(IConnected tgt)
		{
			target = tgt;
		}
		
		@Override
		public <N extends IConnected> Iterable<N> Neighbors()
		{
			return Iterables.empty();
		}
		
		@Override
		public IConnected Delegate()
		{
			return target;
		}
	}
	
	/**
	 * Returns all connected neighbors in the {@code IConnections}.
	 * 
	 * @param <N>  an object type
	 * @return  a set of neighbors
	 * 
	 * 
	 * @see IConnected
	 * @see Iterable
	 */
	public abstract <N extends IConnected> Iterable<N> Neighbors();
}
