package waffles.utils.sets.utilities.keymaps.iterators;

import java.util.Iterator;

import waffles.utils.sets.countable.keymaps.KeyMap;
import waffles.utils.sets.utilities.keymaps.Pair;

/**
 * A {@code KeyIterator} iterates the keys in a {@code KeyMap}.
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
public class KeyIterator<K, V> implements Iterator<K>
{
	private Iterator<Pair<K, V>> source;
	
	/**
	 * Creates a new {@code KeyIterator}.
	 * 
	 * @param map  a key map
	 * 
	 * 
	 * @see KeyMap
	 */
	public KeyIterator(KeyMap<K, V> map)
	{
		source = map.Pairs().iterator();
	}
	
	
	@Override
	public boolean hasNext()
	{
		return source.hasNext();
	}

	@Override
	public K next()
	{
		return source.next().Key();
	}
}