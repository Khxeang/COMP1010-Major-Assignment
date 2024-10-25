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
            int choice = scanner.nextInt();

            if (choice == 2) {
                System.out.println("Thanks for playing!");
                break;
            } else if (choice == 1) {
                game.startGame(); // Begin the game when "Play" is selected
            } else {
                System.out.println("Invalid choice! Please try again."); // Handle invalid input
            }
        }
    }
}

