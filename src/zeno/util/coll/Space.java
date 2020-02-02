package zeno.util.coll;

import zeno.util.algebra.linear.vector.Vector;
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
 * @param <O>  an object type to contain
 * @see Collection
 * @see IBounded
 */
public interface Space<O extends IBounded> extends Collection<O>, IBounded
{
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
	 * @param v  a point to query
	 * @return  a set of possible results
	 * 
	 * 
	 * @see Iterable
	 * @see Vector
	 */
	public abstract Iterable<O> query(Vector v);
}