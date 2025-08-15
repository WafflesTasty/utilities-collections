package waffles.utils.sets.countable.keymaps.wrapper.properties;

import waffles.utils.sets.utilities.keymaps.Pair;

/**
 * A {@code ClassPair} defines a key-value pair for a {@code ClassMap}.
 *
 * @author Waffles
 * @since 15 Aug 2025
 * @version 1.1
 *
 * 
 * @see Class
 * @see Pair
 */
public class ClassPair extends Pair.Base<Class<?>, Object>
{
	/**
	 * Creates a new {@code ClassPair}.
	 * 
	 * @param k  a pair key
	 * @param v  a pair value
	 */
	public ClassPair(Class<?> k, Object v)
	{
		super(k, v);
	}
}