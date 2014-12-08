package avltree;

/**
 * Die Klasse eines Blattes/Knoten des Baums. Mehr eine Art Struktur also 
 * Datenhalter f�r die Daten eines Blattes/Knoten.
 * 
 * @author holyshit
 *
 */
public class AVLNode {
	private AVLNode leftChild;
	private AVLNode rightChild;
	private int key;
	int height;
	int balanceHeight;
	
	public AVLNode(int key){
		this.key = key;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public AVLNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(AVLNode newLeftChild) {
		this.leftChild = newLeftChild;
	}
	public AVLNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(AVLNode newRightChild) {
		this.rightChild = newRightChild;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
}
