package zeno.util.coll.mapping;

import java.util.HashMap;
import java.util.Iterator;

import zeno.util.tools.Serials;

/**
 * The {@code ClassMap} class defines a hash map with class types for key values.
 *
 * @author Zeno
 * @since Sep 16, 2015
 *
 * @param <V>  the type of values in the map
 * @see HashMap
 * @see Class
 */
public class ClassMap<V> extends HashMap<Class<?>, V>
{
	private static final long serialVersionUID = Serials.UTIL_COLL_CLASSMAP.number();

	
	/**
	 * Returns a value iterable mapped to an object.
	 * 
	 * @param o  an object to check
	 * @return  all compatible values
	 * @see Iterable
	 * @see Object
	 */
	public Iterable<V> getValues(Object o)
	{
		return () -> new Values(o);
	}
	
	class Values implements Iterator<V>
	{
		private V next;
		private Object object;
		private Iterator<Class<?>> keys;
		
		public Values(Object o)
		{
			object = o;
			keys = keySet().iterator();
			next = findNext();
		}
		
		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public V next()
		{
			V current = next;
			next = findNext();
			return current;
		}
		
		V findNext()
		{
			if(!keys.hasNext())
			{
				return null;
			}
			
			Class<?> c = keys.next();
			if(c.isInstance(object))
			{
				return get(c);
			}
			
			return findNext();
		}
	}
}