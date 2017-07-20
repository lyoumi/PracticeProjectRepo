package RPG.classes.Items.items;

import RPG.classes.Items.EquipmentItems;
import RPG.classes.abilities.Magic;

/**
 * Created by pikachu on 17.07.17.
 */
public interface Item {
    EquipmentItems EQUIPMENT_ITEMS();
    Magic getMagic();
}
