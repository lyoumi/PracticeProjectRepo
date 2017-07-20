package RPG.classes.Monsters;

import RPG.classes.Items.Items;
import RPG.classes.Items.items.Item;

import java.util.LinkedList;

/**
 * Created by pikachu on 13.07.17.
 */
public interface Monster {
    int getExperience();
    int getDamage();
    int applyDamage(int applyDamage);
    int getHitPoint();
    void setHitPoint(int hitPoint);
    LinkedList<Items> getInventory();
    public Item getDroppedItems();
}
