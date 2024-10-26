import java.util.Scanner;

public class MainMenu {
    

    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(); // Initialize the Game class to handle game setup and gameplay

        while (true) {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Play");
            System.out.println("2. Quit");
            System.out.print("Enter your choice: ");
            
            String userInput = scanner.nextLine();

           if (userInput.equalsIgnoreCase("Quit") || userInput.equalsIgnoreCase("2")) {
                System.out.println("Thanks for playing!");
                break;
           } else if (userInput.equalsIgnoreCase("Play") || userInput.equalsIgnoreCase("1")){
                game.StartGame();
           } else {
                System.out.println("Invalid choice! Please try again.");
           }
        }
    }
}

