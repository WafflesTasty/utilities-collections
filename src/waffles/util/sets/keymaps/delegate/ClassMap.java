package waffles.util.sets.keymaps.delegate;

import java.util.Iterator;

import waffles.util.sets.keymaps.KeyPair;

/**
 * The {@code ClassMap} class defines a map which uses classes as keys.
 * Care must be taken to avoid key collisions due to class inheritance.
 * The {@link #SubValues(Class)} method iterates over all pairs whose
 * keys are classes that the given object inherits. 
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @param <V>  a map value type
 * @see JHashMap
 * @see Class
 */
public class ClassMap<V> extends JHashMap<Class<?>, V>
{
	/**
	 * The {@code SubValues} iterator traverses all values
	 * which are tied to a class which the given object subclasses.
	 *
	 * @author Waffles
	 * @since 10 Aug 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Iterator
	 */
	public class SubValues implements Iterator<V>
	{
		private V next;
		private Class<?> type;
		private Iterator<KeyPair<Class<?>, V>> pairs;
		
		/**
		 * Creates a new {@code SubValues}.
		 * 
		 * @param c  a target class
		 */
		public SubValues(Class<?> c)
		{
			type = c;
			pairs = Pairs().iterator();
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
			if(!pairs.hasNext())
			{
				return null;
			}
			
			
			KeyPair<Class<?>, V> pair = pairs.next();
			if(pair.Key().isAssignableFrom(type))
			{
				return pair.Value();
			}

			return findNext();
		}
	}

	
	/**
	 * Returns all subvalues mapped to a class in the {@code ClassMap}.
	 * This iterates over all key-value pairs whose keys subclass
	 * the given class.
	 * 
	 * @param c  a parent class
	 * @return   a set of subvalues
	 * 
	 * 
	 * @see Iterable
	 */
	public Iterable<V> SubValues(Class<?> c)
	{
		return () -> new SubValues(c);
	}
	
	/**
	 * Returns all subvalues mapped to an object in the {@code ClassMap}.
	 * This iterates over all key-value pairs whose keys subclass
	 * the class of the given object.
	 * 
	 * @param o  a parent object
	 * @return   a set of subvalues
	 * 
	 * 
	 * @see Iterable
	 */
	public Iterable<V> SubValues(Object o)
	{
		return () -> new SubValues(o.getClass());
	}
}