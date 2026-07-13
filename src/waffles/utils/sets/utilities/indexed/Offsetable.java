package waffles.utils.sets.utilities.indexed;

import waffles.utils.tools.patterns.properties.Immutable;

/**
 * An {@code Offsetable} provides an offset index.
 *
 * @author Waffles
 * @since Jul 13, 2026
 * @version 1.1
 *
 * 
 * @see Immutable
 */
public interface Offsetable extends Immutable
{
	/**
	 * An {@code Offsetable.Mutable} can change its own offset.
	 *
	 * @author Waffles
	 * @since Jul 13, 2026
	 * @version 1.1
	 *
	 * 
	 * @see Offsetable
	 * @see Immutable
	 */
	public static interface Mutable extends Offsetable, Immutable.Mutable
	{
		/**
		 * Changes the offset index.
		 * 
		 * @param ofs  a data offset
		 */
		public abstract void setOffset(int... ofs);
	}
	
	
	/**
	 * Returns the offset index.
	 * 
	 * @return  a data offset
	 */
	public abstract int[] Offset();
}