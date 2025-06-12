package waffles.utils.sets.indexed;

import waffles.utils.sets.indexed.mutable.ArrayIndex;
import waffles.utils.sets.utilities.coordinates.indexed.Indexed;
import waffles.utils.tools.primitives.Array;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Sentinel} defines a relative buffer in an {@code IndexedSet}.
 * A {@code Sentinel} of length {@code l} maintains a buffer of size 2 * l + 1
 * in all dimensions. Retrieving values happens in a relative coordinate system
 * with the origin pointing to the current coordinates of the {@code Sentinel}.
 * Every time {{@link #get(int...)} fetches a value, it checks the buffer
 * for a loaded value before fetching the value from the parent.
 *
 * @author Waffles
 * @since 12 Jun 2025
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see IndexedSet
 * @see Indexed
 */
public class Sentinel<O> implements Indexed, IndexedSet<O>
{
	private int[] offset;
	private ArrayIndex<O> buffer;
	private IndexedSet<O> parent;

	/**
	 * Creates a new {@code Sentinel}.
	 * 
	 * @param p  a parent index
	 * @param l  a buffer length
	 * @param d  a buffer dimension
	 */
	public Sentinel(IndexedSet<O> p, int l, int d)
	{
		parent = p;
		
		int len = 2 * l + 1;
		int[] dims = new int[d];
		dims = Array.fill.in(dims, len);
		buffer = new ArrayIndex<>(dims);
		offset = parent.Dimensions();
		for(int i = 0; i < d; i++)
		{
			offset[i] /= 2;
		}
	}
	
	/**
	 * Returns the parent of the {@code Sentinel}.
	 * 
	 * @return  a parent set
	 * 
	 * 
	 * @see IndexedSet
	 */
	public IndexedSet<O> Parent()
	{
		return parent;
	}
	
	/**
	 * Returns the length of the {@code Sentinel}.
	 * 
	 * @return  a buffer length
	 */
	public int Length()
	{
		return (buffer.Dimensions()[0] - 1) / 2;
	}

	
	/**
	 * Returns a single value from the {@code Sentinel}.
	 * Coordinates are assumed to be relative to the index
	 * of the {@code Sentinel}, i.e. the zero coordinate
	 * returns the center of the {@code Sentinel}.
	 * 
	 * @param crd  an index coordinate
	 * @return     an index value
	 */
	@Override
	public O get(int... crd)
	{		
		int len = Length();
		boolean isBuffered = true;
		// First, build a buffer coordinate.
		int[] iBuffer = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			iBuffer[i] = crd[i] + len;
			int l = Integers.abs(crd[i]);
			// If the coordinate is out of bounds...
			if(len < l)
			{
				// ...the value is not buffered.
				isBuffered = false;
			}
		}
		
		O value = null;
		if(isBuffered)
		{
			// Fetch a value from the buffer.
			value = buffer.get(iBuffer);
			// If a value was found...
			if(value != null)
			{
				// ...return it.
				return value;
			}
		}
		
		// Otherwise, build a parent coordinate.
		int[] iParent = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			iParent[i] = Coordinates()[i] + iBuffer[i];
		}
		
		// Fetch a value from the parent.
		value = parent.get(iParent);
		if(isBuffered)
		{
			// Buffer the retrieved value.
			buffer.put(value, iBuffer);
		}

		return value;
	}

	@Override
	public void moveTo(int... off)
	{
		offset = off;
	}
	
	@Override
	public int[] Coordinates()
	{
		return offset;
	}
	
	@Override
	public int Order()
	{
		return Dimensions().length;
	}
}