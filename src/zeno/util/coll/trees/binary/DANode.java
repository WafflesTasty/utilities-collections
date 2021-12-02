package zeno.util.coll.trees.binary;

import zeno.util.algebra.intervals.Cut;
import zeno.util.calc.constants.Extreme;
import zeno.util.tools.Floats;

/**
 * The {@code DANode} class defines a node in a disjoint angle tree.
 * 
 * @author Zeno
 * @since Jul 1, 2017
 * @version 1.0
 * 
 * 
 * @see DRNode
 */
public class DANode extends DRNode
{
	/**
	 * Creates a new {@code DANode}.
	 * 
	 * @param tree  a parent tree
	 * @param cut  an real line cut
	 * @param ex   an interval extreme
	 * 
	 * 
	 * @see Extreme
	 * @see DATree
	 * @see Cut
	 */
	public DANode(DATree tree, Cut cut, Extreme ex)
	{
		super(tree, cut, ex);
	}

	
	@Override
	public String toString()
	{
		Cut cut = Value();
		
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
	public DANode Parent()
	{
		return (DANode) super.Parent();
	}
}