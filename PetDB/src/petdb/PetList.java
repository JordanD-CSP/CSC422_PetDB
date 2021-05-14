package petdb;

import java.util.Scanner;

/**
 *
 * @author duest
 */
public class PetList {
    Pet[] pets = new Pet[100];
    int petCount = 0;
    Scanner input = new Scanner(System.in);
    
    PetList() {};
    
    public boolean printList() {
        System.out.println();
        printTableHeader();
        for (int id = 0; id < petCount; id++) {
            printTableRow(id, pets[id].getName(), pets[id].getAge());
        }
        printTableFooter(petCount);
        
        return true;
    }
    
    private static void printTableHeader() {
        System.out.println("+-------------------------+");
        System.out.println("|  ID | NAME       |  AGE |");
        System.out.println("+-------------------------+");
    }
    
    private static void printTableRow(int id, String name, int age) {
        System.out.printf("| %3d | %-10s | %4d |\n", id, name, age);
    }
    
    private static void printTableFooter(int rowCount) {
        System.out.println("+-------------------------+");
        System.out.printf("%d rows in set.\n", rowCount);
    }
    
    public boolean addPets() {
        System.out.println();
        int petsAdded = 0;
        do {
            if (petsAdded == 100) {
                System.out.println(
                        "You have reached the maximum number of pets.");
                break;
            }
            try {
                System.out.print("add pet (name, age): ");
                String line = input.nextLine().trim();
                if (line.equals("done")) {
                    break;
                }
                String[] petData = line.split(" ");
                String name = petData[0];
                int age = Integer.parseInt(petData[1]);
                pets[petCount++] = new Pet(name, age);
                petsAdded++;
            } catch (NumberFormatException ex) {
                System.out.println("Input was formatted incorrectly.");
                System.out.println("Pet names may not contain spaces.");
                System.out.println("Please re-enter.\n");
            }
        } while (true);
        System.out.printf("%d pets added.\n", petsAdded);
        System.out.println();
        return true;
    }
}
