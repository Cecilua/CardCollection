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
    }
    
    /**
     *  add a card to the collection 
     */
    public void addCard() {
        // ask the user for details 
        String nm = UI.askString("name: ").trim();
        double val = UI.askDouble("market value: ");
        
        //add an image to display in GUI
        String img = UIFileChooser.open("choose image file: ");
        
        // add the card to the collection
        cards.addCard(nm, val, img); 
    }
}
