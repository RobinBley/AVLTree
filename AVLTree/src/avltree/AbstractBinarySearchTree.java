package avltree;

import java.util.List;

/**
 * Interface fuer Binary Search Tress
 *
 * @author wolke
 * @version 1.0, 19.06.2011
 *
 */
public interface AbstractBinarySearchTree {
	
	/**
         * Add a new node
	 * @param value_ value of the new node which is to add
	 * @throws BinarySearchTreeException
	 */
	public abstract void addValue(Integer value_)
			throws BinarySearchTreeException;

	/**
         * Delete a node out of the tree
	 * @param value_ the value we'd like to delete
	 * @throws BinarySearchTreeException If the value is not in the tree.
	 */
	public abstract void delValue(Integer value_)
			throws BinarySearchTreeException;

	/**
         * Checks for a value
	 * @param value_ The value we are searching
	 * @return True or False(we couldn't find it)
	 */
	public abstract boolean hasValue(Integer value_);

	/**
         * Get the deep of the tree
	 * @return the deepness of the tree
	 */
	public abstract Integer getDepth();
	
	/**
         * Method to traverse the tree
	 * @param o enum (inorder, preorder or postorder)
	 * @return list with the elements of the tree in the given order 
	 */
	public abstract List<Integer> traverse(Order o);

}