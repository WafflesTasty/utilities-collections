package zeno.util.coll.utilities.sorting;

import java.util.Comparator;
import java.util.List;

import zeno.util.coll.utilities.AbstractSort;

/**
 * The {@code BubbleSort} class defines a bubble sorting method.
 * 
 * <ul>
 * 	<li> This algorithm has a worst-case complexity of O(n�).
 * 	<li> This algorithm has an average complexity of O(n�).
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
public class BubbleSort<O> extends AbstractSort<O>
{
	/**
	 * Creates a new {@code BubbleSort}.
	 * </br> Without a comparator, this
	 * sort depends on natural ordering.
	 */
	public BubbleSort()
	{
		super(null);
	}
	
	/**
	 * Creates a new {@code BubbleSort}.
	 * 
	 * @param c  a comparator to use
	 * 
	 * 
	 * @see Comparator
	 */
	public BubbleSort(Comparator<O> c)
	{
		super(c);
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