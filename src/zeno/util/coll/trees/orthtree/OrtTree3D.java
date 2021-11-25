package zeno.util.coll.trees.orthtree;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.coll.space.Space3D;
import zeno.util.geom.collidables.IGeometrical3D;
import zeno.util.geom.collidables.geometry.spatial.Cuboid;

/**
 * The {@code OrtTree3D} class provides an {@code OrtTree} implementation in 3D space.
 *
 * @author Zeno
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see IGeometrical3D
 * @see OrtTree
 * @see Space3D
 */
public class OrtTree3D<O extends IGeometrical3D> extends OrtTree<O> implements Space3D<O>
{	
	/**
	 * Creates a new {@code OrtTree3D}.
	 * 
	 * @param x  a bounds center x-coordinate
	 * @param y  a bounds center y-coordinate
	 * @param z  a bounds center z-coordinate
	 * @param w  a bounds width
	 * @param h  a bounds height
	 * @param d  a bounds depth
	 */
	public OrtTree3D(float x, float y, float z, float w, float h, float d)
	{
		this(new Cuboid(x, y, z, w, h, d));
	}
	
	/**
	 * Creates a new {@code OrtTree3D}.
	 * 
	 * @param c  a bounds center
	 * @param s  a bounds size
	 * 
	 * 
	 * @see Vector3
	 */
	public OrtTree3D(Vector3 c, Vector3 s)
	{
		this(new Cuboid(c, s));
	}
	
	/**
	 * Creates a new {@code OrtTree3D}.
	 * 
	 * @param b  a bounds cuboid
	 * 
	 * 
	 * @see Cuboid
	 */
	public OrtTree3D(Cuboid b)
	{
		super(b);
	}
	
	
	@Override
	public Cuboid Bounds()
	{
		return (Cuboid) super.Bounds();
	}
}