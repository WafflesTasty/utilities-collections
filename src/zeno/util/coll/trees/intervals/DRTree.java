package zeno.util.coll.trees.intervals;

import zeno.util.algebra.constants.Extreme;
import zeno.util.algebra.intervals.Cut;
import zeno.util.algebra.intervals.Interval;
import zeno.util.coll.trees.BiNode;
import zeno.util.coll.trees.BiTree;

/**
 * The {@code DRTree} class defines a disjoint range tree.
 * <br> This tree stores disjoint intervals in a binary tree.
 * <br> Each node represents either an interval minimum or
 * maximum through the {@code Cut} class.
 * 
 * @since Jun 30, 2017
 * @author Zeno
 * 
 * @see BiTree
 * @see Cut
 */
public class DRTree extends BiTree<Cut>
{	
	/**
	 * Deletes an interval from the {@code DRTree}.
	 * 
	 * @param ival  an interval to delete
	 * @see Interval
	 */
	public void delete(Interval ival)
	{
		// If the interval is invalid, or the tree is empty...
		if(ival == null || isEmpty())
		{
			// Do nothing.
			return;
		}
		
		
		// Create nodes for the interval.
		DRNode min = create(ival.min(), Extreme.MAX);
		DRNode max = create(ival.max(), Extreme.MIN);
	
		// Find the surrounding nodes in the tree.
		DRNode prev = lowerbound(ival.min());
		DRNode next = upperbound(ival.max());

		// If the interval has no lower bound...
		if(prev == null)
		{
			// And the interval has no upper bound...
			if(next == null)
			{
				// Clear the root.
				clear();
				return;
			}
			
			// Delete below the upper bound.
			deleteBelow(next);

			// If the upper bound is a maximum...
			if(next.Extreme() == Extreme.MAX)
			{				
				// Add the maximum node before the upper bound.
				next.setLChild(max);
				Tracker().update();
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
				Tracker().update();
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
					Tracker().update();
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(max);
					max.setLChild(min);
					Tracker().update();
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
					Tracker().update();
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(min);
					Tracker().update();
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
					Tracker().update();
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(max);
					Tracker().update();
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
		if(isEmpty())
		{
			// Add the interval at the root.
			min.setRChild(max);
			setRoot(min);
			return;
		}

		
		// Find the surrounding nodes in the tree.
		DRNode prev = lowerbound(ival.min());
		DRNode next = upperbound(ival.max());

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
				Tracker().update();
				return;
			}
			
			// If the upper bound is a minimum...
			if(next.Extreme() == Extreme.MIN)
			{
				// Add the interval below the upper bound.
				next.setLChild(min);
				min.setRChild(max);
				Tracker().update();
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
				Tracker().update();
				return;
			}
			
			// If the upper bound is a minimum...
			if(prev.Extreme() == Extreme.MAX)
			{
				// Add the interval above the lower bound.
				prev.setRChild(max);
				max.setLChild(min);
				Tracker().update();
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
					Tracker().update();
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(max);
					Tracker().update();
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
					Tracker().update();
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(min);
					Tracker().update();
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
					Tracker().update();
					return;
				}
				
				if(next.LChild() == null)
				{
					next.setLChild(max);
					max.setLChild(min);
					Tracker().update();
					return;
				}
			}
			
			return;
		}
	}
	
	
	/**
	 * Creates a new node for the {@code DRTree}.
	 * 
	 * @param cut  an interval cut
	 * @param ex  an interval extreme
	 * @return  a new node
	 * @see Extreme
	 * @see DRNode
	 */
	public DRNode create(Cut cut, Extreme ex)
	{
		return new DRNode(cut, ex, this);
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
		if(ival == null || isEmpty())
		{
			// Return false.
			return false;
		}
		
		
		// Find the surrounding nodes in the tree.
		DRNode prev = lowerbound(ival.min());
		DRNode next = upperbound(ival.max());

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
		if(isEmpty())
		{
			// Return false.
			return false;
		}
		
		
		// Otherwise, find the nearest node.
		DRNode node = search(value);
		// Retrieve the node's cut.
		Cut cut = node.Object();

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
	public DRNode lowerbound(Cut cut)
	{
		DRNode lower = search(cut);
		if(compare(cut, lower.Object()) < 0)
		{
			return lower.prev();
		}
		
		return lower;
	}
	
	/**
	 * Returns the upper bound of a cut in the {@code DRTree}.
	 * 
	 * @param cut  a cut to search
	 * @return  an upper bound
	 * @see DRNode
	 */
	public DRNode upperbound(Cut cut)
	{
		DRNode upper = search(cut);
		if(compare(cut, upper.Object()) > 0)
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
			int comp = node.Object().compareTo(val);
			
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
	public String toString()
	{
		String text = "";
		for(BiNode<Cut> node : inorder())
		{
			text += node;
		}
		
		return text;
	}
	
	@Override
	public DRNode search(Cut object)
	{
		return (DRNode) super.search(object);
	}
		
	@Override
	public DRTree instance()
	{
		return new DRTree();
	}
		
	@Override
	public DRNode Root()
	{
		return (DRNode) super.Root();
	}
	
	
	
	void deleteBetween(DRNode min, DRNode max)
	{
		mergeBetween(min, max);
		
		int dmin = min.NodeDepth();
		int dmax = max.NodeDepth();

		if(dmin < dmax)
			max.setLChild(null);
		else
			min.setRChild(null);
		
		Tracker().update();
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
		
		
		int dmin = min.NodeDepth();
		int dmax = max.NodeDepth();
		
		if(dmin < dmax)
		{
			if(max.isRChild())
			{
				pmax.replace(max);
				mergeBetween(min, max);
				return;
			}
			
			mergeBetween(min, pmax);
			return;
		}
		
		if(min.isLChild())
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
		Tracker().update();
	}
	
	void deleteBelow(DRNode max)
	{
		max.setLChild(null);
		mergeBelow(max);
		Tracker().update();
	}
	
	
	void mergeAbove(DRNode min)
	{
		if(min.isRChild())
		{
			mergeAbove(min.Parent());
		}
		
		if(min.isLChild())
		{
			min.Parent().replace(min);
		}
	}
	
	void mergeBelow(DRNode max)
	{
		if(max.isLChild())
		{
			mergeBelow(max.Parent());
		}
		
		if(max.isRChild())
		{
			max.Parent().replace(max);
		}
	}
}