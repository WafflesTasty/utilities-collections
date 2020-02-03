package zeno.util.coll.utilities.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import zeno.util.coll.utilities.AbstractSort;

/**
 * The {@code JavaSort} class defines Java's default sorting method.
 * 
 * <ul>
 * 	<li> This algorithm has a worst-case complexity of O(n log n).
 * 	<li> This algorithm has an average complexity of O(n log n).
 * 	<li> This algorithm has a best-case complexity of O(n).
 * </ul>
 * 
 * @author Zeno
 * @since Mar 26, 2017
 * @version 1.0
 * 
 *
 * @param <O>  an object type
 * @see AbstractSort
 */
public class JavaSort<O> extends AbstractSort<O>
{
	/**
	 * Creates a new {@code JavaSort}.
	 * </br> Without a comparator, this
	 * sort depends on natural ordering.
	 */
	public JavaSort()
	{
		super();
	}
	
	/**
	 * Creates a new {@code JavaSort}.
	 * 
	 * @param c  a comparator to use
	 * 
	 * 
	 * @see Comparator
	 */
	public JavaSort(Comparator<O> c)
	{
		super(c);
	}
	
		
	@Override
	public void sort(O[] list, int start, int end)
	{
		Arrays.sort(list, start, end, this);
	}

	@Override
	public void sort(List<O> list, int start, int end)
	{
		Collections.sort(list.subList(start, end), this);
	}
}