package RPG.classes.Items.items.armors;

import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.items.Item;

/**
 * Created by pikachu on 17.07.17.
 */
public interface Armor extends Item {
    EquipmentItems EQUIPMENT_ITEMS();
    int getItemLevel();
    int getDefence();
    String getName();
}
