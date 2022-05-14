package zeno.util.coll.trees.binary;

import java.util.Iterator;

import zeno.util.algebra.intervals.Cut;
import zeno.util.algebra.intervals.Interval;
import zeno.util.calc.constants.Extreme;

/**
 * The {@code DRTree} class defines a disjoint range tree.
 * </br> This tree stores disjoint intervals in a binary tree.
 * </br> Each node represents either an interval minimum or
 * maximum through the {@code Cut} class.
 * 
 * @author Zeno
 * @since Jun 30, 2017
 * @version 1.0
 * 
 * 
 * @see BSTree
 * @see Cut
 */
public class DRTree extends BSTree<Cut>
{
	/**
	 * The {@code Intervals} class iterates over intervals in a {@code DRTree}.
	 *
	 * @author Zeno
	 * @since 17 Aug 2020
	 * @version 1.0
	 * 
	 * 
	 * @see Interval
	 * @see Iterator
	 */
	public class Intervals implements Iterator<Interval>
	{
		private Iterator<BSNode<Cut>> nodes = inorder().iterator();
		
		@Override
		public boolean hasNext()
		{
			return nodes.hasNext();
		}

		@Override
		public Interval next()
		{
			Cut min = nodes.next().Value();
			Cut max = nodes.next().Value();
			return new Interval(min, max);
		}
	}
	
	
	/**
	 * Deletes an interval from the {@code DRTree}.
	 * 
	 * @param ival  an interval to delete
	 * 
	 * 
	 * @see Interval
	 */
	public void delete(Interval ival)
	{
		// If the interval is invalid, or the tree is empty...
		if(ival == null || Root() == null)
		{
			// Do nothing.
			return;
		}
		
		
		// Create nodes for the interval.
		DRNode min = create(ival.min(), Extreme.MAX);
		DRNode max = create(ival.max(), Extreme.MIN);
	
		// Find the surrounding nodes in the tree.
		DRNode prev = strictLowerBound(ival.min());
		DRNode next = strictUpperBound(ival.max());

		// If the interval has no lower bound...
		if(prev == null)
		{
			// And the interval has no upper bound...
			if(next == null)
			{
				// Clear the root.
				setRoot(null);
				return;
			}
			
			// Delete below the upper bound.
			deleteBelow(next);

			// If the upper bound is a maximum...
			if(next.Extreme() == Extreme.MAX)
			{				
				// Add the maximum node before the upper bound.
				next.setLChild(max);
				onInsert(max);
			}

			return;
		}
		
		
		// If the interval has no upper bound...
		if(next == null)
		{
			// Delete above the lower bound.
			deleteAbove(prev);

			// If the lower bound is a minimum...
			if(prev.Extreme() == Extreme.MIN)
			{
				// Add the minimum node after the lower bound.
				prev.setRChild(min);
				onInsert(min);
			}
			
			return;
		}
		
		
		// Otherwise, delete between the bounds.
		deleteBetween(prev, next);

		// If the lower bound is a minimum...
		if(prev.Extreme() == Extreme.MIN)
		{
			// And the upper bound is a maximum...
			if(next.Extreme() == Extreme.MAX)
			{
				// Add the interval nodes between the bounds.
				if(prev.RChild() == null)
				{
					prev.setRChild(min);
					min.setRChild(max);
					onUpdate(this);
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(max);
					max.setLChild(min);
					onUpdate(this);
					return;
				}
				
				return;
			}
			
			// And the upper bound is a minimum...
			if(next.Extreme() == Extreme.MIN)
			{
				// Add the minimum node between the bounds.
				if(prev.RChild() == null)
				{
					prev.setRChild(min);
					onInsert(min);
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(min);
					onInsert(min);
					return;
				}
			}
			
			return;
		}

		// If the lower bound is a maximum...
		if(prev.Extreme() == Extreme.MAX)
		{
			// And the upper bound is a maximum...
			if(next.Extreme() == Extreme.MAX)
			{		
				// Add the maximum node between the bounds.
				if(prev.RChild() == null)
				{
					prev.setRChild(max);
					onInsert(max);
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(max);
					onInsert(max);
					return;
				}
			}
			
			return;
		}
	}
	
