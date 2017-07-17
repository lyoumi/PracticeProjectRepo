package RPG.classes.Items.items.armors.boots;

import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.items.armors.Armor;

/**
 * Created by pikachu on 17.07.17.
 */
public class IronBoots implements Armor{

    private int defence;
    private int itemLevel;

    public IronBoots(int itemLevel){
        this.itemLevel = itemLevel;
        this.defence = getItemLevel() * 10 + 5;
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
    public String toString(){
        return IronBoots.class.getSimpleName() + ": DEF +" + getDefence();
    }
}
