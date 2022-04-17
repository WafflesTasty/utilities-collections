package zeno.util.coll.space.tiles.enums;

import zeno.util.coll.utilities.enums.Binary;

/**
 * The {@code BoolTree} class defines an enum space with two possible values.
 * For ease of use, the space considers {@code Binary.ZERO} as equal to null under the hood.
 * For the sake of consistency, any null value is returned as {@code Binary.ZERO} as well.
 *
 * @author Waffles
 * @since 27 Nov 2021
 * @version 1.0
 * 
 * 
 * @see BETree
 * @see Binary
 */
public class BoolTree extends BETree<Binary>
{
	/**
	 * Creates a new {@code BoolTree}.
	 * 
	 * @param tSize  a grid tile size
	 * @param dim    an index dimension
	 */
	public BoolTree(float tSize, int... dim)
	{
		super(tSize, dim);
	}
	
	
	@Override
	public void put(Binary val, int[] min, int[] max)
	{
		if(val == Binary.ZERO)
			super.remove(min, max);
		else		
			super.put(val, min, max);
	}
	
	@Override
	public Binary put(Binary val, int... coords)
	{
		if(val != Binary.ZERO)
		{
			return super.put(val, coords);
		}
		
		return super.remove(coords);
	}
	
	@Override
	public Binary get(int... coords)
	{
		Binary b = super.get(coords);
		if(b != null) return b;
		return Binary.ZERO;
	}
}
