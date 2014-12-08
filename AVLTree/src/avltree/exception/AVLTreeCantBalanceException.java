package avltree.exception;

/**
 * Wenn {@link AVLTree} nicht balanciert werden kann, wegen fehlendem Knoten. 
 * @author holyshit
 *
 */
public class AVLTreeCantBalanceException extends Exception {
	public AVLTreeCantBalanceException(){
		
	}
	
	public AVLTreeCantBalanceException(String s){
		super(s);
	}
}
