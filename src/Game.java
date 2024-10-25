import java.util.Scanner; //Importing the scanner class to take in user's input.
import java.util.ArrayList; //Importing ArrayList to manage lists of different characters.
import java.util.Random; //Importing random to help randomize character selections during the game.


public class Game {

    //Declaring teams and scanner to take in user's input.
    private Team Heroes;
    private Team Villians;
    private Team selectedTeam;
    private Scanner scanner;


    //Constructor for the Game class
    public Game(){
        this.Heroes = new Team("Team Hero"); //Heroes Team
        
    }
}
