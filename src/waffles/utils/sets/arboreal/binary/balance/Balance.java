package waffles.utils.sets.arboreal.binary.balance;

import waffles.utils.sets.arboreal.binary.BiNodal;
import waffles.utils.sets.arboreal.binary.BiNode;
import waffles.utils.sets.utilities.Iterables;

/**
 * A {@code Balance} manages the balancing algorithm of a {@code BalanceTree}.
 *
 * @author Waffles
 * @since 02 Aug 2020
 * @version 1.1
 * 
 * 
 * @param <N>  a nodal type
 * @see BiNodal
 */
public interface Balance<N extends BiNodal>
{	
	/**
	 * Computes the median of a root {@code BiNode}.
	 * 
	 * @param root  a root node
	 * @return  a median node
	 * 
	 * 
	 * @see BiNode
	 */
	public static BiNode median(BiNode root)
	{
		int med = root.TreeSize() / 2;
		for(BiNode node : Iterables.inorder(root))
		{
			if(med-- == 0)
			{
				return node;
			}
		}
		
		return null;
	}

	/**
	 * Balances the subtree of a {@code BiNodal}.
	 * 
	 * @param base  a base nodal
	 * 
	 * 
	 * @see BiNodal
	 */
	public default void rebalance(BiNodal base)
	{	
		BiNode root = base.Arch();
		if(root.isLeaf())
			return;

		
		int depth = root.Depth();
		BiNode med = median(root);
		while(med.Depth() > depth)
		{
			med.rotate();
		}
		
		
		if(med.LChild() != null)
			rebalance(med.LChild());
		if(med.RChild() != null)
			rebalance(med.RChild());
	}
	
	/**
	 * An event raised when a tree node is inserted.
	 * 
	 * @param node  a target node
	 */
	public default void onInsert(N node)
	{
		// NOT APPLICABLE
	}
	
	/**
	 * An event raised when a tree node is deleted.
	 * 
	 * @param node  a target node
	 */
	public default void onDelete(N node)
	{
		// NOT APPLICABLE
	}

	/**
	 * An event raised when the tree is cleared.
	 */
	public default void onClear()
	{
		// NOT APPLICABLE
	}
}