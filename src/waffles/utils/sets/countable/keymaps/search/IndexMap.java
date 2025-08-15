package waffles.utils.sets.countable.keymaps.search;

import waffles.utils.sets.countable.keymaps.wrapper.JavaMap;
import waffles.utils.sets.countable.wrapper.JavaList;
import waffles.utils.sets.indexed.IndexedSet;

/**
 * An {@code IndexMap} combines a {@code KeyMap} with an {@code IndexedSet}.
 * This allows the keys in the map to be accessed in order of addition.
 *
 * @author Waffles
 * @since 08 Aug 2025
 * @version 1.1
 *
 *
 * @param <K>  a key type
 * @param <V>  a value type
 * @see IndexedSet
 * @see JavaMap
 */
public class IndexMap<K, V> extends JavaMap<K, V> implements IndexedSet<K>
{	
	private JavaList<K> index;

	/**
	 * Creates a new {@code IndexMap}.
	 */
	public IndexMap()
	{
		index = new JavaList<>();
	}
	
	/**
	 * Returns the index of the {@code IndexMap}.
	 * 
	 * @return  an index list
	 * 
	 * 
	 * @see JavaList
	 */
	public JavaList<K> Index()
	{
		return index;
	}
	
	
	@Override
	public void clear()
	{
		super.clear();
		index.clear();
	}
	
	@Override
	public K get(int... crd)
	{
		return index.get(crd);
	}
	
	@Override
	public V put(K key, V val)
	{
		if(!index.contains(key))
			index.add(key);
		return super.put(key, val);
	}
	
	@Override
	public V remove(K key)
	{
		index.remove(key);
		return super.remove(key);
	}

	@Override
	public int Count()
	{
		return index.Count();
	}
}