package petdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    static Scanner input = new Scanner(System.in);
    static PetList petList = new PetList();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Pet Database Program\n");
        loadData();
        
        do {
            displayMenu();
        } while (select());
        
        saveData();
        System.out.println("\nGoodbye!");
    }
    
    
    private static boolean select() {
        int option;
        do {
            System.out.print("Your choice: ");
            option = input.nextInt();
        } while (option < 1 || option > options.length);
        
        switch (option) {
            case 1: return printTable();
            case 2: return addPets();
            case 3: return updatePet();
            case 4: return removePet();
            case 5: return searchPetsByName();
            case 6: return searchPetsByAge();
            default: return false;
        }
    }
    
    private static boolean addPets() {
        System.out.println();
        int petsAdded = 0;
        do {
            String line;
            do {
                System.out.print("add pet (name, age): ");
                line = input.nextLine().trim();
                if (line.equals("done")) {
                    break;
                }
            } while (!inputIsValid(line));
            if (line.equals("done")) {
                break;
            }
            if (petList.size() == 5) {
                System.out.println("Error: Database is full.");
                break;
            } else {
                String[] petData = line.split(" ");
                String name = petData[0];
                int age = Integer.parseInt(petData[1]);
                petList.add(new Pet(name, age));
                petsAdded++;
            }
        } while (true);
        System.out.printf("%d pets added.\n", petsAdded);
        System.out.println();
        
        return true;
    }
    
    private static void displayMenu() {
        int optionNumber = 1;
        for (String option: options) {
            System.out.println(optionNumber + ") " + option);
            optionNumber++;
        }
    }
    
    private static void loadData(){
        File petData = new File("petData.txt");
        if (petData.exists()) {
            try {
                Scanner dataScanner = new Scanner(petData);
                String name;
                int age;
                while (dataScanner.hasNext()) {
                    name = dataScanner.next();
                    age = dataScanner.nextInt();
                    petList.add(new Pet(name, age));
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
        }
    }
    
    private static boolean printTable() {
        String name;
        int age;
        
        System.out.println();
        printTableHeader();
        for (int id = 0; id < petList.size(); id++) {
            name = petList.get(id).getName();
            age = petList.get(id).getAge();
            printTableRow(id, name, age);
        }
        printTableFooter(petList.size());
        System.out.println();
        
        return true;
    }
    
    private static boolean printTable(String searchName) {
        String name;
        int age;
        int itemsFound = 0;
        
        System.out.println();
        printTableHeader();
        for (int id = 0; id < petList.size(); id++) {
            name = petList.get(id).getName();
            age = petList.get(id).getAge();
            if (name.equalsIgnoreCase(searchName)) {
                itemsFound++;
                printTableRow(id, name, age);
            }
        }
        printTableFooter(itemsFound);
        System.out.println();
        
        return true;
    }
    
    private static boolean printTable(int searchAge) {
        String name;
        int age;
        int itemsFound = 0;
        
        
        System.out.println();
        printTableHeader();
        for (int id = 0; id < petList.size(); id++) {
            name = petList.get(id).getName();
            age = petList.get(id).getAge();
            if (age == searchAge) {
                itemsFound++;
                printTableRow(id, name, age);
            }
        }
        printTableFooter(itemsFound);
        System.out.println();
        
        return true;
    }
    
    private static void printTableFooter(int rowCount) {
        System.out.println("+-------------------------+");
        System.out.printf("%d rows in set.\n", rowCount);
    }
    
    private static void printTableHeader() {
        System.out.println("+-------------------------+");
        System.out.println("|  ID | NAME       |  AGE |");
        System.out.println("+-------------------------+");
    }
    
    private static void printTableRow(int id, String name, int age) {
        System.out.printf("| %3d | %-10s | %4d |\n", id, name, age);
    }
    
    private static boolean removePet() {
        printTable();
        System.out.println();
        System.out.print("Enter the pet ID to remove: ");
        int id = input.nextInt();
        System.out.printf("%s %d is removed.\n", 
                petList.get(id).getName(), petList.get(id).getAge());
        petList.remove(id);
        
        return true;
    }
    
    private static void saveData(){
        File petData = new File("petData.txt");
        try {
            PrintWriter output = new PrintWriter(petData);
            String name;
            int age;
            for (Pet pet: petList) {
                name = pet.getName();
                age = pet.getAge();
                output.print(name + " " + age + " ");
            }
            output.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    private static boolean searchPetsByAge() {
        System.out.println();
        System.out.print("Enter age to search: ");
        int age = input.nextInt();
        
        return printTable(age);
    }
    
    private static boolean searchPetsByName() {
        System.out.println();
        System.out.print("Enter a name to search: ");
        String name = input.next();
        
        return printTable(name);
    }
    
    private static boolean updatePet() {
        printTable();
        System.out.println();
        System.out.print("Enter the pet ID to update: ");
        int id = input.nextInt();
        String line;
        String newName;
        int newAge;
        do {
            System.out.print("Enter new name and new age: ");
            line = input.nextLine();
        } while (!inputIsValid(line));
        String[] petData = line.split(" ");
        newName = petData[0];
        newAge = Integer.parseInt(petData[1]);
        System.out.printf("%s %d changed to %s %d\n", 
                petList.get(id).getName(), petList.get(id).getAge(), newName, newAge);
        petList.get(id).setName(newName);
        petList.get(id).setAge(newAge);
        
        return true;
    }
    
    private static boolean inputIsValid(String input) {
        String[] input2 = input.split(" ");
        if (input2.length != 2) {
            System.out.printf("Error: %s is not a valid input.\n", input);
            return false;
        } else {
            int age = Integer.parseInt(input2[1]);
            if (age < 1 || age > 20) {
                System.out.printf("Error: %d is not a valid age.\n", age);
                return false;
            } else {
                return true;
            }
        }
        
    }
}
