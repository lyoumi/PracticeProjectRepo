package RPG.classes.abilities;

import RPG.classes.Characters.Human;

/**
 * Created by pikachu on 18.07.17.
 */
public interface Magic {

    int getLevel();

    int getManaCost();

    MagicClasses getMagicClass();

}
