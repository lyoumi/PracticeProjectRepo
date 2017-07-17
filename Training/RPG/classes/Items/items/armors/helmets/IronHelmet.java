package RPG.classes.Items.items.armors.helmets;

import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.items.armors.Armor;

/**
 * Created by pikachu on 17.07.17.
 */
public class IronHelmet implements Armor{

    private int defence;
    private int itemLevel;

    public IronHelmet(int itemLevel){
        this.itemLevel = itemLevel;
        this.defence = getItemLevel() * 10 + 5;
    }

    @Override
    public EquipmentItems EQUIPMENT_ITEMS() {
        return EquipmentItems.HEAD;
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
        return IronHelmet.class.getSimpleName();
    }

    public String toString(){
        return IronHelmet.class.getSimpleName() + ": DEF +" + getDefence();
    }
}
