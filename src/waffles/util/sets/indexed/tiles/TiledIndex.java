package waffles.util.sets.indexed.tiles;

import waffles.util.sets.indexed.MutableIndex;

/**
 * A {@code TiledIndex} defines a mutable index over a set of tiles.
 * The index can iterate over its {@code Tile} objects, which
 * each define their own index coordinate.
 *
 * @author Waffles
 * @since 23 Aug 2023
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see MutableIndex
 * @see Tile
 */
public interface TiledIndex<T extends Tile> extends MutableIndex<T>
{
	/**
	 * Iterates over the tiles of the {@code TiledIndex}.
	 * 
	 * @return  a tile iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public abstract Iterable<T> Tiles();
	
	
	@Override
	public default int[] indexOf(T tile)
	{
		return tile.Coordinate();
	}
}