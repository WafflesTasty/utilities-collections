package waffles.utils.sets.arboreal.binary.indexed.iterators;

import java.util.Iterator;

import waffles.utils.sets.arboreal.binary.indexed.BIPNode;

/**
 * A {@code QRYNodes} iterator queries {@code BIPNodes} at a specific coordinate.
 *
 * @author Waffles
 * @since 13 Feb 2026
 * @version 1.1
 *
 *
 * @param <N>  a node type
 * @see Iterator
 * @see BIPNode
 */
public class QRYNodes<N extends BIPNode> implements Iterator<N>
{
	private int[] crds;
	private BIPNode next;
	
	/**
	 * Creates a new {@code QRYNodes}.
	 * 
	 * @param n  a parent node
	 * @param c  a coordinate index
	 * 
	 * 
	 * @see BIPNode
	 */
	public QRYNodes(BIPNode n, int... c)
	{
		crds = c;
		next = n;
	}

	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public N next()
	{
		N curr = (N) next;
		next = curr.childAt(crds);
		return curr;
	}
}