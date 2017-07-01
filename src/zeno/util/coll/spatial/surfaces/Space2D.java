package zeno.util.coll.spatial.surfaces;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.coll.Space;
import zeno.util.geom.shapes.ICuboid;
import zeno.util.geom.tools.bounds.Bounded2D;

/**
 * The {@code Space2D} interface defines queries handled by 2D spatial collections.
 * 
 * @since Mar 29, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects in the space
 * @see Bounded2D
 * @see Space
 */
public interface Space2D<O extends Bounded2D> extends Bounded2D, Space<O>
{
	/**
	 * Queries the {@code Space} in a specified cuboid.
	 * 
	 * @param x  the cuboid's x-coördinate
	 * @param y  the cuboid's y-coördinate
	 * @param w  the cuboid's  width
	 * @param h  the cuboid's height
	 * @return  a list of possible results
	 * @see Iterable
	 */
	public abstract Iterable<O> query(float x, float y, float w, float h);
	
	/**
	 * Queries the {@code Space} at a specified point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @return  a list of possible results
	 * @see Iterable
	 */
	public abstract Iterable<O> query(float x, float y);
	
	
	@Override
	public default Iterable<O> query(ICuboid c)
	{
		Vector cent = c.Center();
		Vector size = c.Size();
		
		return query
		(
			cent.get(0), cent.get(1),
			size.get(0), size.get(1)
		);
	}
	
	@Override
	public default Iterable<O> query(Vector v)
	{
		return query(v.get(0), v.get(1));
	}
}