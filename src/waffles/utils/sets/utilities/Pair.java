package waffles.utils.sets.utilities;

import waffles.utils.tools.patterns.properties.Immutable;
import waffles.utils.tools.patterns.properties.values.Valuable;

/**
 * A {@code Pair} defines a generic key-value pair.
 *
 * @author Waffles
 * @since 23 Mar 2024
 * @version 1.1
 *
 *
 * @param <K>  a key type
 * @param <V>  a value type
 * @see Immutable
 * @see Valuable
 */
public interface Pair<K, V> extends Immutable, Valuable<V>
{
	/**
	 * A {@code Pair.Mutable} is a mutable implementation of a {@code Pair}.
	 *
	 * @author Waffles
	 * @since 23 Mar 2024
	 * @version 1.1
	 *
	 *
	 * @param <K>  a key type
	 * @param <V>  a value type
	 * @see Pair
	 */
	public static class Mutable<K, V> implements Immutable.Mutable, Pair<K, V>
	{
		private K key;
		private V value;
		
		/**
		 * Creates a new {@code Pair.Mutable}.
		 * 
		 * @param k  a key object
		 * @param v  a value object
		 */
		public Mutable(K k, V v)
		{
			value = v;
			key = k;
		}
		
		/**
		 * Changes the value of the {@code Pair}.
		 * 
		 * @param v  a value object
		 */
		public void setValue(V v)
		{
			value = v;
		}
		
		/**
		 * Changes the key of the {@code Pair}.
		 * 
		 * @param k  a key object
		 */
		public void setKey(K k)
		{
			key = k;
		}
		

		@Override
		public V Value()
		{
			return value;
		}
		
		@Override
		public K Key()
		{
			return key;
		}
	}
	
	/**
	 * A {@code Pair.Base} is an immutable implementation of a {@code Pair}.
	 *
	 * @author Waffles
	 * @since 23 Mar 2024
	 * @version 1.1
	 *
	 *
	 * @param <K>  a key type
	 * @param <V>  a value type
	 * @see Pair
	 */
	public static class Base<K, V> implements Pair<K, V>
	{
		private K key;
		private V value;
		
		/**
		 * Creates a new {@code Pair.Base}.
		 * 
		 * @param k  a key object
		 * @param v  a value object
		 */
		public Base(K k, V v)
		{
			value = v;
			key = k;
		}
		

		@Override
		public V Value()
		{
			return value;
		}
		
		@Override
		public K Key()
		{
			return key;
		}
	}
	
	
	/**
	 * Returns the value of the {@code Pair}.
	 * 
	 * @return  a value object
	 */
	@Override
	public abstract V Value();
	
	/**
	 * Returns the key of the {@code Pair}.
	 * 
	 * @return  a key object
	 */
	public abstract K Key();
}