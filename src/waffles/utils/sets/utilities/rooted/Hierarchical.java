package waffles.utils.sets.utilities.rooted;

/**
 * A {@code Hierarchical} object defines its own {@code Hierarchy}.
 * This allows parent relationships between similar objects.
 *
 * @author Waffles
 * @since Mar 5, 2016
 * @version 1.0
 * 
 * 
 * @see Collectible
 */
@FunctionalInterface
public interface Hierarchical extends Collectible
{		
	@Override
	public abstract Hierarchy Arch();
}