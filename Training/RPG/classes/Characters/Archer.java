package Training.RPG.classes.Characters;

import Training.RPG.classes.Items.Items;
import Training.RPG.classes.Items.UsingItems;
import Training.RPG.classes.Monsters.Demon;
import Training.RPG.classes.Monsters.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by pikachu on 13.07.17.
 */
public class Archer implements Human, UsingItems{

    private Scanner scanner = new Scanner(System.in);

    private int agility = 22;
    private int intelligence = 13;
    private int power = 11;
    private int experience = 0;
    private int level = 0;
    private int damage = getAgility()*2;
    private int hitPoint = getPower()*10;
    private List<Items> inventory = new ArrayList<>();

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience += experience;
    }

    public int getLevel() {
        return level;
    }

    private boolean expToNextLevel(){
        return getExperience() >= ((level+1)*150);
    }

    public void changeLevel(){
        if (expToNextLevel()) {
            level++;
            System.out.println("Congratulation with level: " + level);
            setAgility(getAgility()+3);
            setIntelligence(getIntelligence()+2);
            setPower(getPower()+2);
            setHitPoint(getPower()*10);
        }
    }

    public int getDamage() {
        return damage;
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
    public int damage() {
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
    public List<Items> inventory() {
        return inventory;
    }

    public String toString(){
        return "HP " + String.valueOf(getHitPoint()) + " Lvl " + String.valueOf(getLevel());
    }

    @Override
    public void add(Items item) {
        inventory.add(item);
    }

    @Override
    public void use(Items item) {
        if (item.equals(Items.SmallHPBottle)) setHitPoint(getHitPoint()+10);
        if (item.equals(Items.MiddleHPBottle)) setHitPoint(getHitPoint()+20);
        if (item.equals(Items.BigHPBottle)) setHitPoint(getPower()*11);
    }

    @Override
    public void drop(Human human, Monster monster) {
        ((Archer) human).setExperience(((Demon)monster).getExperience());
        ((Archer)human).changeLevel();
        System.out.println("You can add to your inventory " + monster.getInventory());
        if (scanner.nextInt()==1) ((Archer)human).add(monster.getInventory().pollLast());
        System.out.println(human + " EXP " + ((Archer)human).getExperience());
    }
}
