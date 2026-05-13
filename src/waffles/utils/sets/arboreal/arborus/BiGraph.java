package waffles.utils.sets.arboreal.arborus;

import waffles.utils.sets.arboreal.binary.BiArboreal;
import waffles.utils.sets.arboreal.binary.BiNodal;

/**
 * A {@code BiGraph} implements a basic binary {@code Graph}.
 *
 * @author Waffles
 * @since May 10, 2026
 * @version 1.1
 *
 *
 * @param <N>  a nodal type
 * @see BiArboreal
 * @see Graph
 */
public class BiGraph<N extends BiNodal> extends Graph<N> implements BiArboreal
{
	/**
	 * A {@code BiGraph.Query} defines queries for a {@code BiGraph}.
	 *
	 * @author Waffles
	 * @since May 12, 2026
	 * @version 1.1
	 *
	 *
	 * @param <N>  a nodal type
	 * @see BiArboreal
	 * @see Arborus
	 */
	@FunctionalInterface
	public static interface Query<N extends BiNodal> extends Arborus.Query<N>, BiArboreal.Query<N>
	{
		@Override
		public abstract BiGraph<N> Tree();
	}

	
	@Override
	public Query<N> Query()
	{
		return () -> this;
	}
	
	@Override
	public BiNodal Root()
	{
		return (BiNodal) super.Root();
	}
}