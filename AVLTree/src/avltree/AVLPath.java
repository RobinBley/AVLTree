package avltree;

import java.util.ArrayList;

/**
 * Speichert den Pfad als eine Art Stack f�r einfaches balancing.
 * 
 * @author holyshit
 *
 */
public class AVLPath {
	private int max;
	private int top;
	private AVLNode[] path;
	
	public AVLPath(int s){
		this.max = s;
		this.top = -1;
		this.path = new AVLNode[max];
	}
	
	public void insertTop(AVLNode s){
		path[++top] = s;
	}
	
	public AVLNode getTopAndRemove(){
		return path[top--];
	}
	
	public AVLNode getTop(){
		return path[top];
	}
	
	public boolean isEmtpy(){
		if(top == -1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isFull(){
		if(top == max-1){
			return true;
		}else{
			return false;
		}
	}
}
