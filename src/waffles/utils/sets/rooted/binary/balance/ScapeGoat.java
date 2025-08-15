package waffles.utils.sets.rooted.binary.balance;

import waffles.utils.sets.rooted.binary.BiNodal;
import waffles.utils.sets.rooted.binary.BiNode;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code ScapeGoat} defines a {@code Balance} using the scapegoat tree algorithm.
 * The tree occasionally gets rebalanced if it becomes too imperfect, guided by an alpha
 * value. This value is clamped between 0.5 and 1.0, where the former rebalances the
 * tree at every opportunity, and the latter never rebalances the tree at all.
 *
 * @author Waffles
 * @since 03 Aug 2020
 * @version 1.1
 * 
 * 
 * @param <N>  a node type
 * @see <a href="https://en.wikipedia.org/wiki/Scapegoat_tree">Wikipedia - Scapegoat Tree</a>
 * @see Balance
 * @see BiNodal
 */
public class ScapeGoat<N extends BiNodal> implements Balance<N>
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
	public void onDelete(N base)
	{
		BiNode node = base.Arch();
		BiNode root = node.Root().Arch();
		
		
		size = root.TreeSize();
		if(size <= alpha * max)
		{
			rebalance(root);
			max = size;
		}
	}

	@Override
	public void onInsert(N base)
	{
		BiNode node = base.Arch();
		BiNode root = node.Root().Arch();
		
		
		size = root.TreeSize();
		max = Integers.max(size, max);
		if(node.Depth() > Floats.log(size, 1 / alpha))
		{
			BiNode curr = node;
			int s1 = node.TreeSize();
			int s2 = -1;
			
			do
			{
				s1 = s1 + s2 + 1;
				
				BiNodal sib = curr.Sibling();
				if(sib != null)
					s2 = sib.Arch().TreeSize();
				else
					s2 = 0;
				
				BiNodal par = curr.Parent();
				if(par == null)
				{
					return;
				}
				curr = par.Arch();
			}
			while(s1 <= alpha * (s1 + s2 + 1)
			   && s2 <= alpha * (s1 + s2 + 1));
			
			rebalance(curr);
		}
	}
	
	@Override
	public void onClear()
	{
		size = max = 0;
	}
}