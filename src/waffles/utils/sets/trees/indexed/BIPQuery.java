package waffles.utils.sets.trees.indexed;

/**
 * A {@code BIPQuery} contains information when querying a {@code BIPTree} with an index array.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.0
 */
public class BIPQuery
{
	/**
	 * An {@code Axis} is generated to define the optimal splitting axis in a {@code BIPQuery}. 
	 *
	 * @author Waffles
	 * @since 08 Aug 2023
	 * @version 1.0
	 */
	public static class Axis
	{
		private int index, value;
		
		/**
		 * Creates a new {@code Axis}.
		 * 
		 * @param index  an axis index
		 * @param value  an axis value
		 */
		public Axis(int index, int value)
		{
			this.index = index;
			this.value = value;
		}
		
		/**
		 * Returns the {@code Axis} index.
		 * 
		 * @return  an axis index
		 */
		public int Index()
		{
			return index;
		}
		
		/**
		 * Returns the {@code Axis} value.
		 * 
		 * @return  an axis value
		 */
		public int Value()
		{
			return value;
		}
	}
	
	
	private BIPNode node;
	private int[] min, max;
	
	/**
	 * Creates a new {@code BIPQuery}.
	 * 
	 * @param node  a target node
	 * @param min   a minimum index
	 * @param max   a maximum index
	 * 
	 * 
	 * @see BIPNode
	 */
	public BIPQuery(BIPNode node, int[] min, int[] max)
	{
		this.node = node;
		this.min = min;
		this.max = max;
	}

	
	/**
	 * Returns the optimal axis of the {@code BIPQuery}.
	 * 
	 * @return  an optimal axis
	 */
	public Axis OptimalAxis()
	{
		int[] cMin = node.Minimum();
		int[] cMax = node.Maximum();
		
		// Find the largest difference of minimums.
		int kMin = -1, dMin = 0;
		for(int k = 0; k < cMin.length; k++)
		{
			if(dMin < min[k] - cMin[k])
			{
				dMin = min[k] - cMin[k];
				kMin = k;
			}
		}

		// Find the largest difference of maximums.
		int kMax = -1, dMax = 0;
		for(int k = 0; k < cMax.length; k++)
		{
			if(dMax < cMax[k] - max[k])
			{
				dMax = cMax[k] - max[k];
				kMax = k;
			}
		}
		
					
		// Compute the new boundary indices.		
		if(0 <= kMin && dMax <= dMin)
		{
			return new Axis(kMin, min[kMin] - 1);
		}
		if(0 <= kMax && dMin <= dMax)
		{
			return new Axis(kMax, max[kMax] + 0);
		}
		
		return null;
	}

	/**
	 * Checks if the {@code BIPQuery} is a cover.
	 * </br> This is true if the queried index covers the node.
	 * 
	 * @return  a query cover state
	 */
	public boolean isCover()
	{
		int[] cMin = node.Minimum();
		int[] cMax = node.Maximum();
		
		for(int i = 0; i < cMin.length; i++)
		{
			if(cMin[i] < min[i] || max[i] < cMax[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Checks if the {@code BIPQuery} is empty.
	 * </br> This is true if the queried index is outside the node.
	 * 
	 * @return  a query emptiness state
	 */
	public boolean isEmpty()
	{
		int[] cMin = node.Minimum();
		int[] cMax = node.Maximum();
		
		for(int i = 0; i < cMin.length; i++)
		{
			if(max[i] < cMin[i] || cMax[i] < min[i])
			{
				return true;
			}
		}
		
		return false;
	}
}