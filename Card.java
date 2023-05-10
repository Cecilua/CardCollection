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
    private static final String DEFAULT_IMG = "default_card_back.png";

    /**
     * Constructor for objects of class Card
     */
    public Card(String nm, double val, String img)
    {
        // initialise instance variables
        this.name = nm;
        this.value = val; 
        // if user selects cancel instead of selecting an image, default image is set 
        if (img == null) {
            this.image = DEFAULT_IMG;
        } else {
            this.image = img;  
        }
    }
    
    /**
     * getter for name 
     * @return String the name of the card 
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * getter for value 
     * @return double the market value of the card 
     */
    public double getValue() {
        return this.value;
    }
    
    /** 
     * getter for image 
     * @return String the image file name 
     */
    public String getImage() {
        return this.image;
    }
    
    /**
     * takes left and top coordinates and displays card image on GUI
     */
    public void displayCard(int locX, int locY) {
        final double WIDTH = 127;
        final double HEIGHT = 176;
        
        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
    }
}
