package RPG.classes.abilities.debuffs.debuffs;

import RPG.classes.abilities.MagicClasses;
import RPG.classes.abilities.debuffs.DebuffMagic;

public class BurningJoe implements DebuffMagic {

    private int level;
    private int damage;
    private int timeOfAction = 5;

    private BurningJoe(int level){
        this.level = level;
        this.damage = getLevel()*3;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getManaCost() {
        return 30;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getTimeOfAction() {
        return --timeOfAction;
    }

    @Override
    public MagicClasses getMagicClass() {
        return MagicClasses.DEBUFF;
    }

    public static BurningJoe getBurningJoe(int level){
        return new BurningJoe(level);
    }
}
