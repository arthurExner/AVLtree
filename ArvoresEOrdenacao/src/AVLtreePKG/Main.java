package AVLtreePKG;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {		
		
		UserInterface ui = new UserInterface();
		
		try {
			ui.menu();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
}
