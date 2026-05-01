package waffles.utils.sets.utilities.keymaps;

import java.util.Iterator;

import waffles.utils.tools.patterns.properties.Queryable.Query;

/**
 * A {@code PairQuery} defines a {@code Query} for pairs.
 *
 * @author Waffles
 * @since May 1, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Query
 */
public interface PairQuery<O> extends Query<O>
{
	/**
	 * Queries all pairs in the {@code PairQuery}.
	 * 
	 * @return  a pair iterator
	 * 
	 * 
	 * @see Iterator
	 * @see Pair
	 */
	public abstract Iterator<? extends Pair<?, ?>> Pairs();
}
