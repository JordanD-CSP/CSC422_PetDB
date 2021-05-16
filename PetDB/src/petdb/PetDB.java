package petdb;

import java.util.Scanner;

/**
 *
 * @author duest
 */
public class PetDB {
    static String[] options = 
        {"View all pets", // option 1
        "Add more pets", // option 2
        "Update an existing pet", // option 3
        "Remove an existing pet",// option 4
        "Search pets by name", // option 5
        "Search pets by age", // option 6
        "Exit Program"}; // default
    static Menu menu = new Menu(options);
    static Scanner input = new Scanner(System.in);
    static PetList petList = new PetList();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Pet Database Program\n");
        
        do {
            menu.displayMenu();
        } while (select());
        
        System.out.println("\nGoodbye!");
    }
    
    
    private static boolean select() {
        int option;
        do {
            System.out.print("Your choice: ");
            option = input.nextInt();
        } while (option < 1 || option > options.length);
        
        switch (option) {
            case 1: return petList.printList();
            case 2: return petList.addPets();
            case 3: return petList.updatePet();
            case 4: return petList.removePet();
            case 5: return petList.searchPetsByName();
            case 6: return petList.searchPetsByAge();
            default: return false;
        }
    }
}
