package petdb;

import java.util.Scanner;

/**
 *
 * @author duest
 */
public class PetDB {
    static String[] options = 
        {"View all pets", 
        "Add more pets", 
        "Search pets by name", 
        "Search pets by age", 
        "Exit Program"};
    static Menu menu = new Menu(options);
    static Scanner input = new Scanner(System.in);
    static PetList petList = new PetList();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            case 3: return petList.searchPetsByName();
            case 4: return petList.searchPetsByAge();
            default: return false;
        }
    }
}
