package waffles.utils.sets.countable.keymaps.search;

import java.util.Comparator;

import waffles.utils.sets._old.trees.binary.search.BSNode;
import waffles.utils.sets._old.trees.binary.search.BSTree;
import waffles.utils.sets.countable.keymaps.KeyMap;
import waffles.utils.sets.utilities.keymaps.Pair;

/**
 * A {@code BSMap} implements a {@code KeyMap} by internally storing data in a {@code BSTree}.
 * This structure is useful for a large map with only very few values.
 *
 * @author Waffles
 * @since 23 Aug 2023
 * @version 1.1
 *
 *
 * @param <K>  a key type
 * @param <V>  a value type
 * @see KeyMap
 */
public class BSMap<K, V> implements KeyMap<K,V>
{
	private BSTree<Pair<K,V>> data;

	/**
	 * Creates a new {@code BSMap}.
	 * 
	 * @param c  a key comparator
	 * 
	 * 
	 * @see Comparator
	 */
	public BSMap(Comparator<K> c)
	{
		data = new BSTree<>((p1, p2) ->
		{
			K k1 = p1.Key();
			K k2 = p2.Key();
			
			return c.compare(k1, k2);
		});
	}
	
	/**
	 * Creates a new {@code BSMap}.
	 */
	public BSMap()
	{
		data = new BSTree<>();
	}
	
	/**
	 * Searches the {@code BSMap} for a key.
	 * 
	 * @param val  a value to find
	 * @return  a value key
	 */
	public K search(V val)
	{
		for(Pair<K,V> pair : data)
		{
			if(val.equals(pair.Value()))
			{
				return pair.Key();
			}
		}
		
		return null;
	}
	
	
	@Override
	public Iterable<Pair<K, V>> Pairs()
	{
		return data;
	}

	@Override
	public V put(K key, V val)
	{
		Pair<K,V> pNew = new Pair.Base<>(key, val);
		if(data.isEmpty())
		{
			data.add(pNew);
			return null;
		}

		
		BSNode<Pair<K,V>> pNode = data.search(pNew);
		Pair<K,V> pOld = pNode.Value();

		V vOld = null;
		if(key.equals(pOld.Key()))
		{
			vOld = pOld.Value();
			pNode.delete();
		}

		data.add(pNew);
		return vOld;
	}

	@Override
	public V remove(K key)
	{
		Pair<K,V> pNew = new Pair.Base<>(key, null);
		if(data.isEmpty())
		{
			return null;
		}
		
		
		BSNode<Pair<K,V>> pNode = data.search(pNew);
		Pair<K,V> pOld = pNode.Value();
		
		V vOld = null;
		if(key.equals(pOld.Key()))
		{
			vOld = pOld.Value();
			pNode.delete();
		}
		
		return vOld;
	}

	@Override
	public V get(K key)
	{
		Pair<K,V> pNew = new Pair.Base<>(key, null);
		if(data.isEmpty())
		{
			return null;
		}
		
		
		BSNode<Pair<K,V>> pNode = data.search(pNew);
		Pair<K,V> pOld = pNode.Value();
		
		if(key.equals(pOld.Key()))
		{
			return pOld.Value();
		}
		
		return null;
	}
	
	
	@Override
	public void clear()
	{
		data.clear();
	}
	
	@Override
	public int Count()
	{
		return data.Count();
	}
}