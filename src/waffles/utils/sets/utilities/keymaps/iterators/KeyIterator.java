package waffles.utils.sets.utilities.keymaps.iterators;

import java.util.Iterator;

import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.sets.utilities.keymaps.queries.MapQuery;

/**
 * A {@code KeyIterator} iterates the keys in a {@code KeyMap}.
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
public class KeyIterator<K, V> implements Iterator<K>
{
	private Iterator<? extends Pair<?, ?>> source;
	
	/**
	 * Creates a new {@code KeyIterator}.
	 * 
	 * @param qry  a map query
	 * 
	 * 
	 * @see MapQuery
	 */
	public KeyIterator(MapQuery<K, V> qry)
	{
		source = qry.Pairs();
	}
	
	
	@Override
	public boolean hasNext()
	{
		return source.hasNext();
	}

	@Override
	public K next()
	{
		return (K) source.next().Key();
	}
}