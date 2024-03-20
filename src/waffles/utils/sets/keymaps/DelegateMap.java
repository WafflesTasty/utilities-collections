package waffles.utils.sets.keymaps;

import java.util.Map;
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