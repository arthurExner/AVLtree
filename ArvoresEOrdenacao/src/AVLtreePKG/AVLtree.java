package AVLtreePKG;

import java.util.ArrayList;

public class AVLtree {	
	
	Node root;	
	
//=======Inserir no
	
	private Node insert(Node currentNode, int value) {
		if(currentNode == null) return new Node(value);
		
		if(value < currentNode.value) {
			currentNode.left = insert(currentNode.left, value);
		} else if (value > currentNode.value) {
			currentNode.right = insert(currentNode.right, value);
		} else {
			return currentNode;
		}
		
		//antes de retornar, checar se precisa balencear a árvore:
		
		updateHeight(currentNode);
		
		return applyRotation(currentNode);	
		
	}
	
	public boolean insert(int value) {
		if(root == null) {
			root = new Node(value);
			return true;
		}
		
		if(search(value)) {			  
			return false; // já tem esse valor na arvore
		} 
		
		root = insert(this.root, value); // chamo o método recursivo, atribuindo à root se não não insere o ultimo nó
		return true;
	}		

//=======Buscar valor
	
	public boolean search(int value) {
		//if(root == null) return false;
		Node temp = root;
		while(temp != null) {
			if(value < temp.value) {
				temp = temp.left;
			} else if(value > temp.value) {
				temp = temp.right;
			} else {
				return true;
			}
		}
		return false;
	}
	
//=======Deletar
	
	private Node deleteNode(Node currentNode, int value) {
		if(currentNode == null) return null;
		
		if(value < currentNode.value) {
			currentNode.left = deleteNode(currentNode.left, value);
		} else if(value > currentNode.value) {
			currentNode.right = deleteNode(currentNode.right, value);
		} else { //se encontrei o valor a ser deletado
			if(currentNode.left == null && currentNode.right == null) { //folha
				return null;
			} else if (currentNode.left == null) { //nó só na direita
				currentNode = currentNode.right;
			} else if (currentNode.right == null) { //nó só na esquerda
				currentNode = currentNode.left;
			} else { //nó em ambos lados
				int subTreeMin = minValue(currentNode.right); //acho o menor valor da subárvore à direita do nó que quero deletar
				currentNode.value = subTreeMin; //copio seu valor pro que quero deletar
				currentNode.right = deleteNode(currentNode.right, subTreeMin);//deleto o que copiei
			}			
		}		

		//antes de retornar, checar se precisa balencear a árvore:		
		updateHeight(currentNode);
		return applyRotation(currentNode);		
						
	}	
	
	public boolean deleteNode(int value) {
	  if(search(value)) {
		  root = deleteNode(this.root, value); // chamo o método recursivo, atribuindo à root se não não remove o ultimo nó
		  return true;
	  } 
	  return false;
    }	  

//=======Rotacoes
    
    private Node rotateRight(Node n) {
    	Node leftNode = n.left; // nova raíz se torna o nó à esquerda da raíz original 
    	Node center = leftNode.right; // o que está À direita da nova raíz será armazenado na esquerda da raíz original
    	
    	leftNode.right = n; // a raíz original é setada à direita da nova raíz
    	n.left = center; 
    	
    	updateHeight(n);
    	updateHeight(leftNode);
    	
    	return leftNode;
    }
    
    private Node rotateLeft(Node n) {
    	Node rightNode = n.right; // nova raíz se torna o nó à direita da raíz original 
    	Node center = rightNode.left; // o que está À esquerda da nova raíz será armazenado na direita da raíz original
    	
    	rightNode.left = n; // a raíz original é setada à esquerda da nova raíz
    	n.right = center; 
    	
    	updateHeight(n);
    	updateHeight(rightNode);
    	
    	return rightNode;
    }
    
//=======Auxiliares    

	private int minValue(Node currentNode) {
		if(currentNode.left == null) return currentNode.value; //o menor valor será o mais à esquerda
		currentNode = currentNode.left;
		return minValue(currentNode);
	} 
    
    private void updateHeight(Node n) {
		int maxHeight = Math.max(height(n.left), height(n.right));
		n.setHeight(maxHeight + 1);
	}
	
	private int height(Node n) {
		return n != null ? n.getHeight() : 0;
	}
	
	private Node applyRotation(Node n) {
		int balance = balanceFactor(n);
		
		if(balance > 1) { // desbalanceado para esquerda
			if(balanceFactor(n.left) < 0){
				n.left = rotateLeft(n.left);
			}
			return rotateRight(n);
		}
		
		if(balance < -1) { //desbalanceado para direita
			if(balanceFactor(n.right) > 0) {
				n.right = rotateRight(n.right);
			}
			return rotateLeft(n);
		}
		
		return n; //se não ta desbalanceado não faz nada
	}
	
	private int balanceFactor(Node n) {
		return n != null ? height(n.left) - height(n.right) : 0;
	}

//=======Caminhamentos
	
	//pré-ordem VLR
	public ArrayList<Integer> preOrder() {
		ArrayList<Integer> result = new ArrayList<>();
		
		class Walk {			
			Walk(Node currentNode) { //usando o construtor da classe como método recursivo auxiliar
				result.add(currentNode.value); //insiro o valor atual
				if(currentNode.left != null) {
					new Walk(currentNode.left);//vai pra esquerda
				}
				if(currentNode.right != null) {
					new Walk(currentNode.right); //vai pra direita
				}
			}
			
		}
		new Walk(root);
		
		return result;
	}
		
	//pós-ordem LRV
	public ArrayList<Integer> posOrder() {
		ArrayList<Integer> result = new ArrayList<>();
		
		class Walk {			
			Walk(Node currentNode) { //usando o construtor da classe como método recursivo auxiliar
				if(currentNode.left != null) {
					new Walk(currentNode.left);
				}
				if(currentNode.right != null) {
					new Walk(currentNode.right); 
				}
				result.add(currentNode.value);
			}
			
		}
		new Walk(root);
		
		return result;
	}
	
	//Em ordem LVR
	public ArrayList<Integer> inOrder() {
		ArrayList<Integer> result = new ArrayList<>();
		
		class Walk {			
			Walk(Node currentNode) { //usando o construtor da classe como método recursivo auxiliar
				if(currentNode.left != null) {
					new Walk(currentNode.left);
				}
				result.add(currentNode.value);
				if(currentNode.right != null) {
					new Walk(currentNode.right); 
				}					
			}
			
		}
		new Walk(root);
		
		return result;
	}
}
