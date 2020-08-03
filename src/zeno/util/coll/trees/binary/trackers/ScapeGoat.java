package zeno.util.coll.trees.binary.trackers;

import zeno.util.coll.trees.binary.BSNode;
import zeno.util.coll.trees.binary.BSTree;
import zeno.util.coll.utilities.Iterables;
import zeno.util.tools.Floats;
import zeno.util.tools.Integers;

/**
 * The {@code ScapeGoat} class defines a tracker for a {@code BSTree}.
 * </br> The tracker occasionally rebalances the tree if it becomes too imperfect.
 * <ul>
 * <li>An alpha value of 0.5 keeps the tree perfect at all times.</li>
 * <li>An alpha value of 1.0 never rebalances the tree.
 * <ul>
 *
 * @author Zeno
 * @since 03 Aug 2020
 * @version 1.0 
 * 
 * 
 * @param <O>  a value type
 * @see <a href="https://en.wikipedia.org/wiki/Scapegoat_tree">Wikipedia - Scapegoat Tree</a>
 * @see BSTree
 */
public class ScapeGoat<O> implements BSTree.Tracker<O>
{
	private float alpha;
	private int size, max;
	
	/**
	 * Creates a new {@code ScapeGoat}.
	 * 
	 * @param a  an alpha value
	 */
	public ScapeGoat(float a)
	{
		alpha = Floats.clamp(a, 0.5f, 1.0f);
	}
	
	
	@Override
	public void onDelete(BSNode<O> node)
	{
		size--;
		if(size <= alpha * max)
		{
			rebalance(node.Root());
			max = size;
		}
	}

	@Override
	public void onInsert(BSNode<O> node)
	{
		size++; max = Integers.max(size, max);
		if(node.Depth() > Floats.log(size, 1 / alpha))
		{
			BSNode<O> curr = node;
			int s1 = 0, s2 = 0;
			
			do
			{
				s1 = s1 + s2 + 1;
				if(curr.Sibling() != null)
					s2 = curr.Sibling().Size();
				else
					s2 = 0;
				curr = curr.Parent();
			}
			while(s1 <= alpha * (s1 + s2 + 1)
			   && s2 <= alpha * (s1 + s2 + 1));
			
			rebalance(curr);
		}
	}
	
	
	BSNode<O> median(BSNode<O> base)
	{
		int med = base.Size() / 2;
		for(BSNode<O> node : Iterables.inorder(base))
		{
			if(med-- == 0)
			{
				return node;
			}
		}
		
		return null;
	}
	
	void rebalance(BSNode<O> base)
	{	
		if(base.isLeaf()) return;

		
		int depth = base.Depth();
		BSNode<O> med = median(base);
		while(med.Depth() > depth)
		{
			med.rotate();
		}
		
		
		if(med.LChild() != null)
			rebalance(med.LChild());
		if(med.RChild() != null)
			rebalance(med.RChild());
	}
}