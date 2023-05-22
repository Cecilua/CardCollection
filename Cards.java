import ecs100.*;
import java.util.HashMap;
/**
 * holds cards in a hashmap
 * allows the user to add a card, find a card, and print all cards. 
 *
 * @author Cecilia Kuntze
 * @version 22/05/2023
 */

public class Cards {
  // instance variables
  private HashMap<Integer, Card> cardsMap; // declare the hashmap
  private int currCardId;

  private int foundCardId; // the id of the found card 

  private Card currCard; // store the instance of the found card

  /**
   * Constructor for objects of class Cards.
   */
  public Cards() {
    // initialise instance variables
    cardsMap = new HashMap<Integer, Card>();

    // create some cards (for testing)
    Card c1 = new Card("raichu", 0.56, "raichu.png");
    Card c2 = new Card("haunter", 0.42, "haunter.png");
    Card c3 = new Card("teddiursa", 0.25, "teddiursa.png");

    // add the cards to the hashmap
    cardsMap.put(1, c1);
    cardsMap.put(2, c2);
    cardsMap.put(3, c3);

    this.currCardId = 3; // set the current card Id 
  }

  /**
   *  add a card to the hashmap.
   */
  public void addCard(String nm, double val, String img) {
    currCardId++; // increment card id 
    cardsMap.put(currCardId, new Card(nm, val, img));
  }

  /**
   *  looks for card in hashmap (by name). 
   *
   *  @return boolean if found
   */
  public boolean findCard(String name) {
    for (int cardId : cardsMap.keySet()) {
      if (cardsMap.get(cardId).getName().equals(name.toLowerCase())) {
        foundCardId = cardId; // stores the found card id 
        return true; // if card is found return true
      }
    }
    return false; // if no card found return false
  }

  /**
   * getter for foundCardId.
   *
   * @return int (the foundCardId) 
   */
  public int getFoundCardId() {
    return this.foundCardId;
  }

  /**
   * getter for size.
   *
   * @return int (the hashmap size) 
   */
  public int getSize() {
    return cardsMap.size();
  }

  /**
   * takes in a cardId (the key of the card). 
   *
   * @return Card (the found card instance) 
   */
  public Card getCard(int cardId) {
    currCard = cardsMap.get(cardId); // gets a Card from the card Id 
    return this.currCard;
  }
}