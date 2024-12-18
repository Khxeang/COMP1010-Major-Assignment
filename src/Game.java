import java.util.Scanner; //Importing the scanner class to take in user's input.
import java.util.ArrayList; //Importing ArrayList to manage lists of different characters.
import java.util.Random; //Importing random to help randomize character and action selections during the game.


public class Game {

    //Declaring teams and scanner to take in user's input.
    public Team Heroes;
    public Team Villians;
    public Team AntiHeroes;
    public Team selectedTeam; //This will keep track of the team that the user have selected.
    public Scanner scanner;

    private int totalRounds = 3; //rounds to win the game
    private int heroWins = 0; //track current rounds
    private int villianWins = 0;// track players total wins


    //Constructor for the Game class
    public Game(){
        this.Heroes = new Team("Team Hero"); //Heroes Team.
        this.Villians = new Team("Team Villian"); //Villians Team.
        this.AntiHeroes = new Team("Team Anti-Hero"); //Anti-Hero Team.
        this.scanner = new Scanner(System.in); //Scanner for user's input.

        Item Satelite = new Item("Satelite beam attack", 50, 0);
        Item VibraniumShield = new Item("Vibranium Shield", 0, 50);
        Item nanoSuit = new Item("Nano suit", 50, 0);
        Item stormbreakerMjolnir = new Item("Stormbreaker and Mjolnir", 0, 300);
        Item dualPistols = new Item("Dual Pistols", 0, 125);
        Item daggers = new Item("Summon Daggers", 0, 115);
        Item crescentDarts = new Item("Crescent Darts", 0, 150);
        Item magicStaff = new Item("Magic Staff", 0, 200);
        Item infinityGauntlet = new Item("Infinity Gauntlet", 0, 200);
        Item helmet = new Item("Helmet Power Channel", 0, 20); // Passive damage boost
        Item aiRobots = new Item("AI Robots", 0, 150);
        Item silverSurferAssists = new Item("Silver surfer attack", 0, 150);

        //Adding characters into the Hero Team.
         Heroes.AddCharacter(new Heroes("Iron Man", 350, 300, 12, 18, 100, 150,"Arc Reactor",Satelite));
         Heroes.AddCharacter(new Heroes("Captain America", 250, 250, 8, 15, 0, 250,"Mjolnir attack" , VibraniumShield));
         Heroes.AddCharacter(new Heroes("Spider-Man", 200, 100, 6, 10, 0, 200,"nano suit Instant kill mode",nanoSuit));
         Heroes.AddCharacter(new Heroes("Thor", 500, 150, 15, 20, 300, 250, "Odin's Power", stormbreakerMjolnir));
 
         //Adding characters into the Villian Team.
         Villians.AddCharacter(new Villains("Thanos", 600, 300, 20, 25, 200, 400, "Infinity Gauntlet Blast", infinityGauntlet));
         Villians.AddCharacter(new Villains("Magneto", 400, 300, 10, 15, 0, 300, "Item Disable", helmet));
         Villians.AddCharacter(new Villains("Ultron", 400, 300, 12, 18, 150, 350, "Drone Swarm", aiRobots));
         Villians.AddCharacter(new Villains("Galactus", 800, 50, 5, 10, 150, 400, "Planet Devourer's Grasp", silverSurferAssists));
 
         //Adding characters into the Anti-Hero Team.
         AntiHeroes.AddCharacter(new AntiHeroes("Deadpool", 400, 50, 13, 20, 125, 300, "Resurrection Strike", dualPistols));
         AntiHeroes.AddCharacter(new AntiHeroes("Loki", 450, 150, 14, 18, 115, 300, "Defense Confusion", daggers));
         AntiHeroes.AddCharacter(new AntiHeroes("MoonKnight", 400, 250, 16, 21, 150, 100, "Full Moon Drain", crescentDarts));
         AntiHeroes.AddCharacter(new AntiHeroes("Gambit", 200, 175, 12, 20, 200, 350, "Magic Card Strike", magicStaff));

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
            Random randaction = new Random();

            for(Character PlayerCharacter : PlayerTeam){
                Character OpponentCharacter = OpponentTeam.GetRandomCharacter();

                System.out.println("\n" + PlayerCharacter.getName() + " (Player) VS " + OpponentCharacter.getName() + " (Opponent)");

                //Loop until one of the characters is defated in the fight-off
                while(PlayerCharacter.isAlive() && OpponentCharacter.isAlive()){
                    //Displaying options to the user
                    System.out.println("\nChoose an action:");
                    System.out.println("1. Use Basic Attack");
                    System.out.println("2. Use Item");
                    System.out.println("3. Use Ultimate Attack");

                    String choice =  scanner.nextLine().trim();


                    if(choice.equalsIgnoreCase("Basic Attack") || choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("Use Basic Attack")){
                        //Perform Basic Attack on the opponent
                        PlayerCharacter.attack(OpponentCharacter);
                        System.out.println(PlayerCharacter.getName() + " attacked " + OpponentCharacter.getName() + " with a basic attack ");
                    } else if (choice.equalsIgnoreCase("Item") || choice.equalsIgnoreCase("Use Item") || choice.equalsIgnoreCase("2")){
                        //Use item to attack if available
                        if(PlayerCharacter.itemCooldownTimer == 0){
                            PlayerCharacter.useItem(OpponentCharacter);
                            PlayerCharacter.itemCooldownTimer = PlayerCharacter.itemCooldown; //Reset item cooldown
                            System.out.println(PlayerCharacter.getName() + " used an item on " + OpponentCharacter.getName() + "!");
                        } else{
                            System.out.println("Item is on cooldown. Turns remaining: " + PlayerCharacter.itemCooldownTimer);
                            continue;
                    }
 
                } else if (choice.equalsIgnoreCase("Ulitmate Attack") || choice.equalsIgnoreCase("use ultimate attack") || choice.equalsIgnoreCase("3")){
                    //Use item to attack if available
                    if(PlayerCharacter.ultimateCooldownTimer == 0){
                        PlayerCharacter.useItem(OpponentCharacter);
                        PlayerCharacter.ultimateCooldownTimer = PlayerCharacter.itemCooldown; //Reset item cooldown
                        System.out.println(PlayerCharacter.getName() + " used an ultimate on " + OpponentCharacter.getName() + "!");
                    } else{
                        System.out.println("Ultimate is on cooldown. Turns remaining: " + PlayerCharacter.ultimateCooldownTimer);
                        continue;
                    }
                }
                
                
                else {
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4");
                    continue; //Will keep the game running
                }
            }
                // Check if opponent is defeated
                if(!OpponentCharacter.isAlive()){
                    System.out.println(OpponentCharacter.getName() + " has been defeated!");
                    OpponentTeam.GetCharacterList().remove(OpponentCharacter); // Remove defeated characters
                    if(OpponentTeam.GetCharacterList().size() > 0){
                        OpponentCharacter = OpponentTeam.GetRandomCharacter(); // Get a new opponent to fight
                    } else {
                        break; //Exit if no opponent left
                    }
    
                }

                // Opponent's turn to attack if they are still alive
                if(OpponentCharacter.isAlive()){
                    //Randomize opponent's action between basic attack, item. or ultimate with the same cooldown logic)
                    int OpponentChoice = randaction.nextInt(3);
                    if(OpponentChoice == 0){
                        OpponentCharacter.attack(PlayerCharacter);
                        System.out.println(OpponentCharacter.getName() + " attacked " + PlayerCharacter.getName() + "with a baisc attack.");
                    } else if(OpponentChoice == 1 && OpponentCharacter.itemCooldownTimer == 0){
                        OpponentCharacter.useItem(PlayerCharacter);
                        OpponentCharacter.itemCooldownTimer = OpponentCharacter.itemCooldown;
                        System.out.println(OpponentCharacter.getName() + " used an item on " + PlayerCharacter.getName() + "!");
                    } else if(OpponentChoice == 2 && OpponentCharacter.ultimateCooldownTimer == 0){
                        OpponentCharacter.useUltimate(PlayerCharacter);
                        OpponentCharacter.ultimateCooldownTimer = OpponentCharacter.ultimateCooldown;
                        System.out.println(OpponentCharacter.getName() + " used their ultimate attack on " + PlayerCharacter.getName() + "!");
                    } else{
                        OpponentCharacter.attack(PlayerCharacter); //Default basic attack
                        System.out.println(OpponentCharacter.getName() + " attacked " + PlayerCharacter.getName() + " with a basic attack.");
                    }

                }
                //Check if the player's character is defeated
                if(!PlayerCharacter.isAlive()){
                    System.out.println(PlayerCharacter.getName() + " has been defeated!");
                    PlayerTeam.remove(PlayerCharacter); //Removed defeated character from player's team
                    if(PlayerTeam.size() > 0){
                        PlayerCharacter = PlayerTeam.get(0); // Get the next available character
                    } else {
                        break;
                    }

                }

                // Reduce cooldown timers at the end of each turn
                if (PlayerCharacter.itemCooldownTimer > 0) {
                    PlayerCharacter.itemCooldownTimer--;
                }
                if (PlayerCharacter.ultimateCooldownTimer > 0) {
                    PlayerCharacter.ultimateCooldownTimer--;
                }
                if (OpponentCharacter.itemCooldownTimer > 0){
                    OpponentCharacter.itemCooldownTimer--;
                }
                if(OpponentCharacter.ultimateCooldownTimer > 0){
                    OpponentCharacter.ultimateCooldownTimer--;
                }


                if(PlayerTeam.size() == 0){
                    System.out.println("Your team has been defeated!");
                } else if(OpponentTeam.GetCharacterList().size() == 0){
                    System.out.println("You defeated all characters in the opponent's team! Congrats!");
                }

                // Check if item is ready to use
                if(PlayerCharacter.itemCooldownTimer == 0){
                    PlayerCharacter.useItem(OpponentCharacter); // Use item
                    PlayerCharacter.itemCooldownTimer = PlayerCharacter.itemCooldown; // Reset item cooldown
                } else { // Use basic attack if no ultimate or item is available
                    PlayerCharacter.attack(OpponentCharacter);
                    PlayerCharacter.itemCooldownTimer--; // Reduce item cooldown timer
                }

                // Check if ultimate ability is ready to use
                if (PlayerCharacter.ultimateCooldownTimer == 0) {
                    PlayerCharacter.useUltimate(OpponentCharacter); // Use ultimate ability
                    PlayerCharacter.ultimateCooldownTimer = PlayerCharacter.ultimateCooldown; // Reset ultimate cooldown
                    } else {// Use basic attack if no ultimate or item is available
                        PlayerCharacter.attack(OpponentCharacter);
                        PlayerCharacter.ultimateCooldownTimer--; // Reduce ultimate cooldown timer
                        }
                        
                        // Check if the opponent character is defeated
                        if (!OpponentCharacter.isAlive()) {
                            System.out.println(OpponentCharacter.getName() + " has been defeated!");
                        } else {
                            // Opponent attacks back if still alive
                            OpponentCharacter.attack(PlayerCharacter);
                            if (!PlayerCharacter.isAlive()) {
                                System.out.println(PlayerCharacter.getName() + " has been defeated!");
                            }
                        }
                    }
                }
        }