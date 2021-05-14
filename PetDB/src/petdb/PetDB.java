package petdb;

import java.util.Scanner;

/**
 *
 * @author duest
 */
public class PetDB {
    static String[] options = {"View all pets", 
        "Add more pets", 
        "Exit Program"};
    static Menu menu = new Menu(options);
    static Scanner input = new Scanner(System.in);
    static PetList petList = new PetList();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        do {
            menu.displayMenu();
        } while (select());
        
        System.out.println("\nGoodbye!");
    }
    
    
    static boolean select() {
        int option;
        do {
            System.out.print("Your choice: ");
            option = input.nextInt();
        } while (option < 1 || option > options.length);
        
        switch (option) {
            case 1: return petList.printList();
            case 2: return petList.addPets();
            default: return false;
        }
    }
}
