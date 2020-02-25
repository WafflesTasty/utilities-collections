package zeno.util.coll.hashed.dict;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import zeno.util.coll.Dictionary;

/**
 * The {@code HashedMap} class defines a basic dictionary backed by a hash.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <K>  a dictionary key type
 * @param <V>  a dictionary value type
 * @see Dictionary
 */
public class HashedMap<K, V> implements Dictionary<K, V>
{
	class MapIterator implements Iterator<Pair<K, V>>
	{
		private Iterator<Entry<K, V>> set;
		
		public MapIterator()
		{
			set = data.entrySet().iterator();
		}
		
				
		@Override
		public boolean hasNext()
		{
			return set.hasNext();
		}

		@Override
		public Pair<K, V> next()
		{
			Entry<K, V> e = set.next();
			
			V val = e.getValue();
			K key = e.getKey();
			
			return new Pair<>(key, val);
		}
		
		@Override
		public void remove()
		{
			set.remove();
		}
	}
	
	
	private Map<K, V> data;
	
	/**
	 * Creates a new {@code HashedMap}.
	 */
	public HashedMap()
	{
		data = new HashMap<>();
	}
	
	
	@Override
	public Iterator<Pair<K, V>> iterator()
	{
		return new MapIterator();
	}

	
	@Override
	public V put(K key, V val)
	{
		return data.put(key, val);
	}

	@Override
	public V remove(K key)
	{
		return data.remove(key);
	}

	@Override
	public V get(K key)
	{
		return data.get(key);
	}
}