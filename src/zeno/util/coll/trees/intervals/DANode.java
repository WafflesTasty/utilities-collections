package zeno.util.coll.trees.intervals;

import zeno.util.algebra.constants.Extreme;
import zeno.util.algebra.intervals.Cut;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code DANode} class defines a node in a {@link DATree}.
 * 
 * @since Jul 1, 2017
 * @author Zeno
 * 
 * @see DRNode
 */
public class DANode extends DRNode
{
	/**
	 * Creates a new {@code DANode}.
	 * 
	 * @param cut  an interval cut
	 * @param ex  an interval extreme
	 * @param tree  a source tree
	 * @see Extreme
	 * @see DATree
	 * @see Cut
	 */
	public DANode(Cut cut, Extreme ex, DATree tree)
	{
		super(cut, ex, tree);
	}

	
	@Override
	public String toString()
	{
		Cut cut = Object();
		
		float value = cut.value() / Floats.PI;
		if(Extreme() == Extreme.MIN)
		{
			if(cut.isBelow(value))
				return "[" + value + "Pi.";
			return "(" + value + "Pi.";
		}
		
		if(cut.isAbove(cut.value()))
			return "." + value + "Pi]";
		return "." + value + "Pi)";
	}

	@Override
	public DANode instance()
	{
		return new DANode();
	}
	
	@Override
	public DANode Parent()
	{
		return (DANode) super.Parent();
	}
	
	@Override
	public DANode copy()
	{
		return (DANode) super.copy();
	}

	
	DANode()
	{
		super(null, null, null);
	}
}