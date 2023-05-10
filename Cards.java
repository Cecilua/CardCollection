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
}
