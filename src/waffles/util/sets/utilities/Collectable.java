package waffles.util.sets.utilities;

import waffles.util.sets.CountableSet;

/**
 * A {@code Collectable} describes the information of an object in a {@code CountableSet}.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.0
 *
 *
 * @see CountableSet
 */
public class Collectable
{
	private CountableSet<?> set;

	/**
	 * Creates a new {@code Collectable}.
	 */
	public Collectable()
	{
		this(null);
	}
	
	/**
	 * Creates a new {@code Collectable}.
	 * 
	 * @param set  a target set
	 * 
	 * 
	 * @see CountableSet
	 */
	public Collectable(CountableSet<?> set)
	{
		this.set = set;
	}
	
	/**
	 * Changes the set of the {@cod Collectable}.
	 * 
	 * @param set  a target set
	 * 
	 * 
	 * @see CountableSet
	 */
	public void setCollection(CountableSet<?> set)
	{
		this.set = set;
	}
		
	/**
	 * Returns the top-level set of
	 * the {@code Hierarchy}.
	 * 
	 * @return  a countable set
	 * 
	 * 
	 * @see CountableSet
	 */
	public <S extends CountableSet<?>> S Set()
	{
		return (S) set;
	}
}