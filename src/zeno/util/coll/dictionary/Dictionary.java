package zeno.util.coll.dictionary;

import zeno.util.coll.utilities.iterators.KeyIterator;
import zeno.util.coll.utilities.iterators.ValueIterator;

/**
 * The {@code Dictionary} interface defines a set organized in key-value pairs.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <K>  a dictionary key type
 * @param <V>  a dictionary value type
 * @see Iterable
 */
public interface Dictionary<K, V> extends Iterable<Dictionary.Pair<K, V>>
{
	/**
	 * The {@code Pair} class defines an entry in a {@code Dictionary}.
	 *
	 * @author Zeno
	 * @since Feb 03, 2020
	 * @version 1.0
	 * 
	 * 
	 * @param <K>  a dictionary key type
	 * @param <V>  a dictionary value type
	 */
	public static class Pair<K, V>
	{
		private K key;
		private V value;
		
		/**
		 * Creates a new {@code Dictionary Pair}.
		 * 
		 * @param k  a pair key
		 * @param v  a pair value
		 */
		public Pair(K k, V v)
		{
			value = v;
			key = k;
		}
		
		/**
		 * Returns the value of the {@code Pair}.
		 * 
		 * @return  a pair value
		 */
		public V Value()
		{
			return value;
		}
		
		/**
		 * Returns the key of the {@code Pair}.
		 * 
		 * @return  a pair key
		 */
		public K Key()
		{
			return key;
		}
	}

	
	/**
	 * Returns the keys of the {@code Dictionary}.
	 * 
	 * @return  a key iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<K> Keys()
	{
		return () -> new KeyIterator<>(this);
	}
	
	/**
	 * Returns the values of the {@code Dictionary}.
	 * 
	 * @return  a value iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<V> Values()
	{
		return () -> new ValueIterator<>(this);
	}
	

	/**
	 * Puts a key-value pair into the {@code Dictionary}.
	 * 
	 * @param key  a key to use
	 * @param val  a value to use
	 * @return  a previous dictionary value
	 */
	public abstract V put(K key, V val);

	/**
	 * Removes a key-value pair from the {@code Dictionary}.
	 * 
	 * @param key  a key to use
	 * @return  a previous dictionary value
	 */
	public abstract V remove(K key);
	
	/**
	 * Returns a value from the {@code Dictionary}.
	 * 
	 * @param key  a key to use
	 * @return  a current dictionary value
	 */
	public abstract V get(K key);
}