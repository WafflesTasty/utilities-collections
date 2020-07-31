package zeno.util.coll.utilities.relations.nodes.array;

import zeno.util.coll.utilities.relations.nodes.INodal;
import zeno.util.coll.utilities.relations.nodes.INode;
import zeno.util.tools.helper.Iterables;

/**
 * The {@code INodeArray} interface defines a {@code INodal} implementation with a child array.
 *
 * @author Zeno
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @see INodal
 */
public interface INodeArray extends INodal
{
	/**
	 * Returns the array of the {@code INodeArray}.
	 * 
	 * @return  a child array
	 * 
	 * 
	 * @see INode
	 */
	public abstract INode[] Array();
	
	/**
	 * Returns a child from the {@code INodeArray}.
	 * 
	 * @param i  a child node index
	 * @return  a child node
	 * 
	 * 
	 * @see INode
	 */
	public default <N extends INode> N Child(int i)
	{
		if(Array() != null 
		&& Array().length > i)
		{
			return (N) Array()[i];
		}
		
		return null;
	}
	
	/**
	 * Returns the index of the {@code INodeArray}.
	 * </br> This is the index of the node as a child of its parent.
	 * 
	 * @return  a nodal index
	 */
	public default int Index()
	{
		int index = 0;
		// Assumes the iterator starts at zero.
		INodeArray parent = (INodeArray) Parent().Relations();
		for(INode child : parent.Children())
		{
			if(child == this)
				return index;
			index++;
		}
		
		return -1;
	}


	@Override
	public default <N extends INode> Iterable<N> Children()
	{
		return Iterables.of(Array());
	}
}