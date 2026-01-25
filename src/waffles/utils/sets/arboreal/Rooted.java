package waffles.utils.sets.arboreal;

/**
 * A {@code Rooted} defines an {@code Arboreal} tree.
 *
 * @author Waffles
 * @since 25 Jan 2026
 * @version 1.1
 */
@FunctionalInterface
public interface Rooted
{
	/**
	 * Returns the tree of the {@code Rooted}.
	 * 
	 * @return  an arboreal tree
	 * 
	 * 
	 * @see Arboreal
	 */
	public abstract Arboreal Tree();
}