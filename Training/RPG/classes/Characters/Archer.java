package Training.RPG.classes.Characters;

import Training.RPG.classes.Items.Items;
import Training.RPG.classes.Items.UsingItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pikachu on 13.07.17.
 */
public class Archer implements Human, UsingItems{

    private int agility = 22;
    private int intelligence = 13;
    private int power = 11;
    private double experience = 0;
    private int level = 0;
    private int damage = getAgility()*2;
    private int hitPoint = getPower()*10;
    private List<Items> inventory = new ArrayList<>();

    public int getLevel() {
        return level;
    }

    private boolean expToNextLevelReady(){
        return getExperience() >= ((level+1)*150);
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience += experience;
    }

    @Override
    public double expToNextLevel() {
        return (((getLevel()+1)*150) - getExperience());
    }

    public void changeLevel(){
        if (expToNextLevelReady()) {
            level++;
            System.out.println("Congratulation with level: " + level);
            setAgility(getAgility()+3);
            setIntelligence(getIntelligence()+2);
            setPower(getPower()+2);
            setHitPoint(getPower()*10);
        }
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int applyDamage(int damage) {
        return damage;
    }

    @Override
    public int getHitPoint() {
        return hitPoint;
    }

    @Override
    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    @Override
    public List<Items> getInventory() {
        return inventory;
    }

    @Override
    public boolean add(Items item) {
        return inventory.add(item);
    }

    @Override
    public void use(Items item) {
        if (item.equals(Items.SmallHPBottle)) {
            setHitPoint(getHitPoint()+10);
            getInventory().remove(item);
        }
        if (item.equals(Items.MiddleHPBottle)) {
            setHitPoint(getHitPoint()+20);
            getInventory().remove(item);
        }
        if (item.equals(Items.BigHPBottle)) {
            setHitPoint(getPower()*11);
            getInventory().remove(item);
        }
    }

    public String toString(){
        return "Class: " + Archer.class.getSimpleName() +
                "; HP " + String.valueOf(getHitPoint()) +
                "; Lvl: " + String.valueOf(getLevel()) +
                "; Exp to next level: " + expToNextLevel();
    }

}
