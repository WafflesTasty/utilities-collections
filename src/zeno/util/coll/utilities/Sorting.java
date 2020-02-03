package zeno.util.coll.utilities;

import java.util.Comparator;
import java.util.List;

import zeno.util.coll.utilities.sorting.BubbleSort;
import zeno.util.coll.utilities.sorting.JavaSort;
import zeno.util.coll.utilities.sorting.QuickSort;

/**
 * The {@code Sorting} class defines utility methods for sorting {@code Collections}.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 */
public final class Sorting
{
	/**
	 * Performs a bubble sort on a target {@code List}.
	 * 
	 * @param <O>  a list object type
	 * @param list  a target list to sort
	 * @param c  an object comparator
	 * 
	 * 
	 * @see Comparator
	 * @see List
	 */
	public static <O> void bubbleSort(List<O> list, Comparator<O> c)
	{
		new BubbleSort<>(c).sort(list);
	}
	
	/**
	 * Performs a bubble sort on a target {@code Array}.
	 * 
	 * @param <O>  an array object type
	 * @param list  a target array to sort
	 * @param c  an object comparator
	 * 
	 * 
	 * @see Comparator
	 */
	public static <O> void bubbleSort(O[] list, Comparator<O> c)
	{
		new BubbleSort<>(c).sort(list);
	}
	
	/**
	 * Performs a bubble sort on a target {@code List}.
	 * </br> This method assumes natural ordering on the objects.
	 * 
	 * @param <O>  a list object type
	 * @param list  a target list to sort
	 * 
	 * 
	 * @see List
	 */
	public static <O> void bubbleSort(List<O> list)
	{
		javaSort(list, null);
	}
	
	/**
	 * Performs a bubble sort on a target {@code Array}.
	 * </br> This method assumes natural ordering on the objects.
	 * 
	 * @param <O>  an array object type
	 * @param list  a target array to sort
	 */
	public static <O> void bubbleSort(O[] list)
	{
		javaSort(list, null);
	}
	
	
	/**
	 * Performs a native java sort on a target {@code List}.
	 * 
	 * @param <O>  a list object type
	 * @param list  a target list to sort
	 * @param c  an object comparator
	 * 
	 * 
	 * @see Comparator
	 * @see List
	 */
	public static <O> void javaSort(List<O> list, Comparator<O> c)
	{
		new JavaSort<>(c).sort(list);
	}
	
	/**
	 * Performs a native java sort on a target {@code Array}.
	 * 
	 * @param <O>  an array object type
	 * @param list  a target array to sort
	 * @param c  an object comparator
	 * 
	 * 
	 * @see Comparator
	 */
	public static <O> void javaSort(O[] list, Comparator<O> c)
	{
		new JavaSort<>(c).sort(list);
	}
	
	/**
	 * Performs a native java sort on a target {@code List}.
	 * </br> This method assumes natural ordering on the objects.
	 * 
	 * @param <O>  a list object type
	 * @param list  a target list to sort
	 * 
	 * 
	 * @see List
	 */
	public static <O> void javaSort(List<O> list)
	{
		javaSort(list, null);
	}
	
	/**
	 * Performs a native java sort on a target {@code Array}.
	 * </br> This method assumes natural ordering on the objects.
	 * 
	 * @param <O>  an array object type
	 * @param list  a target array to sort
	 */
	public static <O> void javaSort(O[] list)
	{
		javaSort(list, null);
	}
	
	
	/**
	 * Performs a quick sort on a target {@code List}.
	 * 
	 * @param <O>  a list object type
	 * @param list  a target list to sort
	 * @param c  an object comparator
	 * 
	 * 
	 * @see Comparator
	 * @see List
	 */
	public static <O> void quickSort(List<O> list, Comparator<O> c)
	{
		new QuickSort<>(c).sort(list);
	}
	
	/**
	 * Performs a quick sort on a target {@code Array}.
	 * 
	 * @param <O>  an array object type
	 * @param list  a target array to sort
	 * @param c  an object comparator
	 * 
	 * 
	 * @see Comparator
	 */
	public static <O> void quickSort(O[] list, Comparator<O> c)
	{
		new QuickSort<>(c).sort(list);
	}
	
	/**
	 * Performs a native java sort on a target {@code List}.
	 * </br> This method assumes natural ordering on the objects.
	 * 
	 * @param <O>  a list object type
	 * @param list  a target list to sort
	 * 
	 * 
	 * @see List
	 */
	public static <O> void quickSort(List<O> list)
	{
		javaSort(list, null);
	}
	
	/**
	 * Performs a quick sort on a target {@code Array}.
	 * </br> This method assumes natural ordering on the objects.
	 * 
	 * @param <O>  an array object type
	 * @param list  a target array to sort
	 */
	public static <O> void quickSort(O[] list)
	{
		javaSort(list, null);
	}
	
	
	private Sorting()
	{
		// NOT APPLICABLE
	}
}