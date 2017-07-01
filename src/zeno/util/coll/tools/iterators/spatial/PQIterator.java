package zeno.util.coll.tools.iterators.spatial;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

import zeno.util.coll.spatial.surfaces.qtrees.PQNode;
import zeno.util.geom.tools.bounds.Bounded2D;

/**
 * The {@code PQIterator} class recursively iterates over the objects of a {@code PQNode}.
 * 
 * @param <O>  the type of the iterated objects
 * 
 * @since Apr 2, 2017
 * @author Zeno
 * 
 * @see Bounded2D
 * @see Iterator
 */
public class PQIterator<O extends Bounded2D> implements Iterator<O>
{	
	private O next;
	private Queue<O> objects;
	private Iterator<PQNode<O>> nodes;
	
	/**
	 * Creates a new {@code PQIterator}.
	 * 
	 * @param nodes  a node iterator
	 * @see Iterator
	 * @see PQNode
	 */
	public PQIterator(Iterator<PQNode<O>> nodes)
	{
		this.nodes = nodes;
		Set<O> set = nodes.next().Objects();
		// Can't iterate directly over the set
		// to prevent concurrent modification.
		objects = new ArrayDeque<>(set);
		next();
	}
	
	/**
	 * Creates a new {@code PQIterator}.
	 * 
	 * @param root  a root node
	 * @see PQNode
	 */
	public PQIterator(PQNode<O> root)
	{
		this(new QIterator<>(root));
	}
	
	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public O next()
	{
		O curr = next;
		while(objects.isEmpty())
		{
			if(!nodes.hasNext())
			{
				next = null;
				return curr;
			}
			
			PQNode<O> node = nodes.next();
			objects = new ArrayDeque<>(node.Objects());
		}
		
		next = objects.poll();
		return curr;
	}
}