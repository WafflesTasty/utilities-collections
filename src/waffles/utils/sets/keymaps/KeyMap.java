package waffles.utils.sets.keymaps;

import java.util.Iterator;

import waffles.utils.sets.CountableSet;

/**
 * The {@code KeyMap} interface defines a mapping of key-value pairs.
 * </br> Each value is mapped to exactly one key. 
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @param <K>  a key type
 * @param <V>  a value type
 * @see CountableSet
 */
public interface KeyMap<K, V> extends CountableSet
{	
	/**
	 * The {@code Keys} iterator traverses all keys in a {@code KeyMap}.
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
	public class Keys<K, V> implements Iterator<K>
	{
		private Iterator<Pair<K, V>> source;
		
		/**
		 * Creates a new {@code Keys}.
		 * 
		 * @param map  a source map
		 * 
		 * 
		 * @see KeyMap
		 */
		public Keys(KeyMap<K, V> map)
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
	
	/**
	 * The {@code Values} iterator traverses all values in a {@code KeyMap}.
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
	public class Values<K, V> implements Iterator<V>
	{
		private Iterator<Pair<K, V>> source;
		
		/**
		 * Creates a new {@code Values}.
		 * 
		 * @param map  a source map
		 * 
		 * 
		 * @see KeyMap
		 */
		public Values(KeyMap<K, V> map)
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
	
	
	/**
	 * Returns the key-value pairs of the {@code KeyMap}.
	 * This method is made generic to allow iteration of subtypes.
	 * Make sure to override {@link #createPair(Object, Object) createPair}
	 * to generate the correct subtype you need to iterate.
	 * 
	 * @return  a pair iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Pair
	 */
	public abstract <P extends Pair<K, V>> Iterable<P> Pairs();
	
	/**
	 * Creates a new key-value pair for the {@code KeyMap}.
	 * 
	 * @param key  a key
	 * @param val  a value
	 * @return  a key-value pair
	 * 
	 * 
	 * @see Pair
	 */
	public default Pair<K, V> createPair(K key, V val)
	{
		return new Pair.Base<>(key, val);
	}

	
	/**
	 * Returns the values of the {@code KeyMap}.
	 * 
	 * @return  a value iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<V> Values()
	{
		return () -> new Values<>(this);
	}

	/**
	 * Returns the keys of the {@code KeyMap}.
	 * 
	 * @return  a key iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<K> Keys()
	{
		return () -> new Keys<>(this);
	}
	
	
	/**
	 * Puts a key-value pair into the {@code KeyMap}.
	 * 
	 * @param key  a key to use
	 * @param val  a value to use
	 * @return  a previous value
	 */
	public abstract V put(K key, V val);

	/**
	 * Removes a key-value pair from the {@code KeyMap}.
	 * 
	 * @param key  a key to use
	 * @return  a previous value
	 */
	public abstract V remove(K key);
	
	/**
	 * Returns a value from the {@code KeyMap}.
	 * 
	 * @param key  a key to use
	 * @return  a current value
	 */
	public abstract V get(K key);
}