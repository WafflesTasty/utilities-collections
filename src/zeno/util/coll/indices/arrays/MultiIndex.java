package zeno.util.coll.indices.arrays;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.coll.dictionary.Dictionary.Pair;
import zeno.util.coll.dictionary.maps.HashedMap;
import zeno.util.coll.indices.Index;
import zeno.util.tools.Integers;
import zeno.util.tools.helper.Array;

/**
 * The {@code MultiIndex} class defines a set of indices each offset with an origin vector.
 *
 * @author Waffles
 * @since 17 Apr 2022
 * @version 1.0
 *
 *
 * @param <V>  an index value
 * @see Index
 */
public class MultiIndex<V> implements Index.Atomic<V>
{
	private int order;
	private HashedMap<Vector, Atomic<V>> map;
	
	/**
	 * Creates a new {@code MultiIndex}.
	 * 
	 * @param ord  an index order
	 */
	public MultiIndex(int ord)
	{
		map = new HashedMap<>();
		order = ord;
	}
	
	/**
	 * Adds an index to the {@cod MultiIndex}.
	 * 
	 * @param v  an origin vector
	 * @param i  a target index
	 * 
	 * 
	 * @see Vector
	 */
	public void add(Vector v, Atomic<V> i)
	{
		map.put(v, i);
	}
	
	/**
	 * Removes an index from the {@code MultiIndex}.
	 * 
	 * @param v  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public void remove(Vector v)
	{
		map.remove(v);
	}


	@Override
	public V get(int... coords)
	{
		for(Pair<Vector, Atomic<V>> pair : map)
		{
			Vector v = pair.Key();
			Atomic<V> index = pair.Value();
			
			int[] crd = new int[coords.length];
			for(int i = 0; i < coords.length; i++)
			{
				crd[i] = (int) (coords[i] - v.get(i));
			}
			
			if(index.contains(crd))
			{
				return index.get(crd);
			}
		}
		
		return null;
	}
	
	@Override
	public V put(V val, int... coords)
	{
		for(Pair<Vector, Atomic<V>> pair : map)
		{
			Vector v = pair.Key();
			Atomic<V> index = pair.Value();
			
			int[] crd = new int[coords.length];
			for(int i = 0; i < coords.length; i++)
			{
				crd[i] = (int) (coords[i] - v.get(i));
			}
			
			if(index.contains(crd))
			{
				return index.put(val, crd);
			}
		}
		
		return null;
	}
	
	@Override
	public V remove(int... coords)
	{
		return put(null, coords);
	}

	@Override
	public int[] indexOf(V val)
	{
		for(Pair<Vector, Atomic<V>> pair : map)
		{
			Vector v = pair.Key();
			Atomic<V> atom = pair.Value();
			int[] index = atom.indexOf(val);
			if(index != null)
			{
				for(int i = 0; i < index.length; i++)
				{
					index[i] = (int) (index[i] + v.get(i));
				}
				
				return index;
			}
		}
		
		return null;
	}
	
	
	@Override
	public int[] Minimum()
	{
		int[] min = new int[Order()];
		int iMax = Integers.MAX_VALUE;
		min = Array.fill.in(min, iMax);

		for(Pair<Vector, Atomic<V>> pair : map)
		{
			Vector v = pair.Key();
			Atomic<V> index = pair.Value();
			int[] iMin = index.Minimum();
			
			for(int i = 0; i < Order(); i++)
			{
				int iVal = (int) (iMin[i] + v.get(i)); 
				min[i] = Integers.min(min[i], iVal);
			}
		}
		
		return min;
	}
	
	@Override
	public int[] Maximum()
	{
		int[] max = new int[Order()];
		int iMin = Integers.MIN_VALUE;
		max = Array.fill.in(max, iMin);

		for(Pair<Vector, Atomic<V>> pair : map)
		{
			Vector v = pair.Key();
			Atomic<V> index = pair.Value();
			int[] iMax = index.Maximum();
			
			for(int i = 0; i < Order(); i++)
			{
				int iVal = (int) (iMax[i] + v.get(i)); 
				max[i] = Integers.max(max[i], iVal);
			}
		}
		
		return max;
	}
	
	@Override
	public int Order()
	{
		return order;
	}
}