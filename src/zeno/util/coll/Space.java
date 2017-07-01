package zeno.util.coll;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.shapes.ICuboid;
import zeno.util.geom.tools.bounds.Bounded;

/**
 * The {@code Space} interface defines queries handled by spatial collections.
 * 
 * @param <O>  the type of objects in the space
 *  
 * @since Mar 29, 2017
 * @author Zeno
 *
 * @see Iterable
 * @see Bounded
 */
public interface Space<O extends Bounded> extends Bounded, Iterable<O>
{
	/**
	 * Queries the {@code Space} in a specified cuboid.
	 * 
	 * @param c  a cuboid to query
	 * @return  a list of possible results
	 * @see Iterable
	 * @see ICuboid
	 */
	public abstract Iterable<O> query(ICuboid c);
	
	/**
	 * Queries the {@code Space} at a specified point.
	 * 
	 * @param v  a point to query
	 * @return  a list of possible results
	 * @see Iterable
	 * @see Vector
	 */
	public abstract Iterable<O> query(Vector v);
}