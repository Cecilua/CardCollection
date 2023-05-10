import java.util.HashMap;
import ecs100.*;
/**
 * holds cards in a hashmap
 * allows the user to add a card, find a card, and print all cards. 
 *
 * @author Cecilia Kuntze
 * @version 09/05/2023
 */
public class Cards
{
    // instance variables
    private HashMap<Integer, Card> cardsMap; // declare the hashmap
    private int currCardId;
    
    private Card currCard; // store the instance of the found card

    /**
     * Constructor for objects of class Cards
     */
    public Cards()
    {
        // initialise instance variables
        cardsMap = new HashMap<Integer, Card>();
        
        // create some cards (for testing)
        Card c1 = new Card("Raichu", 0.56 , "raichu.png");
        Card c2 = new Card("Haunter", 0.42 , "haunter.png");
        Card c3 = new Card("Teddiursa", 0.25 , "teddiursa.png");
        
        // add the cards to the hashmap
        cardsMap.put(1, c1);
        cardsMap.put(2, c2);
        cardsMap.put(3, c3);
        
        this.currCardId = 3; // set the current card Id 
    }
    
    /**
     *  add a card to the hashmap
     */
    public void addCard(String nm, double val, String img) {
        currCardId++; // increment card id 
        cardsMap.put(currCardId, new Card(nm, val, img));
    }
    
    /**
     *  looks for card in hashmap (by name) 
     *  @return boolean if found
     */
    public boolean findCard(String name) {
        for (int cardId : cardsMap.keySet()) {
            if (cardsMap.get(cardId).getName().toLowerCase().equals(name.toLowerCase())) {
                currCard = cardsMap.get(cardId); // stores the found card
                return true;
            }
        }
        return false; // if no card found 
    }
    
    /**
     * getter for current card 
     * @return Card (the found card instance) 
     */
    public Card getCard() {
        return this.currCard;
    }
    
    /**
     * displays all cards in the hashmap 
     */
    public void displayAll() {
        final int STARTX = 147; //  starting x pos of cards
        int locY = 20; 
        final int YJUMP = 196; // the ammount the y pos moves per row 
        final double ROW_NUM = 3; // the number of cards in each row
        int cardId = 1; // the id of the card to be displayed 
        
        // learnt about ceiling function here:
        // https://www.programiz.com/java-programming/library/math/ceil
        double rowAmmount = Math.ceil(cardsMap.size() / ROW_NUM); // calculate the ammount of rows 
        
        UI.clearGraphics(); // clear the graphics pane 
        
        // display all cards 
        for (int i = 0; i < rowAmmount; i++) {
            for (int a = 0; a < ROW_NUM || a > cardsMap.size(); a++) {
                currCard = cardsMap.get(cardId); // get the card instance
                currCard.displayCard(STARTX*(a+1), locY); // display the card
                cardId++; // increment cardId 
            }
            locY += YJUMP; // update y pos 
        }
    }
}
