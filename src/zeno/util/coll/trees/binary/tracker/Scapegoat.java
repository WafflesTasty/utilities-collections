package zeno.util.coll.trees.binary.tracker;

import zeno.util.coll.Tree;
import zeno.util.coll.Tree.Branch;
import zeno.util.coll.trees.BiNode;
import zeno.util.coll.trees.BiTree;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code Scapegoat} class defines a tracker for a {@link BiTree}.
 * The tracker is capable of rebalancing the tree using the {@code Scapegoat algorithm}.
 * 
 * @since Jul 1, 2017
 * @author Zeno
 *
 * @param <O>  the type of objects in the tree
 * @see <a href="https://en.wikipedia.org/wiki/Scapegoat_tree">Wikipedia - Scapegoat Tree</a>
 * @see Tree
 */
public class Scapegoat<O> extends Tree.Tracker
{
	private static <O> BiNode<O> build(BiNode<O>[] array, int i, int size)
	{
		int m = size / 2;
		array[i + m].setLChild(build(array, i, m));
		array[i + m].setRChild(build(array, i + m + 1, size - m - 1));
		return array[i + m];
	}
	
	/**
	 * Builds a balanced {@code BiNode} from an ordered array of nodes.
	 * 
	 * @param array  an array of nodes in order
	 * @return  a balanced node
	 * @see BiNode
	 */
	public static <O> BiNode<O> build(BiNode<O>[] array)
	{
		return build(array, 0, array.length);
	}
		
	
	private int max;
	private float alpha;
	private boolean needsUpdate;
	private BiNode<O> goat, pgoat;
		
	/**
	 * Creates a new {@code Scapegoat}.
	 * 
	 * @param tree  a source tree
	 * @see BiTree
	 */
	public Scapegoat(BiTree<O> tree)
	{
		super(tree);
	}

	
	@Override
	public BiTree<O> Tree()
	{
		return (BiTree<O>) super.Tree();
	}
	
	@Override
	public void onInsert(Branch b)
	{
		super.onInsert(b);
		
		max++;
		goat = (BiNode<O>) b;
		if(goat.NodeDepth() > Floats.log(TreeSize(), 1 / alpha) + 1)
		{
			do
			{
				goat = goat.Parent();
				pgoat = goat.Parent();
			}
			while(3 * goat.NodeCount() <= 2 * pgoat.NodeCount());
			
			needsUpdate = true;
		}
	}
	
	@Override
	public void onDelete(Branch b)
	{
		super.onDelete(b);
	
		if(TreeSize() <= alpha * max)
		{
			needsUpdate = true;
			goat = Tree().Root();
			max = TreeSize();
		}
	}
	
	@Override
	public void onClear()
	{
		super.onClear();
		needsUpdate = false;
		max = 0;
	}

	@Override
	public void update()
	{
		if(needsUpdate)
		{
			super.update();
			
			// Build a balanced node.
			
			int size = goat.NodeCount();
			BiNode<O>[] array = new BiNode[size];
			BiNode<O> node = goat.LLeaf();
			for(int i = 0; i < size; i++)
			{
				array[i] = node;
				node = node.next();
			}
			
			// Replace the scapegoat.
			
			needsUpdate = false;
			node = build(array);
			goat.replace(node);
		}
	}
}