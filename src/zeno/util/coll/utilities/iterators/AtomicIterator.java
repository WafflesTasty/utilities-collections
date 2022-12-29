package zeno.util.coll.utilities.iterators;

import zeno.util.coll.indices.Index.Atomic;

/**
 * The {@code AtomicIterator} class iterates over a subsection of an {@code Atomic Index}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 *
 *
 * @param <V>  an index value type
 * @see IndexIterator
 */
public class AtomicIterator<V> extends IndexIterator<V>
{
	/**
	 * Creates a new {@code AtomicIterator}.
	 * 
	 * @param index  a target index
	 * @param min    an index minimum
	 * @param max    an index maximum
	 * 
	 * 
	 * @see Atomic
	 */
	public AtomicIterator(Atomic<V> index, int[] min, int[] max)
	{
		super(index, min, max);
	}
	
	/**
	 * Creates a new {@code AtomicIterator}.
	 * 
	 * @param index  a target index
	 * 
	 * 
	 * @see Atomic
	 */
	public AtomicIterator(Atomic<V> index)
	{
		super(index);
	}
	
	
	@Override
	public void remove()
	{
		Atomic<V> index = (Atomic<V>) Index();
		index.remove(Current());
	}
}