package zeno.util.coll;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.tools.bounds.Bounded;
import zeno.util.tools.Array;
import zeno.util.tools.generic.properties.Copyable;

/**
 * The {@code Grid} class divides a space into equally sized cuboids.
 * 
 * @param <O>  the type of objects in the grid
 * @param <T>  the type of tiles in the grid
 *  
 * @since Mar 30, 2017
 * @author Zeno
 * 
 * @see Copyable
 * @see Bounded
 * @see Space
 * @see Tile
 */
public abstract class Grid<T extends Grid.Tile, O extends Bounded> implements Copyable<Grid<T,O>>, Space<O>
{
	/**
	 * The {@code Tile} interface defines a single tile in a {@link Grid}.
	 * 
	 * @since Mar 31, 2017
	 * @author Zeno
	 * 
	 * @see Copyable
	 * @see Grid
	 */
	public static interface Tile extends Copyable<Tile>
	{
		/**
		 * Returns the coördinates of the {@code Tile}.
		 * 
		 * @return  the tile's coördinates
		 */
		public abstract int[] Coordinates();
	}
	
	
	private Tile[] tiles;
	private int[] dimensions;
	
	/**
	 * Creates a new {@code Grid}.
	 * 
	 * @param dim  the grid's dimensions
	 */
	public Grid(int... dim)
	{
		dimensions = dim;
		tiles = new Tile[TileCount()];
	}
	
	/**
	 * Returns the dimensions of the {@code Grid}.
	 * 
	 * @return  the grid's dimensions
	 */
	public int[] Dimensions()
	{
		return dimensions;
	}
	
	/**
	 * Returns the tile count of the {@code Grid}.
	 * 
	 * @return  the grid's tile count
	 */
	public int TileCount()
	{
		return Array.product.of(dimensions);
	}
	
	
	/**
	 * Changes a tile in the {@code Grid}.
	 * 
	 * @param tile  a new tile
	 */
	public void set(T tile)
	{
		if(tiles == null)
		{
			tiles = new Tile[TileCount()];
		}
		
		tiles[toIndex(tile.Coordinates())] = tile;
	}
	
	/**
	 * Returns a tile in the {@code Grid}.
	 * <br> The parameters are treated as array indices.
	 * 
	 * @param coords  the tile's index
	 * @return  a grid tile
	 */
	public T get(int... coords)
	{
		if(tiles != null)
		{
			return (T) tiles[toIndex(coords)];
		}
		
		return null;
	}
	
	/**
	 * Clears a tile in the {@code Grid}.
	 * 
	 * @param coords  the tile's index
	 * @return  the previous tile
	 */
	public T clear(int... coords)
	{
		if(tiles != null)
		{
			int index = toIndex(coords);
			
			Tile tile = tiles[index];
			tiles[index] = null;
			return (T) tile;
		}

		return null;
	}
	
	/**
	 * Returns a tile in the {@code Grid}.
	 * <br> The parameters are treated as space coördinates.
	 * 
	 * @param vals  the tile's coördinates
	 * @return  a grid tile
	 */
	public T get(float... vals)
	{
		if(tiles != null)
		{
			int[] coords = new int[order()];
			for(int i = 0; i < order(); i++)
			{
				coords[i] = getIndex(i, vals[i]);
				if(coords[i] < 0 || dimensions[i] <= coords[i])
				{
					return null;
				}
			}
			
			return get(coords);
		}
		
		return null;
	}
	
	/**
	 * Returns a tile in the {@code Grid}.
	 * <br> The parameters are treated as space coördinates.
	 * 
	 * @param v  the tile's coördinates
	 * @return  a grid tile
	 */
	public T get(Vector v)
	{
		return get(v.values());
	}

	
	@Override
	public Grid<T, O> copy()
	{
		Grid<T, O> grid = Copyable.super.copy();
		for(int i = 0; i < TileCount(); i++)
		{
			grid.set((T) tiles[i].copy());
		}

		return grid;
	}
		
	private int getIndex(int i, float val)
	{
		return (int) (val * dimensions[i] / Size().get(i));
	}

	private int toIndex(int... coords)
	{
		int index = 0;
		for(int i = 0; i < order(); i++)
		{
			index *= dimensions[i];
			index += coords[i];
		}

		return index;
	}
	
	private int order()
	{
		return dimensions.length;
	}
}