package petdb;

/**
 *
 * @author duest
 */
public class Menu {
    private String[] options;
    
    Menu() {};
    
    Menu(String[] options) {
        this.options = options;
    }
    
    public String[] getOptions() {
        return options;
    }
    
    public void setOption(String[] options) {
        this.options = options;
    }
    
    public void displayMenu() {
        int optionNumber = 1;
        for (String option: options) {
            System.out.println(optionNumber + ") " + option);
            optionNumber++;
        }
    }
}
