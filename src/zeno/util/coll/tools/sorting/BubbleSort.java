package zeno.util.coll.tools.sorting;

import java.util.Comparator;
import java.util.List;

import zeno.util.coll.tools.Sort;

/**
 * The {@code BubbleSort} class defines a bubble sorting method.
 * 
 * <ul>
 * 	<li> This algorithm has a worst-case complexity of O(n²).
 * 	<li> This algorithm has an average complexity of O(n²).
 * 	<li> This algorithm has a best-case complexity of O(n).
 * </ul>
 * 
 * @since Mar 26, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects being sorted
 * @see Sort
 */
public class BubbleSort<O> extends Sort<O>
{
	/**
	 * Creates a new {@code BubbleSort}.
	 * 
	 * @param c  a comparator to use
	 * @see Comparator
	 */
	public BubbleSort(Comparator<O> c)
	{
		super(c);
	}
	
	/**
	 * Creates a new {@code BubbleSort}.
	 * Without a comparator, this sort
	 * depends on natural ordering.
	 */
	public BubbleSort()
	{
		super(null);
	}
	
	
	@Override
	public void sort(O[] list, int start, int end)
	{
		if(list.length < 2 || end <= start)
		{
			return;
		}
				
		for(int i = start; i < end; i++)
		{
			for(int j = start; j < end - i - 1; j++)
			{
				int k = j + 1;
				O o1 = list[j];
				O o2 = list[k];
				
				int comp = compare(o1, o2);
				if(comp > 0)
				{
					list[j] = o2;
					list[k] = o1;
				}
			}
		}
	}

	@Override
	public void sort(List<O> list, int start, int end)
	{
		if(list.size() < 2 || end <= start)
		{
			return;
		}
		
		for(int i = start; i < end; i++)
		{
			for(int j = start; j < end - i - 1; j++)
			{
				int k = j + 1;
				O o1 = list.get(j);
				O o2 = list.get(k);
				
				int comp = compare(o1, o2);
				if(comp > 0)
				{
					list.set(j, o2);
					list.set(k, o1);
				}
			}
		}
	}
}