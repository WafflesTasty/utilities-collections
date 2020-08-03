package zeno.util.coll.trees.binary;

import zeno.util.algebra.intervals.Cut;
import zeno.util.algebra.intervals.Interval;
import zeno.util.calc.constants.Extreme;
import zeno.util.tools.Floats;

/**
 * The {@code DATree} class defines a disjoint angle tree.
 * </br> This tree works the same as a {@link DRTree}, except
 * all of the intervals are normalized within the radian range
 * of {@code -PI} to {@code PI}.
 * 
 * @author Zeno
 * @since Jul 2, 2017
 * @version 1.0
 * 
 * 
 * @see DRTree
 */
public class DATree extends DRTree
{	
	@Override
	public void delete(Interval ival)
	{
		Interval[] ranges = Interval.toRadians(ival);
		for(Interval range : ranges)
		{
			super.delete(range);
		}
	}
	
	@Override
	public void insert(Interval ival)
	{
		Interval[] ranges = Interval.toRadians(ival);
		for(Interval range : ranges)
		{
			super.insert(range);
		}
	}
	
	
	@Override
	public DRNode create(Cut cut, Extreme ex)
	{
		return new DANode(this, cut, ex);
	}
	
	@Override
	public boolean intersects(Interval ival)
	{
		Interval[] ranges = Interval.toRadians(ival);
		for(Interval range : ranges)
		{
			if(super.intersects(range))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean contains(float value)
	{
		return super.contains(Floats.normrad(value));
	}
}