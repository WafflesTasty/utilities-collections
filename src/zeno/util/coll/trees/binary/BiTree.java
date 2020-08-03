package zeno.util.coll.trees.binary;

import zeno.util.coll.trees.Tree;
import zeno.util.coll.trees.traversal.binary.InOrder;
import zeno.util.coll.trees.traversal.binary.PostOrder;
import zeno.util.coll.trees.traversal.binary.PreOrder;

/**
 * The {@code BiTree} class defines a generic binary tree.
 *
 * @author Zeno
 * @since 03 Aug 2020
 * @version 1.0
 * 
 * 
 * @see Tree
 */
public class BiTree implements Tree
{
	private BiNode root;	
	
	/**
	 * Changes the root of the {@code BiTree}.
	 * 
	 * @param root  a root node
	 * 
	 * 
	 * @see BiNode
	 */
	public void setRoot(BiNode root)
	{
		this.root = root;
	}
	
	/**
	 * Performs in-order iteration of the {@code BiTree}.
	 * 
	 * @param <B>  a node type
	 * @return  an in-order iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public <B extends BiNode> Iterable<B> inorder()
	{
		return () -> new InOrder<>(Root());
	}
	
	/**
	 * Performs post-order iteration of the {@code BiTree}.
	 * 
	 * @param <B>  a node type
	 * @return  a post-order iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public <B extends BiNode> Iterable<B> postorder()
	{
		return () -> new PostOrder<>(Root());
	}
	
	/**
	 * Performs pre-order iteration of the {@code BiTree}.
	 * 
	 * @param <B>  a node type
	 * @return  a pre-order iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public <B extends BiNode> Iterable<B> preorder()
	{
		return () -> new PreOrder<>(Root());
	}
		
	
	@Override
	public BiNode Root()
	{
		return root;
	}
}