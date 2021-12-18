package zeno.util.coll.indices.enums;

import zeno.util.coll.trees.binary.BiNode;
import zeno.util.tools.Integers;
import zeno.util.tools.helper.Array;

/**
 * The {@code BENode} class defines a single node in a {@code BEIndex}.
 *
 * @author Waffles
 * @since 25 Nov 2021
 * @version 1.1
 *
 *
 * @param <E>  an enum type
 * @see BiNode
 * @see Enum
 */
public class BENode<E extends Enum<E>> extends BiNode
{
	/**
	 * The {@code Collision} class provides info on intersection of a range with a {@code BENode}.
	 *
	 * @author Waffles
	 * @since 07 Dec 2021
	 * @version 1.0
	 * 
	 * 
	 * @param <E>  an enum type
	 * @see BENode
	 * @see Enum
	 */
	public static class Collision<E extends Enum<E>>
	{
		private boolean isCover, isEmpty;
		private int[] cMin, cMax, iMin, iMax;
		
		/**
		 * Creates a new {@code Collision}.
		 * 
		 * @param src  a source node
		 * @param min  a range minimum
		 * @param max  a range maximum
		 */
		public Collision(BENode<E> src, int[] min, int[] max)
		{
			isCover = true;
			
			cMin = src.Minimum();
			cMax = src.Maximum();
			
			iMin = new int[cMin.length];
			iMax = new int[cMax.length];
			
			for(int i = 0; i < cMin.length; i++)
			{
				if(max[i] < cMin[i] || cMax[i] < min[i])
				{
					isEmpty = true;
				}
				
				if(cMin[i] < min[i] || max[i] < cMax[i])
				{
					isCover = false;
				}
				
				iMin[i] = Integers.max(min[i], cMin[i]);
				iMax[i] = Integers.min(max[i], cMax[i]);
			}
		}

		
		/**
		 * Checks if the range covers the entire node.
		 * 
		 * @return  {@code true} if the node is covered
		 */
		public boolean isCover()
		{
			return isCover;
		}
		
		/**
		 * Checks if the range is disjoint from the node.
		 * 
		 * @return  {@code true} if intersection is empty
		 */
		public boolean isEmpty()
		{
			return isEmpty;
		}
		
		/**
		 * Returns the minimum of the intersection.
		 * 
		 * @return  an intersection minimum
		 */
		public int[] Minimum()
		{
			return iMin;
		}
		
		/**
		 * Returns the maximum of the intersection.
		 * 
		 * @return  an intersection maximum
		 */
		public int[] Maximum()
		{
			return iMin;
		}
	}
	
	/**
	 * The {@code Cut} class defines a plane that splits a {@code BENode}.
	 *
	 * @author Waffles
	 * @since 25 Nov 2021
	 * @version 1.1
	 */
	public class Cut
	{
		private Integer dim;
		
		/**
		 * Returns the dimension of the {@code Cut}.
		 * 
		 * @return  a cut dimension
		 */
		public int Dimension()
		{
			if(dim == null)
			{
				dim = 0; int dMax = 0;
				for(int i = 0; i < cMin.length; i++)
				{
					int diff = cMax[i] - cMin[i];
					if(dMax < diff)
					{
						dMax = diff;
						dim = i;
					}
				}
			}
			
			return dim;
		}
		
		/**
		 * Returns the value of the {@code Cut}.
		 * 
		 * @return  a cut value
		 */
		public float Value()
		{
			int dMin = cMin[Dimension()];
			int dMax = cMax[Dimension()];

			return dMin + (dMax - dMin) / 2 + 0.5f;
		}
	}
	
	
	private Cut cut;
	private Enum<E>[] values;
	
	private int[] cMin;
	private int[] cMax;
	
