package waffles.utils.sets;

/**
 * An {@code ArrayLike} defines a generic array-like structure.
 *
 * @author Waffles
 * @since 12 Nov 2023
 * @version 1.1
 *
 *
 * @param <A>  an array type
 */
public interface ArrayLike<A>
{
	/**
	 * Returns the array of the {@code ArraySet}.
	 * 
	 * @return  a data array
	 */
	public abstract A Array();
}