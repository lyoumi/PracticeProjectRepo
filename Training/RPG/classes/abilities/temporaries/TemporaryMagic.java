package RPG.classes.abilities.temporaries;

import RPG.classes.Characters.Human;
import RPG.classes.abilities.Magic;
import RPG.classes.abilities.MagicClasses;

/**
 * Created by pikachu on 18.07.17.
 */
public interface TemporaryMagic extends Magic {

    int getDamage();

    @Override
    int getLevel();

    @Override
    int getManaCost();

    @Override
    MagicClasses getMagicClass();
}
