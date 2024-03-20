package waffles.utils.sets.keymaps.delegate;

import java.util.HashMap;

import waffles.utils.sets.keymaps.DelegateMap;

/**
 * A {@code JHashMap} defines a {@code KeyMap} which is backed by a Java {@code Map}.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @param <K>  a map key type
 * @param <V>  a map value type
 * @see DelegateMap
 */
public class JHashMap<K, V> implements DelegateMap<K, V>
{
	private HashMap<K, V> data;

	/**
	 * Creates a new {@code JHashMap}.
	 */
	public JHashMap()
	{
		data = new HashMap<>();
	}

	
	@Override
	public int hashCode()
	{
		return data.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof JHashMap)
		{
			JHashMap<?,?> other = (JHashMap<?,?>) obj; 
			return data.equals(other.data);
		}
		
		return false;
	}
	
	@Override
	public HashMap<K, V> Delegate()
	{
		return data;
	}
}