	/**
	 * Creates a new {@code BENode}.
	 * 
	 * @param tree  a parent tree
	 * @param min  a minimum index
	 * @param max  a maximum index
	 * 
	 * 
	 * @see BEIndex
	 */
	public BENode(BEIndex<E, ?> tree, int[] min, int[] max)
	{
		super(tree);
		cut = new Cut();
		cMin = min; cMax = max;
		values = new Enum[]{null};
	}

	/**
	 * Intersects a range with the {@code BENode}.
	 * 
	 * @param min  a range minimum
	 * @param max  a range maximum
	 * @return  a collision
	 * 
	 * 
	 * @see Collision
	 */
	public Collision<E> intersect(int[] min, int[] max)
	{
		return new Collision<>(this, min, max);
	}
	
	/**
	 * Returns a target child of the {@code BENode}.
	 * 
	 * @param coords  a tile coordinate
	 * @return  a child node
	 * 
	 * 
	 * @see BENode
	 */
	public BENode<E> Child(int... coords)
	{
		if(coords[cut.Dimension()] < cut.Value())
			return LChild();
		return RChild();
	}

	/**
	 * Returns the cut of the {@code BENode}.
	 * 
	 * @return  an index cut
	 * 
	 * 
	 * @see Cut
	 */
	public Cut Cut()
	{
		return cut;
	}

	
	/**
	 * Adds a value to the {@code BENode}.
	 * 
	 * @param val  an enum value
	 */
	public void addValue(E val)
	{
		if(!hasValue(val))
		{
			values = Array.add.to(values, val);
		}
	}
	
	/**
	 * Checks a value in the {@code BENode}.
	 * 
	 * @param val  an enum value
	 * @return  {@code true} if the node contains the value
	 */
	public boolean hasValue(E val)
	{
		for(Enum<E> e : values)
		{
			if(e == val)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Changes the value of the {@code BENode}.
	 * 
	 * @param val  an enum value
	 */
	public void setValue(E val)
	{
		values = new Enum[]{val};
	}
		
	/**
	 * Splits the {@code BENode} into two children.
	 */
	public void split()
	{
		int dim = cut.Dimension();
		
		int[] lMax = Array.copy.of(cMax);
		int[] rMin = Array.copy.of(cMin);
		
		int diff = cMax[dim] - cMin[dim];
		lMax[dim] = cMin[dim] + diff / 2;
		rMin[dim] = lMax[dim] + 1;


		BENode<E> lChild = Tree().create(cMin, lMax);
		BENode<E> rChild = Tree().create(rMin, cMax);
		
		lChild.setValue(Value());
		rChild.setValue(Value());
		
		setLChild(lChild);
		setRChild(rChild);
	}
	
	
	/**
	 * Checks the size of the {@code BENode}.
	 * </br> A node is a tile iff it contains exactly one coordinate.
	 * 
	 * @return  {@code true} if the node is a tile
	 */
	public boolean isTile()
	{
		return Array.equals.of(cMin, cMax);
	}
	
	/**
	 * Returns the minimum of the {@code BENode}.
	 * 
	 * @return  a minimum coordinate
	 */
	public int[] Minimum()
	{
		return cMin;
	}
	
	/**
	 * Returns the maximum of the {@code BENode}.
	 * 
	 * @return  a maximum coordinate
	 */
	public int[] Maximum()
	{
		return cMax;
	}
		
	/**
	 * Returns the value of the {@code BENode}.
	 * 
	 * @return  an enum value
	 */
	public E Value()
	{
		if(values.length > 0)
			return (E) values[0];
		return null;
	}


	@Override
	public BEIndex<E, ?> Tree()
	{
		return (BEIndex<E, ?>) super.Tree();
	}
	
	@Override
	public BENode<E> Sibling()
	{
		return (BENode<E>) super.Sibling();
	}
	
	@Override
	public BENode<E> Parent()
	{
		return (BENode<E>) super.Parent();
	}
	
	@Override
	public BENode<E> LChild()
	{
		return (BENode<E>) super.LChild();
	}
	
	@Override
	public BENode<E> RChild()
	{
		return (BENode<E>) super.RChild();
	}
}