package RPG.classes.abilities.buffs.buffs;

import RPG.classes.Characters.Human;
import RPG.classes.abilities.Magic;
import RPG.classes.abilities.MagicClasses;
import RPG.classes.abilities.buffs.BuffMagic;

/**
 * Created by pikachu on 18.07.17.
 */
public class ArchersBuff implements BuffMagic {

    private Human human;
    private int agility = 20;
    private int power = 8;
    private int intelligence = 4;

    private ArchersBuff(Human human){
        this.human = human;
    }

    @Override
    public int getAgility() {
        return agility;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public int getLevel() {
        return human.getLevel();
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public MagicClasses getMagicClass() {
        return MagicClasses.BUFF;
    }

    public static Magic getMagic(Human human) {
        return new ArchersBuff(human);
    }
}
