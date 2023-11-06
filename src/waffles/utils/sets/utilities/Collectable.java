package waffles.utils.sets.utilities;

import waffles.utils.sets.Set;

/**
 * A {@code Collectable} describes the information of an object in a {@code CountableSet}.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.1
 */
public class Collectable
{
	private Set set;

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
	 * @param s  a target set
	 * 
	 * 
	 * @see Set
	 */
	public Collectable(Set s)
	{
		set = s;
	}
	
	/**
	 * Changes the set of the {@cod Collectable}.
	 * 
	 * @param s  a target set
	 * 
	 * 
	 * @see Set
	 */
	public void setCollection(Set s)
	{
		set = s;
	}
		
	/**
	 * Returns the top-level set of
	 * the {@code Hierarchy}.
	 * 
	 * @return  an object set
	 */
	public <S extends Object> S Set()
	{
		return (S) set;
	}
}