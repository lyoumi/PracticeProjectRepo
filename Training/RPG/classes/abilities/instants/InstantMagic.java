package RPG.classes.abilities.instants;

import RPG.classes.abilities.Magic;
import RPG.classes.abilities.MagicClasses;

/**
 * Created by pikachu on 18.07.17.
 */
public interface InstantMagic extends Magic{

    int getDamage();

    @Override
    int getLevel();

    @Override
    int getManaCost();

    @Override
    MagicClasses getMagicClass();
}
