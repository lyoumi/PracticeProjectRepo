package Training.RPG.classes.Items;

import Training.RPG.classes.Characters.Human;
import Training.RPG.classes.Monsters.Monster;

/**
 * Created by pikachu on 14.07.17.
 */
public interface UsingItems {
    void add(Items item);
    void use(Items item);
    void drop(Human human, Monster monster);
}
