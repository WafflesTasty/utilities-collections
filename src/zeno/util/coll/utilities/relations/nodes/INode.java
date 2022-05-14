package zeno.util.coll.utilities.relations.nodes;

import zeno.util.coll.utilities.relations.IRelatable;
import zeno.util.tools.patterns.Properties;

/**
 * The {@code INode} interface defines a relatable object with parent-child behavior.
 *
 * @author Waffles
 * @since Sep 22, 2019
 * @version 1.0
 * 
 * 
 * @see IRelatable
 * @see Properties
 */
public interface INode extends IRelatable, Properties
{
	@Override
	public abstract INodal Relations();
}