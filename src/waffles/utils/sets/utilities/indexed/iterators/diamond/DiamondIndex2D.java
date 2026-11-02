package waffles.utils.sets.utilities.indexed.iterators.diamond;

import java.util.Iterator;

import waffles.utils.sets.utilities.indexed.coords.Coordinated;
import waffles.utils.tools.primitives.Array;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code DiamondIndexND} iterates over a two-dimensional diamond in an {@code IndexedSet}.
 *
 * @author Waffles
 * @since 07 Nov 2024
 * @version 1.1
 *
 * 
 * @see Iterator
 */
public class DiamondIndex2D implements Iterator<int[]>
{
	private int[] center;
	private int rad, index;
	
	/**
	 * Creates a new {@code DiamondIndex2D}.
	 * 
	 * @param c  a center object
	 * @param r  a diamond radius
	 * 
	 * 
	 * @see Coordinated
	 */
	public DiamondIndex2D(Coordinated c, int r)
	{	
		this(c.Coords(), r);
	}
	
	/**
	 * Creates a new {@code DiamondIndex2D}.
	 * 
	 * @param c  a center index
	 * @param r  a diamond radius
	 */
	public DiamondIndex2D(int[] c, int r)
	{
		index = 0;
		center = c;
		rad = r;
	}
	
	
	private int X(int i, int r)
	{
		return r - Integers.abs(2 * r - i);
	}
	
	private int Y(int i, int r)
	{
		int j = Integers.mod(i - r, 4 * r);
		return X(j, r);
	}

	@Override
	public boolean hasNext()
	{
		return index <= 4 * rad;
	}
	
	@Override
	public int[] next()
	{
		int x = X(index, rad);
		int y = Y(index, rad);
		index++;
		
		int[] next = center;
		next = Array.copy.of(next);
		
		next[0] += x;
		next[1] += y;

		return next;
	}
}