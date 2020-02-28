package zeno.util.coll.utilities;

import zeno.util.coll.Index;
import zeno.util.coll.utilities.iterators.IndexIterator;

/**
 * The {@code Iterables} class provides a few basic iterables over collections.
 *
 * @author Zeno
 * @since 28 Feb 2020
 * @version 1.0
 */
public final class Iterables
{
	/**
	 * Returns an iterable over a generic {@code Index}.
	 * 
	 * @param <V>  an index value type
	 * @param index  a target index
	 * @param min  a minimum coordinate
	 * @param max  a maximum coordinate
	 * @return  an index iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public static <V> Iterable<V> index(Index<V> index, int[] min, int[] max)
	{
		return () -> new IndexIterator<>(index, min, max);
	}
	
	
	private Iterables()
	{
		// NOT APPLICABLE
	}
}