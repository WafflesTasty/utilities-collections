package waffles.utils.sets.arboreal.binary;

import java.util.Iterator;

import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.utilities.arboreal.iterators.binary.InOrder;
import waffles.utils.sets.utilities.arboreal.iterators.binary.PostOrder;
import waffles.utils.sets.utilities.arboreal.iterators.binary.PreOrder;

/**
 * A {@code BiArboreal} defines a generic binary {@code Arboreal}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 * 
 * @see Arboreal
 */
public interface BiArboreal extends Arboreal
{
	/**
	 * A {@code BiArboreal.Query} defines queries for a {@code BiArboreal}.
	 *
	 * @author Waffles
	 * @since May 12, 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see Arboreal
	 */
	public static interface Query<O> extends Arboreal.Query<O>
	{
		/**
		 * Returns the tree of the {@code Query}.
		 * 
		 * @return  a parent tree
		 * 
		 * 
		 * @see BiArboreal
		 */
		public abstract BiArboreal Tree();
		
		
		/**
		 * Performs pre-order iteration on the {@code BiArboreal}.
		 * 
		 * @param <N>   a nodal type
		 * @param root  a root nodal
		 * @return  a pre-order iterator
		 * 
		 * 
		 * @see Iterator
		 * @see BiNodal
		 */
		public default <N extends BiNodal> Iterator<N> PreOrder(N root)
		{
			return new PreOrder<>(root);
		}
		
		/**
		 * Performs post-order iteration on the {@code BiArboreal}.
		 * 
		 * @param <N>  a nodal type
		 * @param root  a root nodal
		 * @return  a post-order iterable
		 * 
		 * 
		 * @see Iterator
		 * @see BiNodal
		 */
		public default <N extends BiNodal> Iterator<N> PostOrder(N root)
		{
			return new PostOrder<>(root);
		}
		
		/**
		 * Performs in-order iteration on the {@code BiArboreal}.
		 * 
		 * @param <N>  a nodal type
		 * @param root  a root nodal
		 * @return  an in-order iterable
		 * 
		 * 
		 * @see Iterator
		 * @see BiNodal
		 */
		public default <N extends BiNodal> Iterator<N> InOrder(N root)
		{
			return new InOrder<>(root);
		}		
	}

		
	/**
	 * Performs pre-order iteration of the {@code BiTree}.
	 * 
	 * @param <B>  a node type
	 * @return  a pre-order iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BiNodal
	 */
	public default <B extends BiNodal> Iterable<B> preorder()
	{
		return () -> Query().PreOrder((B) Root());
	}
	
	/**
	 * Performs post-order iteration of the {@code BiTree}.
	 * 
	 * @param <B>  a node type
	 * @return  a post-order iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BiNodal
	 */
	public default <B extends BiNodal> Iterable<B> postorder()
	{
		return () -> Query().PostOrder((B) Root());
	}
	
	/**
	 * Performs in-order iteration of the {@code BiTree}.
	 * 
	 * @param <B>  a node type
	 * @return  an in-order iterable
	 * 
	 * 
	 * @see Iterable
	 * @see BiNodal
	 */
	public default <B extends BiNodal> Iterable<B> inorder()
	{
		return () -> Query().InOrder((B) Root());
	}

	
	@Override
	public abstract Query<?> Query();

	@Override
	public abstract BiNodal Root();
}