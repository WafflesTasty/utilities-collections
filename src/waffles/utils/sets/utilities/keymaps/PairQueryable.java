package waffles.utils.sets.utilities.keymaps;

import waffles.utils.tools.collections.iterators.CastIterator;
import waffles.utils.tools.patterns.properties.Queryable;

/**
 * A {@code PairQueryable} can iterate over pairs of objects.
 *
 * @author Waffles
 * @since May 1, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Queryable
 */
public interface PairQueryable<O> extends Queryable<O>
{
	/**
	 * Iterates over all pairs in the {@code PairQueryable}.
	 * Preferably, this method iterates over all unique
	 * pairs of objects relevant to the use-case.
	 *
	 * @return  a pair iterable
	 *
	 *
	 * @see Iterable
	 */
	public default <P extends Pair<?, ?>> Iterable<P> Pairs()
	{
		return () -> new CastIterator<>(Query().Pairs());
	}
	
	@Override
	public abstract PairQuery<O> Query();
}
