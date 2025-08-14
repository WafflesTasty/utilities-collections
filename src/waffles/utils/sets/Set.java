package waffles.utils.sets;

import java.util.Collection;

import waffles.utils.tools.patterns.properties.values.Decorator;

/**
 * A {@code Set} defines the top-level structure of the {@code Collections} library.
 *
 * @author Waffles
 * @since 06 Nov 2023
 * @version 1.1
 */
public interface Set
{	
	/**
	 * A {@code Wrapper Set} defines a wrapper around another {@code Set}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @see Decorator
	 * @see Set
	 */
	public static interface Wrapper extends Set, Decorator
	{
		@Override
		public abstract Set Delegate();
	}
	
	/**
	 * A {@code Java Set} defines a wrapper around a {@code Collection}.
	 *
	 * @author Waffles
	 * @since 14 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see Decorator
	 * @see Set
	 */
	public static interface Java<O> extends Set, Decorator
	{
		@Override
		public abstract Collection<O> Delegate();
	}
}