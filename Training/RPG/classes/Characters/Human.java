package Training.RPG.classes.Characters;

import Training.RPG.classes.Items.Items;

import java.util.List;

/**
 * Created by pikachu on 13.07.17.
 */
public interface Human {
    int getDamage();

    int applyDamage(int damage);

    int getHitPoint();

    void setHitPoint(int hitPoint);

    List<Items> getInventory();

    int getLevel();

    double getExperience();

    void setExperience(double experience);

    double expToNextLevel();

    void changeLevel();

    void setDamage(int damage);

    int getAgility();

    void setAgility(int agility);

    int getIntelligence();

    void setIntelligence(int intelligence);

    int getPower();

    void setPower(int power);
}
