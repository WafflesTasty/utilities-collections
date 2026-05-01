package waffles.utils.sets.utilities.keymaps.queries;

import java.util.Iterator;

import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.sets.utilities.keymaps.PairQuery;
import waffles.utils.sets.utilities.keymaps.iterators.KeyIterator;
import waffles.utils.sets.utilities.keymaps.iterators.ValueIterator;
import waffles.utils.tools.collections.iterators.CastIterator;

/**
 * A {@code MapQuery} defines a query for a {@code KeyMap}.
 *
 * @author Waffles
 * @since May 1, 2026
 * @version 1.1
 *
 *
 * @param <K>  a key type
 * @param <V>  a value type
 * @see PairQuery
 * @see Pair
 */
@FunctionalInterface
public interface MapQuery<K, V> extends PairQuery<Pair<K, V>>
{	
	@Override
	public abstract Iterator<? extends Pair<K, V>> Pairs();
	
	@Override
	public default Iterator<Pair<K, V>> All()
	{
		return new CastIterator<>(Pairs());
	}
	 	
	
	/**
	 * Iterates over the values of the {@code MapQuery}.
	 * 
	 * @return  a value iterator
	 * 
	 * 
	 * @see Iterator
	 */
	public default Iterator<V> Values()
	{
		return new ValueIterator<>(this);
	}
	
	/**
	 * Iterates over the keys of the {@code MapQuery}.
	 * 
	 * @return  a key iterator
	 * 
	 * 
	 * @see Iterator
	 */
	public default Iterator<K> Keys()
	{
		return new KeyIterator<>(this);
	}
}
