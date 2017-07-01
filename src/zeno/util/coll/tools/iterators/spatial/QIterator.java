package zeno.util.coll.tools.iterators.spatial;

import java.util.Iterator;

import zeno.util.coll.spatial.OrthNode;

/**
 * The {@code QIterator} class recursively iterates over the children of a {@code OrthNode}.
 * 
 * @param <N>  the type of the iterated nodes
 * 
 * @since Apr 2, 2017
 * @author Zeno
 * 
 * @see Iterator
 * @see OrthNode
 */
public class QIterator<N extends OrthNode> implements Iterator<N>
{
	private int cIndex;
	private OrthNode root;
	
	
	private Iterator<OrthNode>[] children;
	
	/**
	 * Creates a new {@code QIterator}.
	 * 
	 * @param node  a root node
	 * @see OrthNode
	 */
	public QIterator(OrthNode node)
	{		
		root = node;
		if(!root.isLeaf())
		{
			children = new QIterator[4];
			for(int i = 0; i < 4; i++)
			{
				children[i] = new QIterator<>(root.Children()[i]);
			}
		}
		
		cIndex = -1;
	}
	
	
	@Override
	public boolean hasNext()
	{
		return cIndex == -1 || (children != null && children[3].hasNext());
	}

	@Override
	public N next()
	{
		if(cIndex == -1)
		{
			cIndex++;
			return (N) root;
		}
		
		Iterator<OrthNode> child = children[cIndex];
		if(child.hasNext())
		{
			return (N) child.next();
		}
		
		cIndex++;
		return next();
	}
}