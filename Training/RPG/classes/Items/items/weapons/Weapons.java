package RPG.classes.Items.items.weapons;

import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.items.Item;

/**
 * Created by pikachu on 17.07.17.
 */
public interface Weapons extends Item {
    EquipmentItems EQUIPMENT_ITEMS();
    int getDamage();
    int getLevel();
    String getName();
}
