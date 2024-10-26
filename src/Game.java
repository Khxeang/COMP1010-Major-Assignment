import java.util.Scanner; //Importing the scanner class to take in user's input.
import java.util.ArrayList; //Importing ArrayList to manage lists of different characters.
import java.util.Random; //Importing random to help randomize character selections during the game.


public class Game {

    //Declaring teams and scanner to take in user's input.
    public Team Heroes;
    public Team Villians;
    public Team AntiHeroes;
    public Team selectedTeam; //This will keep track of the team that the user have selected.
    public Scanner scanner;


    //Constructor for the Game class
    public Game(){
        this.Heroes = new Team("Team Hero"); //Heroes Team.
        this.Villians = new Team("Team Villian"); //Villians Team.
        this.AntiHeroes = new Team("Team Anti-Hero"); //Anti-Hero Team.
        this.scanner = new Scanner(System.in); //Scanner for user's input.

        //Adding characters into the Hero Team.
         Heroes.AddCharacter(new Heroes("Iron Man", 350, 300, 12, 18, 100, 150));
         Heroes.AddCharacter(new Heroes("Captain America", 250, 250, 8, 15, 0, 250));
         Heroes.AddCharacter(new Heroes("Spider-Man", 200, 100, 6, 10, 0, 200));
         Heroes.AddCharacter(new Heroes("Thor", 500, 150, 15, 20, 300, 250));
 
         //Adding characters into the Villian Team.
         Villians.AddCharacter(new Villains("Thanos", 600, 300, 20, 25, 200, 400));
         Villians.AddCharacter(new Villains("Magneto", 400, 300, 10, 15, 0, 300));
         Villians.AddCharacter(new Villains("Ultron", 400, 300, 12, 18, 150, 350));
         Villians.AddCharacter(new Villains("Galactus", 800, 50, 5, 10, 0, 400));
 
         //Adding characters into the Anti-Hero Team.
         AntiHeroes.AddCharacter(new AntiHeroes("Deadpool", 400, 50, 13, 20, 125, 300));
         AntiHeroes.AddCharacter(new AntiHeroes("Loki", 450, 150, 14, 18, 115, 300));
         AntiHeroes.AddCharacter(new AntiHeroes("MoonKnight", 400, 250, 16, 21, 150, 100));
         AntiHeroes.AddCharacter(new AntiHeroes("Gambit", 200, 175, 12, 20, 200, 350));

    }

    public void StartGame(){
        while (true) {
            System.out.println("======================================");
            System.out.println("         Team Selection Menu              ");
            System.out.println("======================================");

            System.out.println("1. Heroes");
            System.out.println("2. Villians");
            System.out.println("3. View Teams");
            System.out.println("4. Quit");
            System.out.println("Choose a team or view teams: ");

            String userInput = scanner.nextLine();

        if(userInput.equals("Quit") || userInput.equalsIgnoreCase("4")){
            break;
            } else if(userInput.equalsIgnoreCase("View Teams") || userInput.equalsIgnoreCase("3")){
                Heroes.displayTeam();
                Villians.displayTeam();
                AntiHeroes.displayTeam();
            } else if(userInput.equalsIgnoreCase("Villians") || userInput.equalsIgnoreCase("2")){
                selectedTeam = Villians;
                break;
            } else if(userInput.equalsIgnoreCase("Heroes") || userInput.equalsIgnoreCase("1")){
                selectedTeam = Heroes;
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        //Display user's selected team and Anti-Heroes for character selection
        System.out.println("\nYou have chosen " + selectedTeam.getTeamname());
        System.out.println("Available characters in your team and Anti-Heroes you can pick from: ");
        selectedTeam.displayTeam();
        AntiHeroes.displayTeam();

        //Limiting user to only picking 3 characters to join their team.
        ArrayList<Character> PlayerTeam = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            System.out.print("Select character " + (i + 1) + " by entering their name: ");
            String CharacterChoice = scanner.nextLine().trim();

            Character ChosenCharacter = CharacterInSelectedTeamAndAntiHeroes(CharacterChoice);
            if(ChosenCharacter != null){
                PlayerTeam.add(ChosenCharacter);
                System.out.println(ChosenCharacter.getName() + " has been added to your team.");
            } else{
                System.out.println(("Invalid Choice. Pleaes Try Again."));
                i--;
            }
        }

        System.out.println("\nYour selected team:");
        for(Character character : PlayerTeam){
            System.out.println(character.getName() + " (" + character.getType() + ")");
        }


        //Start Battle Round
        Team OpponentTeam = (selectedTeam == Heroes) ? Villians : Heroes;
        battleRound(PlayerTeam, OpponentTeam);
    } 

    // Find a character in the selected team or Anti-Heroes based on user input.
        public Character CharacterInSelectedTeamAndAntiHeroes(String input) {
            ArrayList<Character> combinedList = new ArrayList<>(selectedTeam.GetCharacterList());
            combinedList.addAll(AntiHeroes.GetCharacterList());

            //Check what user input is and match by character's name
            for (Character character : combinedList){
                if(character.getName().equalsIgnoreCase(input.trim())) { // Case-insensitive match
                    return character;
                }
            }

            return null; //When Character is not found
        }

        //Battle round between player's team and opponent team
        public void battleRound(ArrayList<Character> PlayerTeam, Team OpponentTeam){
            System.out.println("\nStarting the battle between your team and " + OpponentTeam.getTeamname());


            for(Character PlayerCharacter : PlayerTeam){
                Character OpponentCharacter = OpponentTeam.GetRandomCharacter();

                System.out.println("\n" + PlayerCharacter.getName() + " (Player) VS " + OpponentCharacter.getName() + " (Opponent)");

                PlayerCharacter.attack(OpponentCharacter);
                if(!OpponentCharacter.isAlive()){
                    System.out.println((OpponentCharacter.getName() + " has been defeated!"));
                } else {
                    OpponentCharacter.attack(PlayerCharacter);
                    if(!PlayerCharacter.isAlive()){
                        System.out.println(PlayerCharacter.getName() + " has been defeated!");
                    }
                }
            }
        }


        
    }
