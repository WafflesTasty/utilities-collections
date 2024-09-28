package waffles.utils.sets.mutable;

import waffles.utils.sets.IterableSet;
import waffles.utils.sets.MutableSet;

/**
 * An {@code AtomicSet} is a mutable set which can iterate over its contents.
 *
 * @author Waffles
 * @since Feb 02, 2020
 * @version 1.1
 * 
 * 
 * @param <O>  a set object type
 * @see IterableSet
 * @see MutableSet
 */
public interface AtomicSet<O> extends IterableSet<O>, MutableSet<O>
{
	// NOT APPLICABLE
}