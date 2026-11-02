package waffles.utils.sets.utilities.indexed.iterators.diamond;

import java.util.Iterator;

import waffles.utils.sets.utilities.indexed.coords.Coordinated;
import waffles.utils.tools.collections.iterators.EmptyIterator;
import waffles.utils.tools.primitives.Array;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code DiamondIndexND} iterates over an n-dimensional diamond in an {@code IndexedSet}.
 *
 * @author Waffles
 * @since 07 Nov 2024
 * @version 1.1
 *
 * 
 * @see Iterator
 */
public class DiamondIndexND implements Iterator<int[]>
{
	private int[] center, index;
	private int rad, next, order;
	private Iterator<int[]> dmd;
	
	/**
	 * Creates a new {@code DiamondIndexND}.
	 * 
	 * @param c  a center object
	 * @param r  a diamond radius
	 * 
	 * 
	 * @see Coordinated
	 */
	public DiamondIndexND(Coordinated c, int r)
	{
		this(c.Coords(), r);
	}
	
	/**
	 * Creates a new {@code DiamondIndexND}.
	 * 
	 * @param c  a center object
	 * @param r  a diamond radius
	 * @param o  a diamond order
	 * 
	 * 
	 * @see Coordinated
	 */
	public DiamondIndexND(Coordinated c, int r, int o)
	{	
		this(c.Coords(), r, o);
	}

	/**
	 * Creates a new {@code DiamondIndexND}.
	 * 
	 * @param c  a center index
	 * @param r  a diamond radius
	 * @param o  a diamond order
	 * 
	 * 
	 * @see Coordinated
	 */
	public DiamondIndexND(int[] c, int r, int o)
	{	
		order = o; rad = r;
		center = c; next = -r;
		index = Array.copy.of(c);
		index[order] -= rad;
		
		if(order > 3)
			dmd = new DiamondIndexND(index, 0, o - 1);
		else if(order == 3)
			dmd = new DiamondIndex2D(index, 0);
		else
			dmd = new EmptyIterator<>();
	}
	
	/**
	 * Creates a new {@code DiamondIndexND}.
	 * 
	 * @param c  a center index
	 * @param r  a diamond radius
	 */
	public DiamondIndexND(int[] c, int r)
	{
		this(c, r, c.length);
	}
	
	
	@Override
	public boolean hasNext()
	{
		return dmd.hasNext();
	}
	
	@Override
	public int[] next()
	{
		int[] idx = dmd.next();
		if(!dmd.hasNext())
		{
			next++;
			if(next <= rad)
			{
				int o = order - 1;
				int r = rad - Integers.abs(next);
				index[order] = center[order] + next;
				if(order > 3)
					dmd = new DiamondIndexND(index, r, o);
				else
					dmd = new DiamondIndex2D(index, r);
			}
		}
		
		return idx;
	}
}