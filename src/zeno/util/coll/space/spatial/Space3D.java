package zeno.util.coll.space.spatial;

import zeno.util.coll.space.Space;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.IBounded3D;

/**
 * The {@code Space3D} interface defines a collection that handles 3D spatial queries.
 *
 * @author Zeno
 * @since 22 Jul 2020
 * @version 1.0
 *
 * @param <O>  a space object type
 * @see IBounded3D
 */
public interface Space3D<O extends IBounded3D> extends Space<O>, IBounded3D
{
	/**
	 * Queries the {@code Space3D} in a specified point.
	 * 
	 * @param x  an x-coördinate
	 * @param y  an y-coördinate
	 * @param z  an z-coördinate
	 * @return  a set of possible results
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<O> query(float x, float y, float z)
	{
		return query(new Point(x, y, 1f));
	}
}