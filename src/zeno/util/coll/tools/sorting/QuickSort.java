package zeno.util.coll.tools.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import zeno.util.coll.tools.Sort;

/**
 * The {@code QuickSort} class defines a quick sorting method.
 * 
 * <ul>
 * 	<li> This algorithm has a worst-case complexity of O(n²).
 * 	<li> This algorithm has an average complexity of O(n log n).
 * 	<li> This algorithm has a best-case complexity of O(n log n).
 * </ul>
 * 
 * @since Mar 26, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects being sorted
 * @see Sort
 */
public class QuickSort<O> extends Sort<O>
{
	/**
	 * Creates a new {@code QuickSort}.
	 * 
	 * @param c  a comparator to use
	 * @see Comparator
	 */
	public QuickSort(Comparator<O> c)
	{
		super(c);
	}
	
	/**
	 * Creates a new {@code QuickSort}.
	 * Without a comparator, this sort
	 * depends on natural ordering.
	 */
	public QuickSort()
	{
		super();
	}
	
	
	@Override
	public void sort(O[] list, int start, int end)
	{
		if(list.length < 2 || end <= start)
		{
			return;
		}
		
		
		O pivot = list[start];
		
		List<O> lower = new ArrayList<>();
		List<O> higher = new ArrayList<>();
		
		for(int i = start + 1; i < end; i++)
		{
			O object = list[i];
			
			int comp = compare(pivot, object);
			if(comp > 0)
				lower.add(object);
			else
				higher.add(object);
		}
		
		sort(lower);
		lower.add(pivot);
		sort(higher);
		lower.addAll(higher);
		
		for(int i = start; i < end; i++)
		{
			list[i] = lower.get(i);
		}
	}

	@Override
	public void sort(List<O> list, int start, int end)
	{
		if(list.size() < 2 || end <= start)
		{
			return;
		}
		
		
		O pivot = list.get(start);
		
		List<O> lower = new ArrayList<>();
		List<O> higher = new ArrayList<>();
		
		for(int i = start + 1; i < end; i++)
		{
			O object = list.get(i);
			
			int comp = compare(pivot, object);
			if(comp > 0)
				lower.add(object);
			else
				higher.add(object);
		}
		
		sort(lower);
		lower.add(pivot);
		sort(higher);
		lower.addAll(higher);
		
		for(int i = start; i < end; i++)
		{
			list.set(i, lower.get(i));
		}
	}
}