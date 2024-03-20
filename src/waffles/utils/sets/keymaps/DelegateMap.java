package waffles.utils.sets.keymaps;

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
 * @version 1.1
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
	 * @version 1.1
	 *
	 *
	 * @param <K>  a key type
	 * @param <V>  a value type
	 * @param <P>  a key-pair type
	 * @see Iterator
	 * @see KeyPair
	 */
	public class Pairs<K, V, P extends KeyPair<K, V>> implements Iterator<P>
	{
		private KeyMap<K, V> source;
		private Iterator<Entry<K, V>> pairs;
		
		/**
		 * Creates a new {@code Pairs}.
		 * 
		 * @param src  a source map
		 * 
		 * 
		 * @see DelegateMap
		 */
		public Pairs(DelegateMap<K, V> src)
		{
			pairs = src.Delegate().entrySet().iterator();
			source = src;
			
		}
		
		
		@Override
		public P next()
		{
			Entry<K, V> e = pairs.next();

			V val = e.getValue();
			K key = e.getKey();
			
			return (P) source.createPair(key, val);
		}
		
		@Override
		public boolean hasNext()
		{
			return pairs.hasNext();
		}
	}
	
	
	@Override
	public default <P extends KeyPair<K, V>> Iterable<P> Pairs()
	{
		return () -> new Pairs<>(this);
	}
	
	@Override
	public abstract Map<K,V> Delegate();
		
	
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

	@Override
	public default int Count()
	{
		return Delegate().size();
	}
}