package waffles.utils.sets.arboreal.data;

import java.util.Iterator;

import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.utilities.arboreal.iterators.data.DataIterator;
import waffles.utils.sets.utilities.arboreal.iterators.data.PairIterator;
import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.sets.utilities.keymaps.PairQuery;
import waffles.utils.sets.utilities.keymaps.PairQueryable;

/**
 * A {@code DataBoreal} is a tree-like structure designed to iterate object sets.
 * Each {@code DataNodal} in the tree defines its own data set, while the
 * tree itself defines a variety of useful iterators on top.
 * 
 * @author Waffles
 * @since May 10, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see PairQueryable
 * @see Arboreal
 */
public interface DataBoreal<O> extends Arboreal, PairQueryable<O>
{
	/**
	 * A {@code DataBoreal.Query} defines a {@code Query} for a {@code DataBoreal}.
	 * The {@link #Pairs()} iterator is designed to return every unique pair
	 * of objects that live in the same parent {@code DataNodal}.
	 *
	 * @author Waffles
	 * @since May 10, 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see PairQuery
	 * @see Arboreal
	 */
	@FunctionalInterface
	public static interface Query<O> extends Arboreal.Query<O>, PairQuery<O>
	{
		/**
		 * Returns the target tree of the {@code Query}.
		 * 
		 * @return  a data tree
		 * 
		 * 
		 * @see DataBoreal
		 */
		public abstract DataBoreal<O> Tree();


		@Override
		public default Iterator<Pair<O, O>> Pairs()
		{
			return new PairIterator<>(Tree().Root());
		}
		
		@Override
		public default Iterator<O> All()
		{
			return new DataIterator<>(Tree().Root());
		}
	}
	
	
	@Override
	public abstract DataNodal<O> Root();
	
	@Override
	public abstract Query<O> Query();
}
