package waffles.util.sets.keymaps;

/**
 * The {@code KeyPair} class defines a dictionary entry through a key-value pair.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <K>  a key type
 * @param <V>  a value type
 */
public class KeyPair<K, V>
{
	private K key;
	private V value;
	
	/**
	 * Creates a new {@code KeyPair}.
	 * 
	 * @param k  a pair key
	 * @param v  a pair value
	 */
	public KeyPair(K k, V v)
	{
		value = v;
		key = k;
	}
	
	
	/**
	 * Returns the value of the {@code KeyPair}.
	 * 
	 * @return  a pair value
	 */
	public V Value()
	{
		return value;
	}
	
	/**
	 * Returns the key of the {@code KeyPair}.
	 * 
	 * @return  a pair key
	 */
	public K Key()
	{
		return key;
	}
}