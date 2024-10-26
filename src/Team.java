import java.util.Scanner; //Importing the scanner class to take in user's input
import java.util.ArrayList; //Importing ArrayList to manage lists of different characters
import java.util.Random; //Importing random to help randomize character selections during the game


//Creating class to manage the team of characters ()
public class Team {
    public String Teamname; // Name of the Team (Heroes or Villians).
    public ArrayList<Character> characters; //Make an array that stores characters in the team.


    //Constructor for initializing the team.
    public Team(String name){
        this.Teamname = name; //Assigning name to the team.
        this.characters = new ArrayList<>(); //Initializing the list of characters.
    }

    public void AddCharacter(Character character){
        characters.add(character); //Adding characters to the list.
    }

    //Function to return the list of characters in the team.
    public ArrayList<Character> GetCharacterList(){
        return characters;
    }

    public Character GetRandomCharacter(){
        Random rand = new Random(); //Creat a random number generator
        return characters.get(rand.nextInt(characters.size())); //Return a random character from the team.
    }

    //Function to display team details
    public void displayTeam(){
        System.out.println("\n" + Teamname);
        for(Character character : characters){
            System.out.println("\n" + character.getName() + " (" + character.getType() + ") \n - Health: " + character.getHealth() + "\n - Defense: " + character.getDefense() + "\n - Attack: " + character.getAttack() + "\n - Item Damage: " + character.getItem() + "\n - Ultimate Damage: " + character.getUltimate());
        }
    }

    public String getTeamname(){
        return Teamname;
    }

    

} 
