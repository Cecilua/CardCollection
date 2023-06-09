import ecs100.*;
import java.text.DecimalFormat;
/**
 * class handles GUI functionality.
 *
 * @author Cecilia Kuntze
 * @version 22/05/23
 */

public class GUI {
  // instance variables
  private Cards cards;
  private Card card;
  private final int FOUND_X = 200; // the x pos of found card
  private final int FOUND_Y = 100; // the y pos of found card 

  /**
   * constructor for objects of class GUI.
   */
  public GUI() {
    // initialise instance variables
    cards = new Cards();
    UI.initialise();

    // add buttons
    UI.addButton("add a card", this::addCard);
    UI.addButton("find a card", this::findCard);
    UI.addButton("show collection", this::displayAll);
    UI.addButton("hide all details", this::clearAll);
    UI.addButton("quit", UI::quit);
    UI.addButton("help", this::help);

    // set up mouse 
    UI.setMouseListener(this::doMouse);

    // window settings

    // set constants
    final int WINDOW_WIDTH = 1100; // window width  
    final int WINDOW_HEIGHT = 618; // window height
    final double WINDOW_RATIO = 0.35; // window ratio 

    UI.setWindowSize(WINDOW_WIDTH, WINDOW_HEIGHT); // set window size 
    UI.setDivider(WINDOW_RATIO); // set text to graphics pane ratio

    this.help(); // show program instructions on startup 
  }

  /**
   * displays program intructions. 
   */
  public void help() {
    // clear panes
    this.clearAll();

    UI.println("---------------- getting started ----------------");
    UI.println("<-- use the buttons on the left to navigate");
    UI.println("\nthen follow the instructions on this column!\n");
    UI.println("cards (if displayed) will appear on the right -->");
  }

  /**
   * adds a card to the collection. 
   */
  public void addCard() {
    // clear panes
    this.clearAll();

    // set constants 
    final double MIN_VALUE = 0.01; // min card market value
    final double MAX_VALUE = 10000000; // max card market value

    final int MIN_LENGTH = 3; // min  name length
    final int MAX_LENGTH = 20; // max name length 

    UI.println("----- add a card -----");

    // ask the user for details 
    String nm = this.isValidString("enter name: ", MIN_LENGTH, MAX_LENGTH).toLowerCase();
    double val = this.isValidDouble("enter market value: ", MIN_VALUE, MAX_VALUE);

    // add an image to display in GUI
    String img = UIFileChooser.open("choose image file: ");

    if (cards.findCard(nm)) {
      UI.println("\nthis card is already in the collection !!");
    } else {
      // add the card to the collection
      cards.addCard(nm, val, img);
      UI.println("\ncard added to collection");
    }
  }

  /**
   * takes a question, a minimum and a maximum number.
   *
   * @return double if valid 
   */
  public double isValidDouble(String question, double min, double max) {
    final int BUFFER = 1;
    double number = min - BUFFER; // initialise the number as less than the minimum 
    // keep asking for a number until it is at least the min and at most the max
    while (number < min || number > max) {
      number = UI.askDouble(question);
      if (number < min) {
        UI.println("number must be at least " + min);
      } else if (number > max) {
        UI.println("number must be at most " + max);
      }
    }
    return number; // once a valid number is given, return number
  }

  /**
   * takes a question, a minimum and maximum character number.
   *
   * @return String if valid
   */
  public String isValidString(String question, int min, int max) {
    boolean asking = true;
    String inputStr = "";
    while (asking) {
      inputStr = UI.askString(question).trim(); // ask user for a string
      if (inputStr.equals("")) {
        UI.println("you must enter something!"); // error message if string is empty
      } else if (inputStr.length() < min) {
        UI.println("you must enter at least " + min + " characters");
      } else if (inputStr.length() > max) {
        UI.println("you must enter at most " + max + " characters");
      } else {
        asking = false; // if string is valid, stop asking
      }
    }
    return inputStr; // return the input string
  }

