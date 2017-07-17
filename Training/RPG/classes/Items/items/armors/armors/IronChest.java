package RPG.classes.Items.items.armors.armors;

import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.items.armors.Armor;

/**
 * Created by pikachu on 17.07.17.
 */
public class IronChest implements Armor {

    private int defence;
    private int itemLevel;

    public IronChest(int itemLevel){
        this.itemLevel = itemLevel;
        this.defence = this.getItemLevel() * 10 + 5;
    }

    @Override
    public EquipmentItems EQUIPMENT_ITEMS() {
        return EquipmentItems.ARMOR;
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
        return IronChest.class.getSimpleName();
    }

    public String toString(){
        return IronChest.class.getSimpleName() + ": DEF +" + getDefence();
    }
}
