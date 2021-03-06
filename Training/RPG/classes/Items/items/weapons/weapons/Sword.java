package RPG.classes.Items.items.weapons.weapons;

import RPG.classes.Characters.Human;
import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.items.weapons.Weapons;
import RPG.classes.abilities.Magic;
import RPG.classes.abilities.buffs.buffs.ArchersBuff;

/**
 * Created by pikachu on 17.07.17.
 */
public class Sword implements Weapons {

    private int damage;
    private int level;
    private Human human;
    private Magic magic;

    public Sword(Human human){
        this.human = human;
        this.level = human.getLevel() + 1;
        this.damage = this.getLevel() * 10 + 5;
        this.magic = ArchersBuff.getMagic(human);
    }

    @Override
    public Magic getMagic() {
        return magic;
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
