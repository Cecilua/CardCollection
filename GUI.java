import ecs100.*; 
/**
 * class handles GUI functionality
 *
 * @author Cecilia Kuntze
 * @version 09/05/23
 */
public class GUI
{
    // instance variables
    private Cards cards;

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        cards = new Cards();
        UI.initialise();
        UI.addButton("add a card", this::addCard);
        UI.addButton("quit", UI::quit);
    }
    
    /**
     *  add a card to the collection 
     */
    public void addCard() {
        // set constants 
        final double MIN_VALUE = 0; 
        final double MAX_VALUE = 10000000;
        
        // ask the user for details 
        String nm = UI.askString("name: ").trim();
        
        double val = isValidDouble("market value: ", MIN_VALUE, MAX_VALUE);
        
        //add an image to display in GUI
        String img = UIFileChooser.open("choose image file: ");
        
        // add the card to the collection
        cards.addCard(nm, val, img); 
    }
    
    /**
     *  takes an question, a minimum and a maximum number
     *  @return double if valid 
     */
    public double isValidDouble(String question, double min, double max) {
        double number = min; // innitialise the number as the minimum 
        while (number <= min || number >= max) {
            number = UI.askDouble(question); 
            if (number <= min) {
                UI.println("number must be greater than " + min);
            } else if (number >= max) {
                UI.println("number must be less than " + max); 
            }
        }
        return number; 
    }
    
}
