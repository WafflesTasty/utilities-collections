package zeno.util.coll.utilities.relations;

/**
 * The {@code IConnected} interface defines an object which can be connected with other objects.
 *
 * @author Zeno
 * @since 28 Feb 2020
 * @version 1.0
 */
public interface IConnected
{
	/**
	 * Returns the relations of the {@code IConnected}.
	 * 
	 * @return  a relations property
	 * 
	 * 
	 * @see IConnections
	 */
	public abstract IConnections Relations();
}