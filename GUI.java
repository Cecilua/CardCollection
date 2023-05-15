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
    private Card card;
    
    final int FOUND_X = 200; // the x pos of found card 
    final int FOUND_Y = 100; // the y pos of found card 

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        cards = new Cards();
        UI.initialise();
        UI.addButton("add a card", this::addCard);
        UI.addButton("find a card", this::findCard);
        UI.addButton("show collection", this::displayAll); 
        UI.addButton("hide all details", this::clearAll); 
        UI.addButton("quit", UI::quit);
        
        // set up mouse 
        UI.setMouseListener(this::doMouse);
    }
    
    /**
     *  add a card to the collection 
     */
    public void addCard() {
        // set constants 
        final double MIN_VALUE = 0; // min card market value
        final double MAX_VALUE = 10000000; // max card market value
        
        UI.println("\n----- add a card -----");
        
        // ask the user for details 
        String nm = isValidString("name: ").toLowerCase();
        double val = isValidDouble("market value: ", MIN_VALUE, MAX_VALUE);
      
        //add an image to display in GUI
        String img = UIFileChooser.open("choose image file: ");
        
        if(cards.findCard(nm)) {
            UI.println("this card is already in the collection !!"); 
        } else {
            // add the card to the collection
            cards.addCard(nm, val, img); 
            UI.println("card added to collection"); 
        }
    }
    
    /**
     *  takes an question, a minimum and a maximum number
     *  @return double if valid 
     */
    public double isValidDouble(String question, double min, double max) {
        double number = min; // innitialise the number as the minimum 
        // keep asking for a number until it is greater than the min and less than the max
        while (number <= min || number >= max) {
            number = UI.askDouble(question); 
            if (number <= min) {
                UI.println("number must be greater than " + min);
            } else if (number >= max) {
                UI.println("number must be less than " + max); 
            }
        }
        return number; // once a valid number is given, return number
    }
    
    /**
     * takes an question 
     * @return String if valid
     */
    public String isValidString(String question) {
        boolean asking = true; 
        String string = "";
        while (asking) {
            string = UI.askString(question).trim(); // ask user for a string
            if (string.equals("")){
                UI.println("you must enter something!"); // error message is string is empty
            } else {
                asking = false; // if string is valid, stop asking
            }
        }
        return string;
    }
    
    /**
     * search for a card, and display if found
     */
    public void findCard() {
        UI.clearGraphics(); // clear the graphics pane 
        
        UI.println("\n----- find a card -----");
        String cardName = UI.askString("search card name: ").trim(); // ask user for the card name
        
        if (cards.findCard(cardName)) {
            UI.sleep(1000); // wait 1 second
            // if card is found, show its details
            UI.println("\ncard found!");
            printDetails(cards.getFoundCardId()); 
            UI.println("click on the card to hide its details."); 
            card.displayCard(FOUND_X, FOUND_Y);
        } else {
            UI.println("card not found :("); // if card isnt found --> error message
        }
    }
    
    /**
     * takes cardID and prints the cards information 
     */
    public void printDetails(int cardID) {
        card = cards.getCard(cardID);
        UI.println("\n----- " + card.getName() + " -----");
        UI.println("market value: $" + card.getValue() + "\n");
    }
    
    /**
     * displays all cards in the collection 
     */
    public void displayAll() {
        UI.clearGraphics(); // clear the graphics pane 
        
        final int STARTX = 147; //  starting x pos of cards
        int locY = 20; 
        final int YJUMP = 196; // the ammount the y pos moves per row 
        final double ROW_NUM = 3; // the number of cards in each row
        int cardId = 1; // the id of the card to be displayed 
        
        // learnt about ceiling function here:
        // https://www.programiz.com/java-programming/library/math/ceil
        double rowAmmount = Math.ceil(cards.getSize() / ROW_NUM); // calculate the ammount of rows  
        
        // display all cards 
        for (int i = 0; i < rowAmmount; i++) {
            for (int a = 1;  a <= ROW_NUM; a++) {
                if(cardId <= cards.getSize()) {
                    card = cards.getCard(cardId); // get the card instance
                    card.displayCard(STARTX*(a), locY); // display the card
                    cardId++; // increment cardId 
                }
            }
            locY += YJUMP; // update y pos 
        }
        
        UI.println("\n----- your card collection!! -----");
        
        UI.println("click on a card to show its details.");
    }
    
    /**
     * clear the text and graphics pane
     */
    public void clearAll() {
        UI.clearText(); // clear the text pane 
        UI.clearGraphics(); // clear the graphics pane 
    } 
    
    /**
     * Callback method to mouse listener 
     */
    public void doMouse(String action, double x, double y) { 
        if (action.equals("clicked")) { 
            for (int i = 1; i <= cards.getSize(); i++) {
                card = cards.getCard(i);
                if ((x >= card.getLeft()) && x <= card.getRight() && y >= card.getTop() && y<= card.getBottom()) {
                    // check if the card clicked is the found card
                    if(card.getLeft() == FOUND_X && card.getTop() == FOUND_Y) {
                        clearAll(); // hide details 
                    } else {
                        printDetails(i); // if card in collection is clicked, print its details
                    }
                } 
            }
        }
    }
}