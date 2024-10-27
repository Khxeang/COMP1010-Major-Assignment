public class Client {
    public static void main(String[] args) {
        System.out.println("Welcome To The Avengers!");

        // Create characters
        Character ironMan = new Character("Iron Man", 100, 20, 10);
        Character thor = new Character("Thor", 120, 25, 12);

        // Battle simulation
        while (ironMan.isAlive() && thor.isAlive()) {
            ironMan.attack(thor);

            if (!thor.isAlive()) {
                System.out.println("Thor is defeated!");
                break;
            }

            thor.attack(ironMan);

            if (!ironMan.isAlive()) {
                System.out.println("Iron Man is defeated!");
            }
        }
    }

    


    

    
}
