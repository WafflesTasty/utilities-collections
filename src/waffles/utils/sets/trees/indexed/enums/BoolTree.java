package waffles.utils.sets.trees.indexed.enums;

import waffles.utils.lang.math.Binary;
import waffles.utils.sets.trees.indexed.BEPTree;

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
 * @see BEPTree
 * @see Binary
 */
public class BoolTree extends BEPTree<Binary>
{
	/**
	 * Creates a new {@code BoolTree}.
	 * 
	 * @param dim    an index dimension
	 */
	public BoolTree(int... dim)
	{
		super(dim);
	}
	
	
	@Override
	public void put(Binary val, int[] min, int[] max)
	{
		switch(val)
		{
		case ONE:
			super.put(val, min, max); break;
		default:
			super.remove(min, max);
		}
	}
	
	@Override
	public Binary put(Binary val, int... coords)
	{
		switch(val)
		{
		case ONE:
			return super.put(val, coords);
		default:
			return super.remove(coords);
		}
	}
	
	@Override
	public Binary get(int... coords)
	{
		Binary val = super.get(coords);
		switch(val)
		{
		case ONE:
			return Binary.ONE;
		default:
			return Binary.ZERO;
		}
	}
}
