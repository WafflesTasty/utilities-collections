package waffles.utils.sets.utilities.collectables;

import waffles.utils.sets.CountableSet;
import waffles.utils.sets.queues.Deque;
import waffles.utils.sets.queues.delegate.JDeque;
import waffles.utils.sets.utilities.Collectable;
import waffles.utils.tools.patterns.semantics.Decorator;

/**
 * A {@code Hierarchy} describes the information of an object with parent-child relationships.
 * Objects can define the {@code Hierarchical} interface to define their own hierarchy,
 * which admits a parent and allows for branch queues to be generated.
 *
 * @author Waffles
 * @since 08 Aug 2023
 * @version 1.0
 *
 * 
 * @see Hierarchical
 * @see Collectable
 * @see Decorator
 */
public class Hierarchy extends Collectable implements Decorator, Hierarchical
{
	private Hierarchical parent, delegate;
		
	/**
	 * Creates a new {@code Hierarchy}.
	 * 
	 * @param set  a target set
	 * @param del  a delegate object
	 * 
	 * 
	 * @see CountableSet
	 * @see Hierarchical
	 */
	public Hierarchy(CountableSet set, Hierarchical del)
	{
		super(set);	delegate = del;
	}
	
	/**
	 * Creates a new {@code Hierarchy}.
	 * 
	 * @param set  a target set
	 * 
	 * 
	 * @see CountableSet
	 */
	public Hierarchy(CountableSet set)
	{
		super(set);
	}
	
	/**
	 * Creates a new {@code Hierarchy}.
	 * 
	 * @param del  a delegate object
	 * 
	 * 
	 * @see Hierarchical
	 */
	public Hierarchy(Hierarchical del)
	{
		super(); delegate = del;
	}
	
	/**
	 * Creates a new {@code Hierarchy}.
	 */
	public Hierarchy()
	{
		super();
	}
	
	
	/**
	 * Returns a branch queue for this {@code Hierarchy}.
	 * </br> This branch starts at the root and goes all the way
	 * down to the delegate of this patriarchy.
	 * 
	 * @return  an iterable branch
	 * 
	 * 
	 * @see Deque
	 */
	public <P extends Hierarchy> Deque<P> Branch()
	{
		Deque<P> queue = new JDeque<>();

		P node = (P) Root();
		while(node != null)
		{
			queue.pushFirst(node);
			node = (P) node.Parent();
		}
		
		return queue;
	}
	
	/**
	 * Checks if this {@code Hierarchy} is a descendant of
	 * the given object. It iterates over each parent in
	 * the hierarchy and checks object equality.
	 * 
	 * @param obj  a potential parent object
	 * @return  {@code true} if obj is a grandparent
	 * 
	 * 
	 * @see Hierarchical
	 */
	public boolean isInside(Hierarchical obj)
	{
		if(this == obj)
		{
			return true;
		}
		
		if(Parent() != null)
		{
			return Parent().Arch().isInside(obj);
		}
		
		return false;
	}

	/**
	 * Changes the parent of the {@code Hierarchy}.
	 * 
	 * @param obj  a parent object
	 * 
	 * 
	 * @see Hierarchical
	 */
	public void setParent(Hierarchical obj)
	{
		parent = obj;
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
	 * i.e. an element without a parent.
	 * 
	 * @return  {@code true} if it is a root
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
		if(delegate != null)
		{
			return delegate;
		}
		
		return this;
	}
	
	@Override
	public Hierarchy Arch()
	{
		return this;
	}
}