import java.util.function.Consumer;

public class Item {
    private String name;
    private int healthBoost;
    private int damageBoost;

    // Constructor for initializing item properties
    public Item(String name, int healthBoost, int damageBoost) {
        this.name = name;
        this.healthBoost = healthBoost;
        this.damageBoost = damageBoost;
    }
    // Applies the item's effect to a character
    public void applyEffect(Character character) {
        character.increaseHealth(healthBoost);
        character.increaseDamage(damageBoost);
    }

    public String getName() {
        return name;
    }

}
