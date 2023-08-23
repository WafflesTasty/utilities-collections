package waffles.util.sets.indexed.tiles;

/**
 * A {@code Tile} defines the objects stored in a {@code TiledIndex}.
 *
 * @author Waffles
 * @since 23 Aug 2023
 * @version 1.0
 */
public interface Tile
{
	/**
	 * Returns the coordinate of the {@code Tile}.
	 * 
	 * @return  a tile coordinate
	 */
	public abstract int[] Coordinate();
}