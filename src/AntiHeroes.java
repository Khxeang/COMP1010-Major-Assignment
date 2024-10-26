public class AntiHeroes extends Character{ //Anti-Hero class is inheriting from the Character class.
    public AntiHeroes(String name, int health, int defense, int passiveMinDamage, int passiveMaxDamage, int itemDamage, int UltimateDamage){
        super(name, health, defense, passiveMinDamage, passiveMaxDamage, itemDamage, UltimateDamage); //Calls the Character constructor to set name, health, defense, passiveMinDamage, passiveMaxDamage, itemDamage, and UltimateDamage.
    }

    public String getType(){
        return "Anti-Hero";
    }
    
}
