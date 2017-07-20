package RPG.classes.Items.items.armors.boots;

import RPG.classes.Characters.Human;
import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.items.armors.Armor;
import RPG.classes.abilities.Magic;
import RPG.classes.abilities.buffs.buffs.ArchersBuff;

/**
 * Created by pikachu on 17.07.17.
 */
public class IronBoots implements Armor{

    private int defence;
    private int itemLevel;
    private Magic magic;
    Human human;

    public IronBoots(Human human){
        this.human = human;
        this.itemLevel = human.getLevel() + 1;
        this.defence = getItemLevel() * 10 + 5;
        this.magic = ArchersBuff.getMagic(human);
    }

    @Override
    public EquipmentItems EQUIPMENT_ITEMS() {
        return EquipmentItems.LEGS;
    }

    @Override
    public int getItemLevel() {
        return itemLevel;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public String getName() {
        return IronBoots.class.getSimpleName();
    }

    @Override
    public Magic getMagic() {
        return magic;
    }

    @Override
    public String toString(){
        return IronBoots.class.getSimpleName() + ": DEF +" + getDefence();
    }
}
