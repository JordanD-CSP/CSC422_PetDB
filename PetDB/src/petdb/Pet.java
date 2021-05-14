/*
 * NAME: Jordan Duesterhoeft
 * CLASS: CSC 422
 * DATE: 2021-05-13
 * ASSIGNMENT: Assignment 1, part 2
 * FILE NAME: Pet.java
 */
package petdb;

/**
 *
 * @author duest
 */
public class Pet {
    private String name = new String();
    private int age = 0;
    
    Pet() {};
    
    Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}
