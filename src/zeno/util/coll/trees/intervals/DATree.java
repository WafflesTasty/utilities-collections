package zeno.util.coll.trees.intervals;

import zeno.util.algebra.constants.Extreme;
import zeno.util.algebra.intervals.Cut;
import zeno.util.algebra.intervals.Interval;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code DATree} class defines a disjoint angle tree.
 * <br> This tree works the same as a {@link DRTree}, except
 * all of the intervals are normalized within the radian range
 * of {@code -PI} to {@code PI}.
 * 
 * @since Jul 2, 2017
 * @author Zeno
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
		return new DANode(cut, ex, this);
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
	
			
	@Override
	public DATree instance()
	{
		return new DATree();
	}
	
	@Override
	public DATree copy()
	{
		return (DATree) super.copy();
	}
}