  /**
   * searches for a card, and display if found.
   */
  public void findCard() {
    // clear panes
    this.clearAll(); 

    // error message if the hashmap is empty
    if (cards.getSize() == 0) {
      UI.println("there are no cards to find!!");
      UI.println("you can add cards by clicking the \"add a card\" button");
    } else {
      // if the hashmap is not empty --> search for a card
      UI.println("----- find a card -----");
      String cardName = UI.askString("search card name: ").trim(); // ask user for card name
      if (cardName.equals("")) {
        // if user enters nothing --> display whole collection
        UI.println("\nyou didn't search for anything!");
        UI.println("displaying whole collection...");
        UI.sleep(1000);
        this.displayAll();
      } else if (cards.findCard(cardName)) {
        // if card is found, show its details
        UI.println("\ncard found!");
        this.printDetails(cards.getFoundCardId());
        UI.println("click on the card to hide its details.");
        card.displayCard(FOUND_X, FOUND_Y);
      } else {
        UI.println("\ncard not found :("); // if card isnt found --> error message
      }
    }
  }

  /**
   * takes cardId and prints the cards information. 
   */
  public void printDetails(int cardId) {
    // learnt about decimal format here: 
    // https://www.javatpoint.com/how-to-round-double-and-float-up-to-two-decimal-places-in-java
    // note that this does not actually round the double, only prints it in the rounded format
    DecimalFormat dformat = new DecimalFormat("0.00"); // create the decimal format

    card = cards.getCard(cardId);
    UI.println("\n----- " + card.getName() + " -----");
    UI.println("market value: $" + dformat.format(card.getValue()) + "\n");
  }

  /**
   * displays all cards in the collection. 
   */
  public void displayAll() {
    // clear panes
    this.clearAll();

    // error message if hashmap is empty 
    if (cards.getSize() == 0) {
      UI.println("your collection is... empty?");
      UI.println("you can add cards by clicking the \"add a card\" button");
    } else {
      // if hashmap is not empty --> display collection 

      final int START_X = 147; //  starting x pos of cards
      int locY = 20; // y pos of cards 
      final int Y_JUMP = 196; // the ammount the y pos moves per row 
      final double ROW_NUM = 3; // the number of cards in each row
      int cardId = 1; // the id of the card to be displayed 

      // learnt about ceiling function here:
      // https://www.programiz.com/java-programming/library/math/ceil
      double rowAmmount = Math.ceil(cards.getSize() / ROW_NUM); // calculate the ammount of rows  

      // display all cards 
      for (int i = 0; i < rowAmmount; i++) {
        for (int a = 1; a <= ROW_NUM; a++) {
          if (cardId <= cards.getSize()) {
            card = cards.getCard(cardId); // get the card instance
            card.displayCard(START_X * (a), locY); // display the card
            cardId++; // increment cardId 
          }
        }
        locY += Y_JUMP; // update y pos 
      }
      UI.println("----- your card collection!! -----");
      UI.println("click on a card to show its details.");
    }
  }

  /**
   * clears the text and graphics pane and set all card visibilities to false.
   */
  public void clearAll() {
    UI.clearText(); // clear the text pane 
    UI.clearGraphics(); // clear the graphics pane 
    
    for (int i = 1; i <= cards.getSize(); i++) {
      card = cards.getCard(i); // get the current card
      card.setVisible(false); // set card visibility to false. 
    }
  }

  /**
   * callback method to mouse listener. 
   */
  public void doMouse(String action, double x, double y) {
    if (action.equals("clicked")) {
      for (int i = 1; i <= cards.getSize(); i++) {
        card = cards.getCard(i); // get the current card
        if (card.isVisible() && (x >= card.getLeft()) && x <= card.getRight() 
            && y >= card.getTop() && y <= card.getBottom()) {
          // check if the card clicked is the found card
          if (card.getLeft() == FOUND_X && card.getTop() == FOUND_Y) {
            this.clearAll(); // hide details 
          } else {
            this.printDetails(i); // if card in collection is clicked, print its details
          }
        }
      }
    }
  }
}