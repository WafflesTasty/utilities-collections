package waffles.utils.sets.utilities.rooted;

import waffles.utils.sets.Set;
import waffles.utils.tools.patterns.properties.values.Decorator;

/**
 * A {@code Collector} defines a parent {@code Set}.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.1
 * 
 * 
 * @see Decorator
 */
public class Collector implements Collectible, Decorator
{
	private Set set;
	private Collectible col;
	
	/**
	 * Creates a new {@code Collector}.
	 * 
	 * @param s  a parent set
	 * 
	 * 
	 * @see Set
	 */
	public Collector(Set s)
	{
		this(s, null);
	}
	
	/**
	 * Creates a new {@code Collector}.
	 * 
	 * @param s  a parent set
	 * @param c  a source object
	 * 
	 * 
	 * @see Collectible
	 * @see Set
	 */
	public Collector(Set s, Collectible c)
	{
		col = c;
		set = s;
	}

	/**
	 * Creates a new {@code Collector}.
	 */
	public Collector()
	{
		this(null);
	}
	
	
	/**
	 * Changes the set of the {@cod Collector}.
	 * 
	 * @param s  a parent set
	 * 
	 * 
	 * @see Set
	 */
	public void setContainer(Set s)
	{
		set = s;
	}
			
	/**
	 * Returns the set of the {@code Collector}.
	 * 
	 * @return  a parent set
	 */
	public <S extends Object> S Set()
	{
		return (S) set;
	}

		
	@Override
	public Collectible Delegate()
	{
		return col;
	}
	
	@Override
	public Collector Arch()
	{
		return this;
	}
}