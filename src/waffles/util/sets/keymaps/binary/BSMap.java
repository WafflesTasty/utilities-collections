package waffles.util.sets.keymaps.binary;

import waffles.util.sets.keymaps.KeyMap;
import waffles.util.sets.keymaps.KeyPair;
import waffles.util.sets.trees.binary.BSNode;
import waffles.util.sets.trees.binary.BSTree;

/**
 * A {@code BSMap} implements a {@code KeyMap} by internally storing data in a {@code BSTree}.
 * The tree is ordered by the natural ordering of the key values.
 *
 * @author Waffles
 * @since 23 Aug 2023
 * @version 1.0
 *
 *
 * @param <K>  a map key type
 * @param <V>  a map value typep
 * @see Comparable
 * @see KeyMap
 */
public class BSMap<K extends Comparable<K>,V> implements KeyMap<K,V>
{
	private BSTree<KeyPair<K,V>> data;

	/**
	 * Creates a new {@code BSMap}.
	 */
	public BSMap()
	{
		data = new BSTree<>((o1,o2) ->
		{
			return o1.Key().compareTo(o2.Key());
		});
	}
	
	/**
	 * Searches the {@code BSMap} for the index of a value.
	 * 
	 * @param val  a value to find
	 * @return  a corresponding key, or null
	 */
	public K search(V val)
	{
		for(KeyPair<K,V> pair : data)
		{
			if(val.equals(pair.Value()))
			{
				return pair.Key();
			}
		}
		
		return null;
	}
	
	
	@Override
	public Iterable<KeyPair<K, V>> Pairs()
	{
		return data;
	}
	
	@Override
	public V put(K key, V val)
	{
		KeyPair<K,V> pNew = new KeyPair<>(key, val);
		BSNode<KeyPair<K,V>> pNode = data.search(pNew);
		KeyPair<K,V> pOld = pNode.Value();
		
		
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
		KeyPair<K,V> pNew = new KeyPair<>(key, null);
		BSNode<KeyPair<K,V>> pNode = data.search(pNew);
		KeyPair<K,V> pOld = pNode.Value();
		
		
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
		KeyPair<K,V> pNew = new KeyPair<>(key, null);
		BSNode<KeyPair<K,V>> pNode = data.search(pNew);
		KeyPair<K,V> pOld = pNode.Value();
		
		
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