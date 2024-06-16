package AVLtreePKG;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
	
	
    AVLtree tree;
	private Scanner keyboard;
	private String option = "";
	
	
	public UserInterface() {
		keyboard = new Scanner(System.in);
		tree = new AVLtree();
	}
	
	public void menu() throws IOException {
		interactionList();
		while(!option.equals("s")) {				
			readInteraction();
		}
	}

	private void interactionList() {
		System.out.println("Guia de interações na Árvore AVL:");
		System.out.println(" i :: Inserir valor inteiro na árvore");
		System.out.println(" r :: Remover valor inteiro");
		System.out.println(" b :: Buscar valor inteiro");
		System.out.println(" pos :: Exibir Arvore em Pos-Ordem");
		System.out.println(" pre :: Exibir Arvore em Pre-Ordem");		
		System.out.println(" em :: Exibir Arvore em Em-Ordem");		
		System.out.println(" s :: Sair");
		System.out.println("=================================");
	}

	private void readInteraction() {
		try {
			System.out.println("Digite a interação que deseja executar:");
			option = keyboard.nextLine();
			switch(option) {
				case "i":
					if(tree.insert(readInteger())) {
						System.out.println("Numero inserido com sucesso.");
					} else {
						System.out.println("Numero já existe na arvore.");
					}
					;
					break;
				case "r":
					if(tree.deleteNode(readInteger())) {
						System.out.println("Numero deletado.");
					} else {
						System.out.println("Numero não existe na arvore.");
					}
					break;
				case "b":						
					if(tree.search(readInteger())) {
						System.out.println("Numero encontrado.");
					} else {
						System.out.println("Numero não existe na arvore.");
					}
					break;
				case "pre":
					System.out.println(tree.preOrder());
					break;
				case "pos":
					System.out.println(tree.posOrder());
					break;
				case "em":
					System.out.println(tree.inOrder());
					break;
				case "s":
					System.out.println("Tchau!");
					break;
				default:
					System.out.println("Interação não reconhecida.");
			}
		}
		catch(Exception e) {
			keyboard.nextLine();
			System.out.println("Digito invalido!");
		}
		System.out.println();
	}
	
	public int readInteger() {
		int intg;
		System.out.println("Digite o inteiro: ");
		intg = keyboard.nextInt();
		keyboard.nextLine();
		return intg;
	}	
}
