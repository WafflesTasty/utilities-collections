package zeno.util.coll.utilities.iterators;

import java.util.Iterator;

import zeno.util.coll.dictionary.Dictionary;
import zeno.util.coll.dictionary.Dictionary.Pair;

/**
 * The {@code KeyIterator} defines an iterator over {@code Dictionary} keys.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <K>  a dictionary key type
 * @param <V>  a dictionary value type
 * @see Iterator
 */
public class ValueIterator<K, V> implements Iterator<V>
{
	private Iterator<Pair<K, V>> source;
	
	/**
	 * Creates a new {@code KeyIterator}.
	 * 
	 * @param dic  a target dictionary
	 * 
	 * 
	 * @see Dictionary
	 */
	public ValueIterator(Dictionary<K, V> dic)
	{
		source = dic.iterator();
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