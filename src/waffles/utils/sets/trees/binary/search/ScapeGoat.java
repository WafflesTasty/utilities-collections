package waffles.utils.sets.trees.binary.search;

import waffles.utils.sets.trees.binary.BSNode;
import waffles.utils.sets.trees.binary.BSTree;
import waffles.utils.sets.utilities.Iterables;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * The {@code ScapeGoat} class defines a tracker for a {@code BSTree}.
 * </br> The tracker occasionally rebalances the tree if it becomes too imperfect.
 * <ul>
 * <li>An alpha value of 0.5 keeps the tree perfect at all times.</li>
 * <li>An alpha value of 1.0 never rebalances the tree.
 * <ul>
 *
 * @author Waffles
 * @since 03 Aug 2020
 * @version 1.0 
 * 
 * 
 * @param <O>  a value type
 * @see <a href="https://en.wikipedia.org/wiki/Scapegoat_tree">Wikipedia - Scapegoat Tree</a>
 * @see BSTree
 */
public class ScapeGoat<O> implements Tracker<O>
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
					s2 = curr.Sibling().TreeSize();
				else
					s2 = 0;
				curr = curr.Parent();
			}
			while(s1 <= alpha * (s1 + s2 + 1)
			   && s2 <= alpha * (s1 + s2 + 1));
			
			rebalance(curr);
		}
	}
	
	@Override
	public void onUpdate(BSTree<O> tree)
	{
		size = max = tree.Count();
		if(tree.Root() != null)
		{
			rebalance(tree.Root());
		}
	}
	
	@Override
	public void onClear(BSTree<O> tree)
	{
		size = max = 0;
	}
	
	
	BSNode<O> median(BSNode<O> base)
	{
		int med = base.TreeSize() / 2;
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