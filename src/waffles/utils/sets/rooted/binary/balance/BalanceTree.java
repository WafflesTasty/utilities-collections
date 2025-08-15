package waffles.utils.sets.rooted.binary.balance;

import waffles.utils.sets.rooted.Rooted;
import waffles.utils.sets.rooted.binary.BiNode;

/**
 * A {@code BalanceTree} defines a tree which can balance itself while keeping its nodes in order.
 * An optional {@code Balance} can be provided to perform dynamic rebalancing. After every
 * change to the tree's structure, the respective events can be called to activate
 * the balancing algorithm on the modified node.
 *
 * @author Waffles
 * @since 01 Aug 2020
 * @version 1.1
 * 
 * 
 * @param <N>  a node type
 * @see Balance
 * @see BiNode
 * @see Rooted
 */
public interface BalanceTree<N extends BiNode> extends Balance<N>, Rooted
{
	/**
	 * Returns the balance of the {@code BalanceTree}.
	 * 
	 * @return  a tree balance
	 * 
	 * 
	 * @see Balance
	 */
	public abstract Balance<N> Balance();

	
	@Override
	public default void onInsert(N node)
	{
		if(Balance() != null)
		{
			Balance().onInsert(node);
		}
	}
	
	@Override
	public default void onDelete(N node)
	{
		if(Balance() != null)
		{
			Balance().onDelete(node);
		}
	}
	
	@Override
	public default void onClear()
	{
		if(Balance() != null)
		{
			Balance().onClear();
		}
	}
}