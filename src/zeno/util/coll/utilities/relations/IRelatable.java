package zeno.util.coll.utilities.relations;

/**
 * The {@code IRelatable} interface defines an object which can be related to other objects.
 *
 * @author Zeno
 * @since Mar 5, 2016
 * @version 1.0
 */
public interface IRelatable
{		
	/**
	 * Returns the relations of the {@code IRelatable}.
	 * 
	 * @return  a relations property
	 * 
	 * 
	 * @see IRelations
	 */
	public abstract IRelations Relations();
}