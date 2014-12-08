package avltree.exception;

/**
 * Exception wenn schon ein Knoten exestiert.
 * 
 * @author holyshit
 *
 */
public class AVLNodeAlreadyExistsException extends Exception {

	public AVLNodeAlreadyExistsException(){
		
	}
	
	public AVLNodeAlreadyExistsException(String s){
		super(s);
	}

}
