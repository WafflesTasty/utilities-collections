package waffles.utils.sets.arboreal.binary.balance;

import waffles.utils.sets.arboreal.binary.BiNode;

/**
 * A {@code Balanced} defines its own {@code Balance} events.
 *
 * @author Waffles
 * @since 01 Aug 2020
 * @version 1.1
 * 
 * 
 * @param <N>  a nodal type
 * @see Balance
 * @see BiNode
 */
public interface Balanced<N extends BiNode> extends Balance<N>
{
	/**
	 * Returns the balance of the {@code Balanced}.
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