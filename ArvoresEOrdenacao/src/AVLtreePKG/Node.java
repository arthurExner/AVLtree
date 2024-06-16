package AVLtreePKG;

public class Node {		
	
	int value;
	int height = 1;
	Node left;
	Node right;
	
	Node(int value) {
		this.value = value;		
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public int getHeight() {
		return this.height;
	}
	
}
