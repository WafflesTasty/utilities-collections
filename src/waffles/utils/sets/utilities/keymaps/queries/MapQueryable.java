package waffles.utils.sets.utilities.keymaps.queries;

import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.sets.utilities.keymaps.PairQueryable;

/**
 * A {@code MapQueryable} can iterate over map pairs.
 *
 * @author Waffles
 * @since May 1, 2026
 * @version 1.1
 *
 *
 * @param <K>  a key type
 * @param <V>  a value type
 * @see PairQueryable
 * @see Pair
 */
public interface MapQueryable<K, V> extends PairQueryable<Pair<K, V>>
{
	/**
	 * Iterates the keys of the {@code MapQueryable}.
	 * 
	 * @return  a key iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<K> Keys()
	{
		return () -> Query().Keys();
	}
	
	/**
	 * Iterates the values of the {@code MapQueryable}.
	 * 
	 * @return  a value iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<V> Values()
	{
		return () -> Query().Values();
	}
	
	
	@Override
	public abstract MapQuery<K, V> Query();
}