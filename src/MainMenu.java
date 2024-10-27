import java.util.Scanner;

public class MainMenu {
    

    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(); // Initialize the Game class 

        while (true) {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Play");
            System.out.println("2. Quit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine()

            if (choice.equalsIgnoreCase("Quit") || choice.equalsIgnoreCase("2")) {
                System.out.println("Thanks for playing!");
                break;
            } else if (choice.equalsIgnoreCase("Play") || choice.equalsIgnoreCase("1")) {
                game.StartGame();// Begin the game when "Play" is selected
            } else {
                System.out.println("Invalid choice! Please try again."); // Handle invalid input
            }
        }
        scanner.close();
    }
}

