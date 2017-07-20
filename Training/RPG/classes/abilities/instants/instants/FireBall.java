package RPG.classes.abilities.instants.instants;

import RPG.classes.abilities.Magic;
import RPG.classes.abilities.MagicClasses;
import RPG.classes.abilities.debuffs.DebuffMagic;
import RPG.classes.abilities.debuffs.debuffs.BurningJoe;
import RPG.classes.abilities.instants.InstantMagic;

public class FireBall implements InstantMagic {
    private int level;
    private int damage;
    private int manaCost;
    private final int costMultiplier = 3;

    private FireBall(int level){
        this.level = level + 1;
        this.damage = getLevel() * 5;
        this.manaCost = getLevel() * getCostMultiplier();
    }

    private int getCostMultiplier() {
        return costMultiplier;
    }

    @Override
    public int getDamage() {
        return damage + (BurningJoe.getBurningJoe(getLevel()).getDamage() * BurningJoe.getBurningJoe(getLevel()).getTimeOfAction());
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public MagicClasses getMagicClass() {
        return MagicClasses.INSTANT;
    }

    public static Magic getMagic(int level) {
        return new FireBall(level);
    }
}
