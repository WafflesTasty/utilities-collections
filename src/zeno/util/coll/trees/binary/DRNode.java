package zeno.util.coll.trees.binary;

import zeno.util.algebra.intervals.Cut;
import zeno.util.calc.constants.Extreme;

/**
 * The {@code DRNode} class defines a node in a disjoint range tree.
 * 
 * @author Zeno
 * @since Jul 1, 2017
 * @version 1.0
 * 
 * 
 * @see BSNode
 * @see Cut
 */
public class DRNode extends BSNode<Cut>
{	
	private Extreme extreme;
	
	/**
	 * Creates a new {@code DRNode}.
	 * 
	 * @param t  a source tree
	 * @param cut  a real line cut
	 * @param ex   an interval extreme
	 * 
	 * 
	 * @see Extreme
	 * @see DRTree
	 * @see Cut
	 */
	public DRNode(DRTree t, Cut cut, Extreme ex)
	{
		super(t, cut); extreme = ex;
	}
	
	/**
	 * Returns the extreme of the {@code DRNode}.
	 * 
	 * @return  an interval extreme
	 * 
	 * 
	 * @see Extreme
	 */
	public Extreme Extreme()
	{
		return extreme;
	}

	
	@Override
	public String toString()
	{
		Cut cut = Value();
		if(extreme == Extreme.MIN)
		{
			return cut.toLowerBound() + ".";
		}
		
		return "." + cut.toUpperBound();
	}
	
	@Override
	public DRNode Sibling()
	{
		return (DRNode) super.Sibling();
	}
	
	@Override
	public DRNode LChild()
	{
		return (DRNode) super.LChild();
	}
	
	@Override
	public DRNode RChild()
	{
		return (DRNode) super.RChild();
	}

	@Override
	public DRNode Parent()
	{
		return (DRNode) super.Parent();
	}

	@Override
	public DRNode Root()
	{
		return (DRNode) super.Root();
	}
}