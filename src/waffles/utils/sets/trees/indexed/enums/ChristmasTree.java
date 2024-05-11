package waffles.utils.sets.trees.indexed.enums;

import waffles.utils.sets.indexed.mutable.ArrayIndex;
import waffles.utils.sets.utilities.Activity;

/**
 * A {@code ChristmasTree} is a regular {@code ArrayIndex} coupled with an {@code ActionTree}.
 * The {@code ActionTree} defines the visibility state of objects in the index, which
 * allows the subset of visible objects to be iterated efficiently. Hopefully.
 *
 * @author Waffles
 * @since 11 May 2024
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see ArrayIndex
 */
public class ChristmasTree<O> extends ArrayIndex<O>
{
	private ActionTree tree;
	
	/**
	 * Creates a new {@code ChristmasTree}.
	 * 
	 * @param dims  an index dimension
	 */
	public ChristmasTree(int... dims)
	{
		super(dims); tree = new ActionTree(dims);
	}
	
	/**
	 * Iterates over visible objects in the {@code ChristmasTree}.
	 * 
	 * @return  an object iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public Iterable<O> VisibleObjects()
	{
		return tree.ActiveObjects(this);
	}
	
	/**
	 * Designates a visible portion of the {@code ChristmasTree}.
	 * 
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void show(int[] min, int[] max)
	{
		tree.put(Activity.ACTIVE, min, max);
	}

	/**
	 * Designates a hidden portion of the {@code ChristmasTree}.
	 * 
	 * @param min  an index minimum
	 * @param max  an index maximum
	 */
	public void hide(int[] min, int[] max)
	{
		tree.put(Activity.IDLE, min, max);
	}
	
	/**
	 * Designates a visible tile of the {@code ChristmasTree}.
	 * 
	 * @param crd  an index coordinate
	 */
	public void show(int... crd)
	{
		tree.put(Activity.ACTIVE, crd);
	}

	/**
	 * Designates a hidden tile of the {@code ChristmasTree}.
	 * 
	 * @param crd  an index coordinate
	 */
	public void hide(int... crd)
	{
		tree.put(Activity.IDLE, crd);
	}
	
	
	@Override
	public void clear()
	{
		super.clear();
		tree.clear();
	}
}