public class Character {
    String name;
    int health;
    int strength;
    int defense;

    // Constructor
    public Character(String name, int health, int strength, int defense) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
    }

    // Method to attack another character
    public void attack(Character target) {
        int damage = (this.strength - target.defense) + (int) (Math.random() * 6);
        if (damage > 0) {
            target.health -= damage;
            System.out.println(this.name + " attacked " + target.name + " and dealt " + damage + " damage!");
        } else {
            System.out.println(target.name + " blocked the attack!");
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}