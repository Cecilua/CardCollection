import ecs100.*;
/**
 * support class for card 
 * each card has a name, monetary value and image. 
 *
 * @author Cecilia Kuntze
 * @version 09/05/2023
 */
public class Card
{
    // instance variables 
    private String name; // name of the card
    private double value; // cards market value 
    private String image; // the cards image 

    /**
     * Constructor for objects of class Card
     */
    public Card(String nm, double val, String img)
    {
        // initialise instance variables
        this.name = nm;
        this.value = val; 
        this.image = img; 
    }
}
