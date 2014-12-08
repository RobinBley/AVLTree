package avltree;

import avltree.exception.AVLNodeAlreadyExistsException;
import avltree.exception.AVLNodeNotFoundException;
import avltree.exception.AVLTreeCantBalanceException;

/**
 * Die Hauptklasse des AVLBaums, nicht Main!
 * 
 * @author holyshit
 *
 */
public class AVLTree {
	
	AVLNode root;
	AVLNode pointer;
	int size;
	
	/**
	 * Erstellt den Baum mit einem Wurzelknoten.
	 * 
	 * @param root
	 */
	public AVLTree(AVLNode root){
		root.height = 0;
		root.balanceHeight = 0;
		this.root = root;
		this.size = 0;
	}
	
	/**
	 * Findet einen Knoten mithilfe des Keys.
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public AVLNode findNode(int key) throws Exception{
		pointer = root;
		
		//solange der Knoten nicht gefunden wurde
		while(pointer.getKey() != key){
			//kucke ob der gesuchte Key kleiner
			if(pointer.getKey() > key){
				//ist er kleiner -> weiter nach rechts
				pointer = pointer.getRightChild();
			//oder gr��er ist
			}else{
				//ist er gr��er -> weiter nach links
				pointer = pointer.getLeftChild();
			}
			
			//wenn der n�chste Knoten nicht exestiert
			if(pointer == null){
				//werfe eine Exception
				throw new AVLNodeNotFoundException();
			}
		}
		
		//gebe den gefundenden Knoten zur�ck
		return pointer;
	}
	
	/**
	 * Setzt einen bestimmten Key an die richtige Stelle im Baum.
	 * 
	 * @param key
	 * @throws Exception
	 */
	public void insertNode(int key) throws Exception{
		size++;
		
		//pointer ist eine Art tempor�re Variable
		pointer = root;
		
		//der Path den der Baum gegangen ist beim einf�gen
		//wird f�rs rebalancing ben�tigt.
		AVLPath path = new AVLPath(size);
		
		while(pointer != null){
			
			path.insertTop(pointer);
			
			//ist der aktuelle Knoten gr��er als der neue key?
			if(pointer.getKey() > key){
				pointer = pointer.getLeftChild();
			//ist der aktuelle Knoten kleiner als der neue key?
			}else if(pointer.getKey() < key){
				//gehe zum n�chsten Kind
				pointer = pointer.getRightChild();
			}else if(pointer.getKey() == key){
				throw new AVLNodeAlreadyExistsException();
			}else{
				throw new Exception();
			}
		}
		
		AVLNode newNode = new AVLNode(key);
		newNode.balanceHeight = 0;
		
		//neuen Knoten einf�gen (links/rechts)
		if(key < pointer.getKey()){
			pointer.setLeftChild(newNode);
		}else{
			pointer.setRightChild(newNode);
		}
		
		int heightLeft, heightRight;
		
		//solange der Pfad nicht leer ist
		while(!path.isEmtpy()){
			//hole den obersten Knoten
			pointer = path.getTopAndRemove();
			
			//hat er ein linkes Kind
			if(pointer.getLeftChild() == null){
				heightLeft = -1;
			}else{
				heightLeft = pointer.getLeftChild().height;
			}
			
			//hat er ein rechtes Kind
			if(pointer.getLeftChild() == null){
				heightRight = -1;
			}else{
				heightRight = pointer.getRightChild().height;
			}
			
			//die neue balanceHeight, setzt sich aus beiden heights zusammen
			pointer.balanceHeight = heightRight - heightLeft;
			
			if(heightLeft > heightRight){
				pointer.height = 1 + heightLeft;
			}else{
				pointer.height = 1 + heightRight;
			}
			
			if((pointer.balanceHeight < -1) || (pointer.balanceHeight > 1)){
				throw new AVLTreeCantBalanceException();
			}
		}
	}
}
