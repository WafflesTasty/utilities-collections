package waffles.utils.sets.utilities.keymaps.iterators;

import java.util.Iterator;

import waffles.utils.sets.countable.keymaps.KeyMap;
import waffles.utils.sets.utilities.keymaps.Pair;

/**
 * A {@code ValueIterator} iterates the values in a {@code KeyMap}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <K>  a key type
 * @param <V>  a value type
 * @see Iterator
 */
public class ValueIterator<K, V> implements Iterator<V>
{
	private Iterator<Pair<K, V>> source;
	
	/**
	 * Creates a new {@code ValueIterator}.
	 * 
	 * @param map  a source map
	 * 
	 * 
	 * @see KeyMap
	 */
	public ValueIterator(KeyMap<K, V> map)
	{
		source = map.Pairs().iterator();
	}
	
	
	@Override
	public boolean hasNext()
	{
		return source.hasNext();
	}

	@Override
	public V next()
	{
		return source.next().Value();
	}
}