package avltree.exception;

/**
 * Exception wenn ein Knoten nicht gefunden werden kann.
 * 
 * @author holyshit
 *
 */
public class AVLNodeNotFoundException extends Exception{
	public AVLNodeNotFoundException(){
		
	}
	
	public AVLNodeNotFoundException(String s){
		super(s);
	}
}
