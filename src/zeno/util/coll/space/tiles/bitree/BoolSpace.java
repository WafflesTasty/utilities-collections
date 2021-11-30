package zeno.util.coll.space.tiles.bitree;

import zeno.util.coll.utilities.enums.Binary;

/**
 * The {@code BoolSpace} class defines an enum space with two possible values.
 * For ease of use, the space considers {@code Binary.ZERO} as equal to null under the hood.
 * For the sake of consistency, any null value is returned as {@code Binary.ZERO} as well.
 *
 * @author Waffles
 * @since 27 Nov 2021
 * @version 1.0
 * 
 * 
 * @see BESpace
 * @see Binary
 */
public class BoolSpace extends BESpace<Binary>
{
	/**
	 * Creates a new {@code BoolSpace}.
	 * 
	 * @param dim  an index dimension
	 */
	public BoolSpace(int... dim)
	{
		super(dim);
	}
	
	
	@Override
	public Binary put(Binary val, int... coords)
	{
		if(val != Binary.ZERO)
		{
			return super.put(val, coords);
		}
		
		return super.put(null, coords);
	}
	
	@Override
	public Binary get(int... coords)
	{
		Binary b = super.get(coords);
		if(b == null) return Binary.ZERO;
		return super.get(coords);
	}
}
