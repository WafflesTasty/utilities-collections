package zeno.util.coll.tools.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import zeno.util.coll.tools.Sort;

/**
 * The {@code JavaSort} class defines Java's default sorting method.
 * 
 * <ul>
 * 	<li> This algorithm has a worst-case complexity of O(n log n).
 * 	<li> This algorithm has an average complexity of O(n log n).
 * 	<li> This algorithm has a best-case complexity of O(n).
 * </ul>
 * 
 * @since Mar 26, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects being sorted
 * @see Sort
 */
public class JavaSort<O> extends Sort<O>
{
	/**
	 * Creates a new {@code JavaSort}.
	 * 
	 * @param c  a comparator to use
	 * @see Comparator
	 */
	public JavaSort(Comparator<O> c)
	{
		super(c);
	}
	
	/**
	 * Creates a new {@code JavaSort}.
	 * Without a comparator, this sort
	 * depends on natural ordering.
	 */
	public JavaSort()
	{
		super();
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