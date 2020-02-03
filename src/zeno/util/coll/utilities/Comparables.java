package zeno.util.coll.utilities;

/**
 * The {@code Comparables} class defines basic operations for {@link Comparable} objects.
 * 
 * @author Zeno
 * @since Apr 25, 2017
 * @version 1.0
 */
public final class Comparables
{
	/**
	 * Returns the minimum of a list of {@code Comparables}.
	 * 
	 * @param objects  an object list
	 * @return  the minimum object
	 * 
	 * 
	 * @see Comparable
	 */
	public static <O> O min(Comparable<O>... objects)
	{
		Comparable<O> min = objects[0];
		for(int i = 1; i < objects.length; i++)
		{
			Comparable<O> o = objects[i];
			if(min.compareTo((O) o) > 0)
			{
				min = o;
			}
		}
		
		return (O) min;
	}
	
	/**
	 * Returns the maximum of a list of {@code Comparables}.
	 * 
	 * @param objects  an object list
	 * @return  the maximum object
	 * 
	 * 
	 * @see Comparable
	 */
	public static <O> O max(Comparable<O>... objects)
	{
		Comparable<O> max = objects[0];
		for(int i = 1; i < objects.length; i++)
		{
			Comparable<O> o = objects[i];
			if(max.compareTo((O) o) < 0)
			{
				max = o;
			}
		}
		
		return (O) max;
	}
	
	
	private Comparables()
	{
		// NOT APPLICABLE
	}
}