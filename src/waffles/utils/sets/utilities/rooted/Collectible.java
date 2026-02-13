package waffles.utils.sets.utilities.rooted;

/**
 * A {@code Collectible} object defines its own {@code Collector}.
 * This allows it to be part of a {@code Set}.
 *
 * @author Waffles
 * @since 15 Aug 2025
 * @version 1.1
 */
@FunctionalInterface
public interface Collectible
{
	/**
	 * Returns the architecture of the {@code Collectible}.
	 * 
	 * @return  a set architecture
	 * 
	 * 
	 * @see Collector
	 */
	public abstract Collector Arch();
}