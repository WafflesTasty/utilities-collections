package zeno.util.coll.trees.orthtree;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.coll.space.Space2D;
import zeno.util.geom.collidables.IGeometrical2D;
import zeno.util.geom.collidables.geometry.planar.Rectangle;

/**
 * The {@code OrtTree2D} class provides an {@code OrtTree} implementation in 2D space.
 *
 * @author Zeno
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see IGeometrical2D
 * @see OrtTree
 * @see Space2D
 */
public class OrtTree2D<O extends IGeometrical2D> extends OrtTree<O> implements Space2D<O>
{	
	/**
	 * Creates a new {@code OrtTree2D}.
	 * 
	 * @param x  a bounds center x-coordinate
	 * @param y  a bounds center y-coordinate
	 * @param w  a bounds width
	 * @param h  a bounds height
	 */
	public OrtTree2D(float x, float y, float w, float h)
	{
		this(new Rectangle(x, y, w, h));
	}
	
	/**
	 * Creates a new {@code OrtTree2D}.
	 * 
	 * @param c  a bounds center
	 * @param s  a bounds size
	 * 
	 * 
	 * @see Vector2
	 */
	public OrtTree2D(Vector2 c, Vector2 s)
	{
		this(new Rectangle(c, s));
	}
	
	/**
	 * Creates a new {@code OrtTree2D}.
	 * 
	 * @param b  a bounds rectangle
	 * 
	 * 
	 * @see Rectangle
	 */
	public OrtTree2D(Rectangle b)
	{
		super(b);
	}

	
	@Override
	public Rectangle Bounds()
	{
		return (Rectangle) super.Bounds();
	}
}