package waffles.util.sets.atomic;

import waffles.util.sets.AtomicSet;
import waffles.utils.tools.patterns.semantics.Clearable;

/**
 * A {@code MutableSet} is an atomic set which allows the adding and removing of objects.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.0
 * 
 * 
 * @param <O>  a set object type
 * @see AtomicSet
 * @see Clearable
 */
public interface MutableSet<O> extends AtomicSet<O>, Clearable
{	
	/**
	 * Removes an object from the {@code MutableSet}.
	 * 
	 * @param obj  an object to remove
	 */
	public abstract void remove(O obj);
	
	/**
	 * Adds an object to the {@code MutableSet}.
	 * 
	 * @param obj  an object to add
	 */
	public abstract void add(O obj);
}