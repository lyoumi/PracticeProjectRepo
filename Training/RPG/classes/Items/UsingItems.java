package RPG.classes.Items;

import RPG.classes.Characters.Human;
import RPG.classes.Monsters.Monster;

/**
 * Created by pikachu on 14.07.17.
 */
public interface UsingItems {
    boolean add(Items item);
    void use(Items item);
}
