package RPG.classes.Items.items.weapons.weapons;

import RPG.classes.Characters.Human;
import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.items.weapons.Weapons;
import RPG.classes.abilities.Magic;
import RPG.classes.abilities.buffs.buffs.ArchersBuff;

/**
 * Created by pikachu on 17.07.17.
 */
public class Bow implements Weapons {
    private int damage;
    private int itemLevel;
    private Human human;
    private Magic magic;

    public Bow(Human human){
        this.human = human;
        this.itemLevel = human.getLevel();
        this.damage = getLevel() * 10 + 5;
        this.magic = ArchersBuff.getMagic(human);
    }

    @Override
    public Magic getMagic() {
        return magic;
    }

    @Override
    public EquipmentItems EQUIPMENT_ITEMS() {
        return EquipmentItems.HANDS;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getLevel() {
        return itemLevel;
    }

    @Override
    public String getName() {
        return Bow.class.getSimpleName();
    }

    @Override
    public String toString(){
        return Bow.class.getSimpleName() + ": " + " ATK +" + getDamage();
    }
}
