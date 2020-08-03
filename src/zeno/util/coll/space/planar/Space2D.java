package zeno.util.coll.space.planar;

import zeno.util.coll.space.Space;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.IBounded2D;

/**

 * The {@code Space2D} interface defines a collection that handles 2D spatial queries.
 *
 * @author Zeno
 * @since 22 Jul 2020
 * @version 1.0
 *
 * @param <O>  a space object type
 * @see IBounded2D
 */
public interface Space2D<O extends IBounded2D> extends Space<O>, IBounded2D
{
	/**
	 * Queries the {@code Space2D} in a specified point.
	 * 
	 * @param x  an x-coördinate
	 * @param y  an y-coördinate
	 * @return  a set of possible results
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<O> query(float x, float y)
	{
		return query(new Point(x, y, 1f));
	}
}