package RPG.classes.abilities.debuffs;

import RPG.classes.abilities.Magic;

/**
 * Created by pikachu on 18.07.17.
 */
public interface DebuffMagic extends Magic{
    int getDamage();
    int getTimeOfAction();
}
