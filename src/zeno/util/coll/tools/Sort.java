package zeno.util.coll.tools;

import java.util.Comparator;
import java.util.List;

/**
 * The {@code Sort} class defines the base for an object sorting algorithm.
 * 
 * @since Mar 26, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects being sorted
 * @see Comparator
 */
public abstract class Sort<O> implements Comparator<O>
{	
	private Comparator<O> comparator;
	
	/**
	 * Creates a new {@code Sort}.
	 * 
	 * @param c  a comparator to use
	 * @see Comparator
	 */
	public Sort(Comparator<O> c)
	{
		comparator = c;
	}
	
	/**
	 * Creates a new {@code Sort}.
	 * Without a comparator, this sort
	 * depends on natural ordering.
	 */
	public Sort()
	{
		comparator = this;
	}
	
	
	/**
	 * Sorts a list from {@code start} to {@code end}.
	 * 
	 * @param list  an array to sort
	 * @param start  the start index
	 * @param end  the end index
	 */
	public abstract void sort(O[] list, int start, int end);

	/**
	 * Sorts a list from {@code start} to {@code end}.
	 * 
	 * @param list  a list to sort
	 * @param start  the start index
	 * @param end  the end index
	 * @see List
	 */
	public abstract void sort(List<O> list, int start, int end);
	
	
	/**
	 * Sorts a list over all its elements.
	 * 
	 * @param list  an array to sort
	 */
	public void sort(O[] list)
	{
		sort(list, 0, list.length);
	}

	/**
	 * Sorts a list over all its elements.
	 * 
	 * @param list  a list to sort
	 * @see List
	 */
	public void sort(List<O> list)
	{
		sort(list, 0, list.size());
	}
	
	
	@Override
	public int compare(O o1, O o2)
	{
		if(comparator != this)
		{
			return comparator.compare(o1, o2);
		}
		
		if(o1 instanceof Comparable)
		{
			return ((Comparable<O>) o1).compareTo(o2);
		}
		
		throw new IllegalArgumentException("No compare method found!");
	}
}