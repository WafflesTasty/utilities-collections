package zeno.util.coll.utilities.relations;

/**
 * The {@code IRelatable} interface defines an object which can be related to other objects.
 *
 * @author Zeno
 * @since Mar 5, 2016
 * @version 1.0
 * 
 * 
 * @see IConnected
 */
public interface IRelatable extends IConnected
{		
	@Override
	public abstract IRelations Relations();
}