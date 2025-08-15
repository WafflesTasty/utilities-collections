package waffles.utils.sets.countable.keymaps.wrapper;

import java.util.HashMap;

import waffles.utils.sets.countable.keymaps.KeyMap;
import waffles.utils.sets.utilities.keymaps.Pair;

/**
 * A {@code JavaMap} defines a {@code KeyMap} backed by a {@code java.util.HashMap}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @param <K>  a key type
 * @param <V>  a value type
 * @see KeyMap
 */
public class JavaMap<K, V> implements KeyMap.Java<K, V>
{
	private HashMap<K, V> data;

	/**
	 * Creates a new {@code JavaMap}.
	 */
	public JavaMap()
	{
		data = new HashMap<>();
	}

		
	@Override
	public HashMap<K, V> Delegate()
	{
		return data;
	}
	
	@Override
	public Pair<K, V> create(K k, V v)
	{
		return new Pair.Base<>(k, v);
	}
}