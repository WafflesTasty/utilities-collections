package zeno.util.coll.hashed.dict;

import java.util.Iterator;

import zeno.util.coll.Dictionary;
import zeno.util.coll.hashed.HashedSet;

/**
 * The {@code MultiDictionary} class defines a dictionary with variable key dimensions.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <K>  a dictionary key type
 * @param <V>  a dictionary value type
 * @see Dictionary
 * @see HashedSet
 */
public class MultiDictionary<K, V> implements Dictionary<HashedSet<K>, V>
{	
	private HashedMap<HashedSet<K>, V> map;
	
	/**
	 * Creates a new {@code MultiDictionary}.
	 */
	public MultiDictionary()
	{
		map = new HashedMap<>();
	}

	/**
	 * Removes a keys-value pair from the {@code MultiDictionary}.
	 * 
	 * @param keys  a dictionary keyset to use
	 * @return  a previous dictionary value
	 */
	public V remove(K... keys)
	{
		return map.remove(new HashedSet<>(keys));
	}
	
	/**
	 * Puts a keys-value pair into the {@code MultiDictionary}.
	 * 
	 * @param val  a dictionary value to use
	 * @param keys  a dictionary keyset to use
	 * @return  a previous dictionary value
	 */
	public V put(V val, K... keys)
	{
		return map.put(new HashedSet<>(keys), val);
	}
		
	/**
	 * Returns a value from the {@code MultiDictionary}.
	 * 
	 * @param keys  a dictionary keyset to use
	 * @return  a current dictionary value
	 */
	public V get(K... keys)
	{
		return map.get(new HashedSet<>(keys));
	}
	
	
	@Override
	public Iterator<Pair<HashedSet<K>, V>> iterator()
	{
		return map.iterator();
	}

	@Override
	public V put(HashedSet<K> key, V val)
	{
		return map.put(key, val);
	}

	@Override
	public V remove(HashedSet<K> key)
	{
		return map.remove(key);
	}

	@Override
	public V get(HashedSet<K> key)
	{
		return map.get(key);
	}
}