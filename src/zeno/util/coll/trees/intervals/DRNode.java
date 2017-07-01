package zeno.util.coll.trees.intervals;

import zeno.util.algebra.constants.Extreme;
import zeno.util.algebra.intervals.Cut;
import zeno.util.coll.trees.BiNode;

/**
 * The {@code DRNode} class defines a node in a {@link DRTree}.
 * 
 * @since Jul 1, 2017
 * @author Zeno
 * 
 * @see BiNode
 * @see Cut
 */
public class DRNode extends BiNode<Cut>
{	
	private Extreme extreme;
	
	/**
	 * Creates a new {@code DRNode}.
	 * 
	 * @param cut  an interval cut
	 * @param ex  an interval extreme
	 * @param tree  a source tree
	 * @see Extreme
	 * @see DRTree
	 * @see Cut
	 */
	public DRNode(Cut cut, Extreme ex, DRTree tree)
	{
		super(cut, tree); extreme = ex;
	}
	
	/**
	 * Returns the extreme of the {@code DRNode}.
	 * 
	 * @return  the node's extreme
	 * @see Extreme
	 */
	public Extreme Extreme()
	{
		return extreme;
	}

	
	@Override
	public String toString()
	{
		Cut cut = Object();
		if(extreme == Extreme.MIN)
		{
			return cut.toLowerBound() + ".";
		}
		
		return "." + cut.toUpperBound();
	}

	@Override
	public DRNode instance()
	{
		return new DRNode();
	}
	
	@Override
	public DRNode Parent()
	{
		return (DRNode) super.Parent();
	}
	
	@Override
	public DRNode copy()
	{
		DRNode copy = (DRNode) super.copy();
		copy.extreme = extreme;
		return copy;
	}

	
	DRNode()
	{
		super(null, null);
	}
}