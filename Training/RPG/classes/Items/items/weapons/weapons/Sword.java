package RPG.classes.Items.items.weapons.weapons;

import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.items.weapons.Weapons;

/**
 * Created by pikachu on 17.07.17.
 */
public class Sword implements Weapons {

    private int damage;
    private int level;

    public Sword(int level){
        this.level = level + 1;
        this.damage = this.getLevel() * 10 + 5;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public EquipmentItems EQUIPMENT_ITEMS() {
        return EquipmentItems.HANDS;
    }

    @Override
    public String getName() {
        return Sword.class.getSimpleName();
    }

    public String toString(){
        return Sword.class.getSimpleName() + ": ATK +" + getDamage();
    }
}
