package Training.RPG.classes.Characters;

import Training.RPG.classes.Items.Items;

import java.util.List;

/**
 * Created by pikachu on 13.07.17.
 */
public interface Human {
    int damage();
    int applyDamage(int damage);
    int getHitPoint();
    void setHitPoint(int hitPoint);
    List<Items> inventory();
    int getLevel();
}