	/**
	 * Inserts an interval into the {@code DRTree}.
	 * 
	 * @param ival  an interval to insert
	 * @see Interval
	 */
	public void insert(Interval ival)
	{
		// If the interval is invalid, or empty...
		if(ival == null || ival.isEmpty())
		{
			// Do nothing.
			return;
		}
		
		
		// Create nodes for the interval.
		DRNode min = create(ival.min(), Extreme.MIN);
		DRNode max = create(ival.max(), Extreme.MAX);

		// If the tree is empty...
		if(Root() == null)
		{
			// Add the interval at the root.
			min.setRChild(max);
			setRoot(min);
			return;
		}

		
		// Find the surrounding nodes in the tree.
		DRNode prev = strictLowerBound(ival.min());
		DRNode next = strictUpperBound(ival.max());

		// If the interval has no lower bound...
		if(prev == null)
		{
			// And the interval has no upper bound...
			if(next == null)
			{
				// Add the interval at the root.
				min.setRChild(max);				
				setRoot(min);
				return;
			}

			// Delete below the upper bound.
			deleteBelow(next);
		
			// If the upper bound is a maximum...
			if(next.Extreme() == Extreme.MAX)
			{
				// Add the minimum node below the upper bound.
				next.setLChild(min);
				onInsert(min);
				return;
			}
			
			// If the upper bound is a minimum...
			if(next.Extreme() == Extreme.MIN)
			{
				// Add the interval below the upper bound.
				next.setLChild(min);
				min.setRChild(max);
				onUpdate(this);
				return;
			}
			
			return;
		}
		
		
		// If the interval has no upper bound...
		if(next == null)
		{
			// Delete above the lower bound.
			deleteAbove(prev);

			// If the lower bound is a minimum...
			if(prev.Extreme() == Extreme.MIN)
			{
				// Add the maximum node above the lower bound.
				prev.setRChild(max);
				onInsert(max);
				return;
			}
			
			// If the upper bound is a minimum...
			if(prev.Extreme() == Extreme.MAX)
			{
				// Add the interval above the lower bound.
				prev.setRChild(max);
				max.setLChild(min);
				onUpdate(this);
				return;
			}
			
			return;
		}
		
		
		// Otherwise, delete between the bounds.
		deleteBetween(prev, next);
		
		// If the lower bound is a minimum...
		if(prev.Extreme() == Extreme.MIN)
		{
			// And the upper bound is a minimum...
			if(next.Extreme() == Extreme.MIN)
			{
				// Add the maximum node between the bounds.
				if(prev.RChild() == null)
				{
					prev.setRChild(max);
					onInsert(max);
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(max);
					onInsert(max);
					return;
				}
			}
			
			return;
		}
		
		// If the lower bound is a maximum...
		if(prev.Extreme() == Extreme.MAX)
		{
			// And the upper bound is a maximum...
			if(next.Extreme() == Extreme.MAX)
			{		
				// Add the minimum node between the bounds.
				if(prev.RChild() == null)
				{
					prev.setRChild(min);
					onInsert(min);
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(min);
					onInsert(min);
					return;
				}
			}
			
			// And the upper bound is a minimum...
			if(next.Extreme() == Extreme.MIN)
			{
				// Add the interval nodes between the bounds.
				if(prev.RChild() == null)
				{
					prev.setRChild(min);
					min.setRChild(max);
					onUpdate(this);
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(max);
					max.setLChild(min);
					onUpdate(this);
					return;
				}
			}
			
			return;
		}
	}
	
	
	/**
	 * Returns the intervals in the {@code DRTree}.
	 * 
	 * @return  an interval iterable
	 * 
	 * 
	 * @see Interval
	 * @see Iterable
	 */
	public Iterable<Interval> Intervals()
	{
		return () -> new Intervals();
	}
		
	/**
	 * Checks if the {@code DRTree} intersects an interval.
	 * 
	 * @param ival  an interval to check
	 * @return  {@code true} of the interval intersects
	 */
	public boolean intersects(Interval ival)
	{
		// If the interval is invalid, or the tree is empty...
		if(ival == null || Root() == null)
		{
			// Repetition false.
			return false;
		}
		
		// Find the surrounding nodes in the tree.
		DRNode prev = lowerBound(ival.min());
		DRNode next = upperBound(ival.max());
		
		// If the interval has no lower bound...
		if(prev == null)
		{
			// And the interval has no upper bound...
			if(next == null)
			{
				// There is an intersection.
				return true;
			}
			
			// Otherwise, check if the upper bound is the lowest node.
			return next.prev() != null;
		}

		// If the interval has no upper bound...
		if(next == null)
		{
			// Check if the lower bound is the highest node.
			return prev.next() != null;
		}

		// If there are nodes between the bounds...
		if(prev.next() != next)
		{
			return true;
		}
		
		// Otherwise, check the bound's contents.
		return prev.Extreme() == Extreme.MIN
			|| next.Extreme() == Extreme.MAX;
	}
	
	/**
	 * Checks if the {@code DRTree} contains a value.
	 * 
	 * @param value  a value to check
	 * @return  {@code true} if the value is contained
	 */
	public boolean contains(float value)
	{
		// If the tree is empty...
		if(Root() == null)
		{
			// Repetition false.
			return false;
		}
		
		
		// Otherwise, find the nearest node.
		DRNode node = search(value);
		// Retrieve the node's cut.
		Cut cut = node.Value();

		// If the node is a minimum...
		if(node.Extreme() == Extreme.MIN)
		{
			// The value should be higher.
			return cut.isBelow(value);
		}
		
		// Otherwise, the value should be lower.
		return cut.isAbove(value);
	}
	
		
	/**
	 * Returns the lower bound of a cut in the {@code DRTree}.
	 * 
	 * @param cut  a cut to search
	 * @return  a lower bound
	 * @see DRNode
	 */
	public DRNode lowerBound(Cut cut)
	{
		DRNode lower = search(cut);
		if(compare(cut, lower.Value()) < 0)
		{
			return lower.prev();
		}
		
		return lower;
	}
	
