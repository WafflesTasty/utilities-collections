package zeno.util.coll.space;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.geometry.generic.ICuboid;

/**
 * The {@code Space} interface defines a collection that handles spatial queries.
 * 
 * @author Zeno
 * @since Mar 29, 2017
 * @version 1.0
 *
 * 
 * @param <O>  a space object type
 * @see IBounded
 */
public interface Space<O extends IBounded> extends IBounded
{
	/**
	 * Queries the {@code Space} at a specified vector.
	 * 
	 * @param v  a vector to query
	 * @return  a set of possible results
	 * 
	 * 
	 * @see Iterable
	 * @see Vector
	 */
	public default Iterable<O> query(Vector v)
	{
		return query(new Point(v, 1f));
	}
	
	/**
	 * Queries the {@code Space} in a specified cuboid.
	 * 
	 * @param c  a cuboid to query
	 * @return  a set of possible results
	 * 
	 * 
	 * @see Iterable
	 * @see ICuboid
	 */
	public abstract Iterable<O> query(ICuboid c);
	
	/**
	 * Queries the {@code Space} at a specified point.
	 * 
	 * @param p  a point to query
	 * @return  a set of possible results
	 * 
	 * 
	 * @see Iterable
	 * @see Point
	 */
	public abstract Iterable<O> query(Point p);
}