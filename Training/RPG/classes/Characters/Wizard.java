package Training.RPG.classes.Characters;

import Training.RPG.classes.Characters.Human;
import Training.RPG.classes.Items.Items;

import java.util.List;

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

    public void changeLevel(){
        if (experience == ((level+1)*100)) level++;
    }

    public int getDamage() {
        return damage;
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

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int damage() {
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
    public List<Items> inventory() {
        return null;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
