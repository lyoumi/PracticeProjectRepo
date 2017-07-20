package RPG.classes.abilities.buffs;

import RPG.classes.Characters.Human;
import RPG.classes.abilities.Magic;
import RPG.classes.abilities.MagicClasses;

/**
 * Created by pikachu on 18.07.17.
 */
public interface BuffMagic extends Magic{

    int getAgility();

    int getPower();

    int getIntelligence();

    @Override
    int getLevel();

    @Override
    int getManaCost();

    @Override
    MagicClasses getMagicClass();
}
