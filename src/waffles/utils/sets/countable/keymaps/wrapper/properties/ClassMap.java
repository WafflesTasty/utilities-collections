package waffles.utils.sets.countable.keymaps.wrapper.properties;

import java.util.Iterator;

import waffles.utils.sets.countable.keymaps.wrapper.JavaMap;
import waffles.utils.tools.patterns.properties.Properties;

/**
 * A {@code ClassMap} defines a map which uses classes as keys.
 * Care must be taken to avoid key collisions due to class inheritance.
 * The {@link #SubValues(Class)} method iterates over all pairs whose
 * keys are classes that the given object inherits. 
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.1
 * 
 * 
 * @see Properties
 * @see JavaMap
 * @see Class
 */
public class ClassMap extends JavaMap<Class<?>, Object> implements Properties
{
	/**
	 * The {@code SubValues} iterator traverses all values
	 * which have a key that is a subtype of the
	 * given class type.
	 *
	 * @author Waffles
	 * @since 10 Aug 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 */
	public class SubValues implements Iterator<Object>
	{
		private Class<?> type;
		private Iterator<Class<?>> keys;
		private Object next;
		
		/**
		 * Creates a new {@code SubValues}.
		 * 
		 * @param t  a class type
		 */
		public SubValues(Class<?> t)
		{
			type = t;
			keys = Keys().iterator();
			next = findNext();
		}

		
		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public Object next()
		{
			Object current = next;
			next = findNext();
			return current;
		}
		
		Object findNext()
		{
			if(!keys.hasNext())
			{
				return null;
			}
			
			
			Class<?> key = keys.next();
			if(key.isAssignableFrom(type))
			{
				return get(key);
			}

			return findNext();
		}
	}

	
	/**
	 * Returns all values mapped to an object in the {@code ClassMap}.
	 * This iterates over all key-value pairs whose keys subclass
	 * the class of the given object.
	 * 
	 * @param o  an object type
	 * @return   a value iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public Iterable<Object> SubValues(Object o)
	{
		return () -> new SubValues(o.getClass());
	}
	
	/**
	 * Returns all subvalues mapped to a class in the {@code ClassMap}.
	 * This iterates over all key-value pairs whose keys subclass
	 * the given class.
	 * 
	 * @param c  a class type
	 * @return   a value iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public Iterable<Object> SubValues(Class<?> c)
	{
		return () -> new SubValues(c);
	}
	
	
	@Override
	public ClassPair create(Class<?> k, Object v)
	{
		return new ClassPair(k, v);
	}
	
	@Override
	public void set(Class<?> t, Object v)
	{
		super.put(t, v);
	}

	@Override
	public <V> V get(Class<?> t)
	{
		return (V) super.get(t);
	}
}