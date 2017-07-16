package Training.RPG.classes.Monsters;

import Training.RPG.classes.Items.Items;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pikachu on 13.07.17.
 */
public interface Monster {
    int getExperience();
    int damage();
    int applyDamage(int applyDamage);
    int getHitPoint();
    void setHitPoint(int hitPoint);
    LinkedList<Items> getInventory();
}
