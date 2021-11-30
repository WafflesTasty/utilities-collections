package zeno.util.coll.indices.enums;

import zeno.util.coll.trees.binary.BiNode;
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
	
	
	private E value;
	private Cut cut;
	
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
	 * Changes the value of the {@code BENode}.
	 * 
	 * @param val  an enum value
	 */
	public void setValue(E val)
	{
		value = val;
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


		BENode<E> lChild = new BENode<>(Tree(), cMin, lMax);
		BENode<E> rChild = new BENode<>(Tree(), rMin, cMax);
		
		lChild.setValue(value);
		rChild.setValue(value);
		
		setLChild(lChild);
		setRChild(rChild);
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
		return value;
	}


	@Override
	protected BEIndex<E, ?> Tree()
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