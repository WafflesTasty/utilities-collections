package zeno.util.coll.spatial.solids;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.coll.Space;
import zeno.util.geom.shapes.ICuboid;
import zeno.util.geom.tools.bounds.Bounded3D;

/**
 * The {@code Space3D} interface defines queries handled by 3D spatial collections.
 * 
 * @since Mar 29, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects in the space
 * @see Bounded3D
 * @see Space
 */
public interface Space3D<O extends Bounded3D> extends Space<O>
{
	/**
	 * Queries the {@code Space} in a specified cuboid.
	 * 
	 * @param xmin  the minimum x-co�rdinate
	 * @param ymin  the minimum y-co�rdinate
	 * @param zmin  the minimum z-co�rdinate
	 * @param xmax  the maximum x-co�rdinate
	 * @param ymax  the maximum y-co�rdinate
	 * @param zmax  the maximum z-co�rdinate
	 * @return  a list of possible results
	 * @see Iterable
	 */
	public abstract Iterable<O> query(float xmin, float ymin, float zmin, float xmax, float ymax, float zmax);
	
	/**
	 * Queries the {@code Space} at a specified point.
	 * 
	 * @param x  the point's x-co�rdinate
	 * @param y  the point's y-co�rdinate
	 * @param z  the point's z-co�rdinate
	 * @return  a list of possible results
	 * @see Iterable
	 */
	public abstract Iterable<O> query(float x, float y, float z);
	
	
	@Override
	public default Iterable<O> query(ICuboid c)
	{
		Vector min = c.Minimum();
		Vector max = c.Maximum();
		
		return query
		(
			min.get(0), min.get(1), min.get(2),
			max.get(0), max.get(1), max.get(2)
		);
	}
	
	@Override
	public default Iterable<O> query(Vector v)
	{
		return query(v.get(0), v.get(1), v.get(2));
	}
}