import java.util.Random;

public class Character {
    String name; //Name of the character.
    String type; //Character type (Hero, Villian, Anti-Hero)
    int health; //Character's health points.
    int defense; //Character's defense points.
    int passiveMinDamage; //Character's minimum passive damage. 
    int passiveMaxDamage; //Character's maximum passive damage.
    int itemDamage; //Damage dealt by character's items.
    int UltimateDamage; //Damage dealt by character's ultimate attacks.
    String ultimateAttackName; // Name of the ultimate attack

    public int ultimateCooldown = 3;
    public int ultimateCooldownTimer = 0;
    private int level = 1;
    private int xp = 0;
    private int attacksMade = 0; // To track critical attacks
    private static final int MAX_LEVEL = 50;
    private static final int XP_PER_LEVEL = 10;
    private int baseAttack = 10;
    private int criticalAttack = 20;
    private Random random = new Random(); // To randomize XP gained per attack

    // Constructor for character initialization
    public Character(String name, int health, int defense, int passiveMinDamage, int passiveMaxDamage, int itemDamage, int UltimateDamage, String ultimateAttackName,Item Item) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.defense = defense;
        this.passiveMinDamage = passiveMinDamage;
        this.passiveMaxDamage = passiveMaxDamage;
        this.itemDamage = itemDamage;
        this.UltimateDamage = UltimateDamage;
        this.ultimateAttackName = ultimateAttackName;
    }
    //Method to increase Health
    public void increaseHealth(int healthBoost) {
        this.health += healthBoost;
        System.out.println(this.name + " received a health boost of " + healthBoost + " points!");
    }
    // Method to increase damage
    public void increaseDamage(int damageBoost) {
        this.itemDamage += damageBoost;
        System.out.println(this.name + " received a damage boost of " + damageBoost + " points!");
    }

    // Method to attack another character
    public void attack(Character target) {
        boolean isCriticalHit = (attacksMade % 3 == 0) || Math.random() < 0.15; // Critical hit every third attack or with a 15% random chance
    int damage = DetermineDamage(isCriticalHit); // Calculate damage based on critical or regular hit

    // Inflict damage on the target
    target.TakeDamage(damage);

    // Gain a random amount of XP between 2 and 5 for each attack
    int xpGained = 2 + (int)(Math.random() * 4);
    gainXp(xpGained); // Update character's XP and check for level-up

    // Display attack details
    String hitType = isCriticalHit ? "critical" : "regular";
    System.out.println(this.name + " attacked " + target.getName() + " with a " + hitType + " hit and dealt " + damage + " damage!");

    // Check if the target is defeated and award extra XP if so
    if (!target.isAlive()) {
        gainXp(XP_PER_LEVEL); // Immediate level-up XP bonus on kill
        System.out.println(target.getName() + " has been defeated by " + this.name + "!");
    }

    attacksMade++; // Track the number of attacks for critical hit logic
        System.out.println(this.name + "attacked" + target.getName() + "and dealt" + damage + "damage!"); //Make announcement of the attack.
    }

    //method to use the ultimate ability
    public void useUltimate(Character target){
        if(ultimateCooldownTimer == 0){
            target.TakeDamage(UltimateDamage);
            System.out.println(this.name + "used" + this.ultimateAttackName + "on" + target.getName()+ "and dealth" + UltimateDamage + "damage!");
            ultimateCooldownTimer = ultimateCooldown;
        } else {
            System.out.println(this.name + "'s ultimate is on cooldown for " + ultimateCooldownTimer + " more turns.");
            }
        }
        public void reduceCooldown() {
            if (ultimateCooldownTimer > 0) {
                ultimateCooldownTimer--;
            }
        }

        public void gainXp(int xpGained) {
            if (level < MAX_LEVEL) {
                this.xp += xpGained;
                while (this.xp >= XP_PER_LEVEL && level < MAX_LEVEL) {
                    levelUp();
                    this.xp -= XP_PER_LEVEL; // Deduct XP for each level up
                }
            }
        }
        private void levelUp() {
            level++;
            baseAttack += 0.5;
            criticalAttack += 2;
            UltimateDamage += 10;
            health += 10;
            defense += 5;
            System.out.println(name + " leveled up! Level: " + level);
        }
        

        //Function that determine the damage based on character's passive attack range.
    private int DetermineDamage(boolean isCriticalHit){
        int baseDamage = (int)(baseAttack + passiveMinDamage + random.nextInt(passiveMaxDamage - passiveMinDamage)); //Randomizng the output damage of every character between min and max.
        return isCriticalHit ? (int)(baseDamage + criticalAttack) : baseDamage;
    }

    //Function for inflicting damage and reducing character's health
    public void TakeDamage(int damage){
        this.health -= Math.max(damage - this.defense, 0); //Will inflict damage onto characters reducing their defense points first.
    }

    //Function to check if the character is alive or not.
    public boolean isAlive() {
        return this.health > 0;
    }

    //Functions to retrieve the name, health, the defense points, and type of the character.
    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public int getHealth(){
        return health;
    }

    public int getDefense(){
        return defense;
    }

    public int getAttack(){
        return baseAttack;
    }

    public int getUltimate() {
        return UltimateDamage;
    }

    // Getter for item damage
    public int getItem() {
        return itemDamage;
    }
}