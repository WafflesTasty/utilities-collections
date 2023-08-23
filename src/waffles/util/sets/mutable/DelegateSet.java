package waffles.util.sets.mutable;

import java.util.Collection;
import java.util.Iterator;

import waffles.utils.tools.patterns.semantics.Decorator;

/**
 * A {@code DelegateSet} defines an atomic set which delegates
 * its functionality to a Java {@code Collection}.
 *
 * @author Waffles
 * @since 06 Aug 2023
 * @version 1.1
 * 
 * 
 * @param <O>  a set object type
 * @see Collection
 * @see AtomicSet
 * @see Decorator
 */
public interface DelegateSet<O> extends AtomicSet<O>, Decorator
{		
	@Override
	public abstract Collection<O> Delegate();
	
	
	@Override
	public default int Count()
	{
		return Delegate().size();
	}

	@Override
	public default Iterator<O> iterator()
	{
		return Delegate().iterator();
	}

	@Override
	public default boolean contains(O obj)
	{
		return Delegate().contains(obj);
	}
	
	
	@Override
	public default void remove(O obj)
	{
		Delegate().remove(obj);	
	}
	
	@Override
	public default void add(O obj)
	{
		Delegate().add(obj);
	}

	@Override
	public default void clear()
	{
		Delegate().clear();
	}
}