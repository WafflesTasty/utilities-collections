package waffles.utils.sets.utilities.rooted;

import waffles.utils.sets.Set;
import waffles.utils.sets.queues.Deque;
import waffles.utils.sets.queues.wrapper.JavaDeque;

/**
 * A {@code Hierarchy} defines a parent {@code Hierarchical}.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.1
 *
 * 
 * @see Hierarchical
 * @see Collector
 */
public class Hierarchy extends Collector implements Hierarchical
{
	private Hierarchical parent;
	
	/**
	 * Creates a new {@code Hierarchy}.
	 * 
	 * @param h  a source object
	 * 
	 * 
	 * @see Hierarchical
	 */
	public Hierarchy(Hierarchical h)
	{
		super(null, h);
	}
		
	/**
	 * Creates a new {@code Hierarchy}.
	 * 
	 * @param s  a parent set
	 * @param h  a source object
	 * 
	 * 
	 * @see Hierarchical
	 * @see Set
	 */
	public Hierarchy(Set s, Hierarchical h)
	{
		super(s, h);
	}
	
	/**
	 * Creates a new {@code Hierarchy}.
	 * 
	 * @param s  a parent set
	 * 
	 * 
	 * @see Set
	 */
	public Hierarchy(Set s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code Hierarchy}.
	 */
	public Hierarchy()
	{
		super();
	}

	
	/**
	 * Changes the parent of the {@code Hierarchy}.
	 * 
	 * @param h  a parent object
	 * 
	 * 
	 * @see Hierarchical
	 */
	public void setParent(Hierarchical h)
	{
		parent = h;
	}
	
	/**
	 * Returns a branch for this {@code Hierarchy}.
	 * A branch iterates towards this object starting
	 * from the root of the hierarchy.
	 * 
	 * @return  a branch queue
	 * 
	 * 
	 * @see Deque
	 */
	public <P extends Hierarchy> Deque<P> Branch()
	{
		Deque<P> queue = new JavaDeque<>();

		P node = (P) Root();
		while(node != null)
		{
			queue.pushFirst(node);
			node = (P) node.Parent();
		}
		
		return queue;
	}
	
	/**
	 * Checks if this {@code Hierarchy} contains an
	 * object. It iterates over each parent of the object
	 * in turn and checks object equality.
	 * 
	 * @param h  a target object
	 * @return  {@code true} if h is a grandchild
	 * 
	 * 
	 * @see Hierarchical
	 */
	public boolean contains(Hierarchical h)
	{
		if(this == h.Arch())
		{
			return true;
		}

		Hierarchical p = h.Arch().Parent();
		if(p != null)
		{
			return contains(p);
		}
		
		return false;
	}


	/**
	 * Returns the parent of the {@code Hierarchy}.
	 * 
	 * @return  a hierarchical parent
	 * 
	 * 
	 * @see Hierarchical
	 */
	public Hierarchical Parent()
	{
		return parent;
	}
	
	/**
	 * Returns the root of the {@code Hierarchy}.
	 * 
	 * @return  a hierarchical root
	 * 
	 * @see Hierarchical
	 */
	public Hierarchical Root()
	{
		if(Parent() != null)
		{
			return Parent().Arch().Root();
		}
		
		return Delegate();
	}
	
	
	/**
	 * Checks if this {@code Hierarchy} is a root,
	 * i.e. an object without a parent object.
	 * 
	 * @return  {@code true} if a root
	 */
	public boolean isRoot()
	{
		return Parent() == null;
	}
	
	/**
	 * Returns the depth of the {@code Hierarchy}.
	 * </br> This indicates the height of the highest parent object.
	 * 
	 * @return  a hierarchy depth
	 */
	public int Depth()
	{
		if(Parent() != null)
		{
			return Parent().Arch().Depth() + 1;
		}
		
		return 0;
	}

	
	@Override
	public Hierarchical Delegate()
	{
		return (Hierarchical) super.Delegate();
	}
	
	@Override
	public Hierarchy Arch()
	{
		return this;
	}
}