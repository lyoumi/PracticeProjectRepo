package RPG.classes.Characters;

import RPG.classes.Items.Items;
import RPG.classes.abilities.Magic;

import java.util.ArrayList;

/**
 * Created by pikachu on 13.07.17.
 */
public class Wizard implements Human {

    private int damage = getIntelligence()*2;
    private int agility = 12;
    private int intelligence = 20;
    private int power = 10;
    private int hitPoint = getPower()*10;
    private int experience = 0;
    private int level = 0;

    public boolean changeLevel(){
        if (experience == ((level+1)*100)) level++;
        return true;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getPower() {
        return power;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public boolean setMana(int mana) {
        return true;
    }

    @Override
    public int getMagic(Magic magic) {
        return 0;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMultiplierAgility() {
        return 0;
    }

    public int getMultiplierPower() {
        return 0;
    }

    public int getMultiplierIntelligence() {
        return 0;
    }

    @Override
    public int getManaPoint() {
        return 0;
    }

    @Override
    public int getDefence() {
        return 0;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int applyDamage(int damage) {
        return 0;
    }

    @Override
    public int getHitPoint() {
        return 0;
    }

    @Override
    public void setHitPoint(int hitPoint) {

    }

    @Override
    public ArrayList<Items> getInventory() {
        return null;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public double getExperience() {
        return 0;
    }

    @Override
    public void setExperience(double experience) {

    }

    @Override
    public double expToNextLevel() {
        return 0;
    }
}
