package RPG.classes.Characters;

import RPG.classes.Items.Items;
import RPG.classes.abilities.Magic;

import java.util.ArrayList;

/**
 * Created by pikachu on 13.07.17.
 */
public interface Human {

    int getManaPoint();

    int getDefence();

    int getDamage();

    int applyDamage(int damage);

    int getHitPoint();

    void setHitPoint(int hitPoint);

    ArrayList<Items> getInventory();

    int getLevel();

    double getExperience();

    void setExperience(double experience);

    double expToNextLevel();

    void setDamage(int damage);

    int getAgility();

    void setAgility(int agility);

    int getIntelligence();

    void setIntelligence(int intelligence);

    boolean setMana(int mana);

    int getMagic(Magic magic);

    int getPower();

    void setPower(int power);
}
