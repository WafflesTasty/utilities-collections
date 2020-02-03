package zeno.util.coll.hashed.dict;

import java.util.Iterator;

/**
 * The {@code ClassMap} class defines a map which uses classes as keys.
 * </br> It is possible for objects to return multiple values due to inheritance.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <V>  a dictionary value type
 * @see HashedMap
 * @see Class
 */
public class ClassMap<V> extends HashedMap<Class<?>, V>
{
	class MapIterator implements Iterator<V>
	{
		private V next;
		private Object object;
		private Iterator<Class<?>> keys;
		
		public MapIterator(Object o)
		{
			object = o;
			keys = Keys().iterator();
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

	/**
	 * Returns all values mapped to an object in the {@code ClassMap}.
	 * 
	 * @param o  an object to use
	 * @return  a set of values
	 * 
	 * 
	 * @see Iterable
	 */
	public Iterable<V> Values(Object o)
	{
		return () -> new MapIterator(o);
	}
}