	/**
	 * Returns the strict lower bound of a cut in the {@code DRTree}.
	 * 
	 * @param cut  a cut to search
	 * @return  a lower bound
	 * @see DRNode
	 */
	public DRNode strictLowerBound(Cut cut)
	{
		DRNode lower = search(cut);
		if(compare(cut, lower.Value()) <= 0)
		{
			return lower.prev();
		}
		
		return lower;
	}
	
	/**
	 * Returns the strict upper bound of a cut in the {@code DRTree}.
	 * 
	 * @param cut  a cut to search
	 * @return  an upper bound
	 * @see DRNode
	 */
	public DRNode strictUpperBound(Cut cut)
	{
		DRNode upper = search(cut);
		if(compare(cut, upper.Value()) >= 0)
		{
			return upper.next();
		}
		
		return upper;
	}
	
	/**
	 * Returns the upper bound of a cut in the {@code DRTree}.
	 * 
	 * @param cut  a cut to search
	 * @return  an upper bound
	 * @see DRNode
	 */
	public DRNode upperBound(Cut cut)
	{
		DRNode upper = search(cut);
		if(compare(cut, upper.Value()) > 0)
		{
			return upper.next();
		}
		
		return upper;
	}
		
	/**
	 * Searches for the {@code DRNode} closest to a value.
	 * 
	 * @param val  a value to search
	 * @return  the closest node
	 * @see DRNode
	 */
	public DRNode search(float val)
	{
		DRNode node = Root();
		
		// The tree is empty.
		if(node == null)
		{
			return null;
		}
		
		// Start checking from root...
		while(true)
		{
			int comp = node.Value().compareTo(val);
			
			// The value has been found.
			if(comp == 0)
			{
				return node;
			}
			
			// The value is lower than the node...
			if(comp > 0)
			{
				// ...but it's the closest one found.
				if(node.LChild() == null)
				{
					return node;
				}
				
				// ...so continue with its left child.
				node = node.LChild();
				continue;
			}
			
			// The value is higher than the node...
			if(comp < 0)
			{
				// ...but it's the closest one found.
				if(node.RChild() == null)
				{
					return node;
				}
				
				// ...so continue with its right child.
				node = node.RChild();
				continue;
			}
		}
	}

	
	@Override
	protected DRNode search(Cut cut)
	{
		return (DRNode) super.search(cut);
	}
	
	@Override
	public DRNode create(Object... vals)
	{
		Cut cut = (Cut) vals[0];
		Extreme ex = (Extreme) vals[1];
		return new DRNode(this, cut, ex);
	}
	
	@Override
	public String toString()
	{
		if(Root() == null)
		{
			return "[0.0, 0.0)";
		}
		
		String text = "";
		for(BSNode<Cut> node : inorder())
		{
			text += node;
		}
		
		return text;
	}
		
	@Override
	public DRNode Root()
	{
		return (DRNode) super.Root();
	}
	
	
	
	void deleteBetween(DRNode min, DRNode max)
	{
		mergeBetween(min, max);
		
		int dmin = min.Depth();
		int dmax = max.Depth();

		if(dmin < dmax)
			max.setLChild(null);
		else
			min.setRChild(null);
		
		onUpdate(this);
	}

	void mergeBetween(DRNode min, DRNode max)
	{
		if(min == max)
		{
			return;
		}
		
		
		DRNode pmin = min.Parent();
		DRNode pmax = max.Parent();
				
		if(min == pmax || pmin == max)
		{
			return;
		}
		
		if(pmin == pmax)
		{
			pmin.delete();
			return;
		}
		
		
		int dmin = min.Depth();
		int dmax = max.Depth();
		
		if(dmin < dmax)
		{
			if(max.TreeIndex() == 1)
			{
				pmax.replace(max);
				mergeBetween(min, max);
				return;
			}
			
			mergeBetween(min, pmax);
			return;
		}
		
		if(min.TreeIndex() == 0)
		{
			pmin.replace(min);
			mergeBetween(min, max);
			return;
		}
		
		mergeBetween(pmin, max);
		return;
	}
	
	
	void deleteAbove(DRNode min)
	{
		min.setRChild(null);
		mergeAbove(min);
		onUpdate(this);
	}
	
	void deleteBelow(DRNode max)
	{
		max.setLChild(null);
		mergeBelow(max);
		onUpdate(this);
	}
	
	
	void mergeAbove(DRNode min)
	{
		if(min.isRoot()) return;
		
		if(min.TreeIndex() == 1)
		{
			mergeAbove(min.Parent());
		}
		
		if(min.TreeIndex() == 0)
		{
			min.Parent().replace(min);
			mergeAbove(min);
		}
	}
	
	void mergeBelow(DRNode max)
	{
		if(max.isRoot()) return;
		
		if(max.TreeIndex() == 0)
		{
			mergeBelow(max.Parent());
		}
		
		if(max.TreeIndex() == 1)
		{
			max.Parent().replace(max);
			mergeBelow(max);
		}
	}
}