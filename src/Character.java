public class Character {
    String name; //Name of the character.
    int health; //Character's health points.
    int defense; //Character's defense points.
    int passiveMinDamage; //Character's minimum passive damage. 
    int passiveMaxDamage; //Character's maximum passive damage.
    int itemDamage; //Damage dealt by character's items.
    int UltimateDamage; //Damage dealt by character's ultimate attacks.


    // Constructor for character initialization
    public Character(String name, int health, int defense, int passiveMinDamage, int passiveMaxDamage, int itemDamage, int UltimateDamage) {
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.passiveMinDamage = passiveMinDamage;
        this.passiveMaxDamage = passiveMaxDamage;
        this.itemDamage = itemDamage;
        this.UltimateDamage = UltimateDamage;
    }

    // Method to attack another character
    public void attack(Character target) {
        int damage = DetermineDamage(); //Call a function that calculate the attack damage.
        target.TakeDamage(damage); //Inflict damage upon the target.
        System.out.println(this.name + "attacked" + target.getName() + "and dealt" + damage + "damage!"); //Make announcement of the attack.
    }

    //Function that determine the damage based on character's passive attack range.
    private int DetermineDamage(){
        return passiveMinDamage + (int)(Math.random()*(passiveMaxDamage - passiveMinDamage)); //Randomizng the output damage of every character between min and max.
    }

    //Function for inflicting damage and reducing character's health
    public void TakeDamage(int damage){
        this.health -= Math.max(damage - this.defense, 0); //Will inflict damage onto characters reducing their defense points first.
    }

    //Function to check if the character is alive or not.
    public boolean isAlive() {
        return this.health > 0;
    }

    //Functions to retrieve the name, health, and the defense points of the character.
    public String getName(){
        return name;
    }


    public int getHealth(){
        return health;
    }

    public int getDefense(){
        return defense;
    }
}