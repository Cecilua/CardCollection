import ecs100.*;
/**
 * support class for card 
 * each card has a name, monetary value and image. 
 *
 * @author Cecilia Kuntze
 * @version 09/05/2023
 */

public class Card {
  // instance variables 
  private String name; // name of the card
  private double value; // cards market value 
  private String image; // the cards image 
  private static final String DEFAULT_IMG = "default_card_back.png";

  final int WIDTH = 127; // width of card 
  final int HEIGHT = 176; // height of card

  private int left; // left of card 
  private int top; // top of card 

  /**
   * Constructor for objects of class Card.
   */
  public Card(String nm, double val, String img) {
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
   * Constructor overloading for objects of class Card. 
   */
  public Card(String nm, double val) {
    // initialise instance variables
    this(nm, val, null);
  }

  /**
   * getter for left. 
   *
   * @return int the left pos of card 
   */
  public int getLeft() {
    return this.left;
  }

  /**
   * getter for top.
   *
   * @return int the top pos of card 
   */
  public int getTop() {
    return this.top;
  }

  /**
   * getter for right.
   *
   * @return int the right pos of card
   */
  public int getRight() {
    return this.left + WIDTH;
  }

  /**
   * getter for bottom.
   *
   * @return int the bottom pos of card
   */
  public int getBottom() {
    return this.top + HEIGHT;
  }

  /**
   * getter for name.
   *
   * @return String the name of the card 
   */
  public String getName() {
    return this.name;
  }

  /**
   * getter for value.
   *
   * @return double the market value of the card 
   */
  public double getValue() {
    return this.value;
  }

  /** 
   * getter for image.
   *
   * @return String the image file name 
   */
  public String getImage() {
    return this.image;
  }

  /**
   * takes left and top coordinates and displays card image on GUI.
   */
  public void displayCard(int locX, int locY) {
    left = locX; // set left of card
    top = locY; // set top of card 

    UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
  }
}