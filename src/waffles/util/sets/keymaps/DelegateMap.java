package waffles.util.sets.keymaps;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import waffles.utils.tools.patterns.semantics.Decorator;

/**
 * A {@code DelegateMap} defines a key map which delegates
 * its functionality to a Java {@code Map}.
 *
 * @author Waffles
 * @since 10 Aug 2023
 * @version 1.0
 *
 *
 * @param <K>  a map key type
 * @param <V>  a map value type
 * @see Decorator
 * @see KeyMap
 */
public interface DelegateMap<K,V> extends KeyMap<K,V>, Decorator
{
	/**
	 * The {@code Pairs} iterator generates {@code KeyPair} objects for a {@code DelegateMap}.
	 *
	 * @author Waffles
	 * @since 10 Aug 2023
	 * @version 1.0
	 *
	 *
	 * @param <K>  a map key type
	 * @param <V>  a map value type
	 * @see Iterator
	 * @see KeyPair
	 */
	public static class Pairs<K,V> implements Iterator<KeyPair<K,V>>
	{
		private Iterator<Entry<K,V>> pairs;
		
		/**
		 * Creates a new {@code Pairs}.
		 * 
		 * @param map  a source map
		 * 
		 * 
		 * @see DelegateMap
		 */
		public Pairs(DelegateMap<K,V> map)
		{
			map.Delegate().entrySet().iterator();
		}
		
		
		@Override
		public KeyPair<K, V> next()
		{
			Entry<K,V> entry = pairs.next();
			V value = entry.getValue();
			K key = entry.getKey();
			
			return new KeyPair<>(key, value);
		}
		
		@Override
		public boolean hasNext()
		{
			return pairs.hasNext();
		}
	}
	
		
	@Override
	public default Iterable<KeyPair<K, V>> Pairs()
	{
		return () -> new Pairs<>(this);
	}
	
	@Override
	public abstract Map<K,V> Delegate();
	
	@Override
	public default int Count()
	{
		return Delegate().size();
	}
	
	
	@Override
	public default V get(K key)
	{
		return Delegate().get(key);
	}
	
	@Override
	public default V put(K key, V val)
	{
		return Delegate().put(key, val);
	}
	
	@Override
	public default V remove(K key)
	{
		return Delegate().remove(key);
	}
		
	@Override
	public default void clear()
	{
		Delegate().clear();
	}
}