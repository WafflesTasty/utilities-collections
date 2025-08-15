package waffles.utils.sets.utilities.keymaps.iterators;

import java.util.Iterator;
import java.util.Map.Entry;

import waffles.utils.sets.countable.keymaps.KeyMap;
import waffles.utils.sets.utilities.keymaps.Pair;

/**
 * A {@code PairGenerator} generates {@code Pairs} for a {@code KeyMap.Java}.
 *
 * @author Waffles
 * @since 10 Aug 2023
 * @version 1.1
 *
 *
 * @param <K>  a key type
 * @param <V>  a value type
 * @param <P>  a pair type
 * @see Iterator
 * @see Pair
 */
public class PairGenerator<K, V, P extends Pair<K, V>> implements Iterator<P>
{
	private KeyMap.Java<K, V> map;
	private Iterator<? extends Entry<K, V>> pairs;
	
	/**
	 * Creates a new {@code PairGenerator}.
	 * 
	 * @param m  a key map
	 * 
	 * 
	 * @see KeyMap
	 */
	public PairGenerator(KeyMap.Java<K, V> m)
	{
		pairs = m.Delegate().entrySet().iterator();
		map = m;
		
	}
	
		
	@Override
	public boolean hasNext()
	{
		return pairs.hasNext();
	}
	
	@Override
	public P next()
	{
		Entry<K, V> e = pairs.next();
		
		
		K k = e.getKey();
		V v = e.getValue();
		
		return (P) map.create(k, v);
	}
}