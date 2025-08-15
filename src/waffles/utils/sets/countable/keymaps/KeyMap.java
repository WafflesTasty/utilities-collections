package waffles.utils.sets.countable.keymaps;

import java.util.Map;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.sets.utilities.keymaps.iterators.KeyIterator;
import waffles.utils.sets.utilities.keymaps.iterators.PairGenerator;
import waffles.utils.sets.utilities.keymaps.iterators.ValueIterator;
import waffles.utils.tools.patterns.properties.values.Decorator;

/**
 * A {@code KeyMap} defines a mapping between key-value pairs.
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
	 * A {@code Wrapper} defines a wrapper around another {@code KeyMap}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @param <K>  a key type
	 * @param <V>  a value type
	 * @see CountableSet
	 * @see KeyMap
	 */
	public static interface Wrapper<K, V> extends CountableSet.Wrapper, KeyMap<K, V>
	{
		@Override
		public abstract KeyMap<K, V> Delegate();
				
		@Override
		public default <P extends Pair<K, V>> Iterable<P> Pairs()
		{
			return Delegate().Pairs();
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
		public default V get(K key)
		{
			return Delegate().get(key);
		}
	}
	
	/**
	 * A {@code Java KeyMap} defines a wrapper around a {@code Map}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <K>  a key type
	 * @param <V>  a value type
	 * @see Decorator
	 * @see KeyMap
	 */
	public static interface Java<K, V> extends KeyMap<K, V>, Decorator
	{
		/**
		 * Creates a new key-value pair for the {@code KeyMap}.
		 * 
		 * @param k  a map key
		 * @param v  a map value
		 * @return  a new pair
		 * 
		 * 
		 * @see Pair
		 */
		public default Pair<K, V> create(K k, V v)
		{
			return new Pair.Base<>(k, v);
		}
		
		@Override
		public default <P extends Pair<K, V>> Iterable<P> Pairs()
		{
			return () -> new PairGenerator<>(this);
		}
		
		@Override
		public abstract Map<K, V> Delegate();
						
		
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
		public default V remove(K key)
		{
			return Delegate().remove(key);
		}
		
		@Override
		public default V put(K key, V val)
		{
			return Delegate().put(key, val);
		}
				
		@Override
		public default void clear()
		{
			Delegate().clear();
		}
	}
	
	
	/**
	 * Iterates the key-value pairs of the {@code KeyMap}.
	 * This method is made generic to allow iteration of subtypes.
	 * 
	 * @return  a pair iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Pair
	 */
	public abstract <P extends Pair<K, V>> Iterable<P> Pairs();

	
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
		return () -> new ValueIterator<>(this);
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
		return () -> new KeyIterator<>(this);
	}
	
	
	/**
	 * Puts a key-value pair into the {@code KeyMap}.
	 * 
	 * @param key  a map key
	 * @param val  a map value
	 * @return  an old value
	 */
	public abstract V put(K key, V val);

	/**
	 * Removes a key-value pair from the {@code KeyMap}.
	 * 
	 * @param key  a map key
	 * @return  an old value
	 */
	public abstract V remove(K key);
	
	/**
	 * Returns a value from the {@code KeyMap}.
	 * 
	 * @param key  a map key
	 * @return  a map value
	 */
	public abstract V get(K key);
}