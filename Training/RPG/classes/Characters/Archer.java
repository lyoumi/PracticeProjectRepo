package RPG.classes.Characters;

import RPG.classes.Items.Equipment;
import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.Items;
import RPG.classes.Items.UsingItems;
import RPG.classes.Items.items.Item;
import RPG.classes.Items.items.armors.Armor;
import RPG.classes.Items.items.weapons.Weapons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by pikachu on 13.07.17.
 */
public class Archer implements Human, UsingItems, Equipment{

    private int agility = 22;
    private int intelligence = 13;
    private int power = 11;
    private double experience = 0;
    private int level = 0;
    private int damage = getAgility()*2;
    private int hitPoint = getPower()*10;
    private ArrayList<Items> inventory = new ArrayList<>();
    private Map<EquipmentItems, Item> equipmentItems = new HashMap<>();
    private Weapons weapons;
    private Armor armor;
    private int defence;

    private boolean expToNextLevelReady(){
        return getExperience() >= ((level+1)*150);
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public double getExperience() {
        return experience;
    }

    @Override
    public void setExperience(double experience) {
        this.experience += experience;
    }

    @Override
    public double expToNextLevel() {
        return (((getLevel()+1)*150) - getExperience());
    }

    @Override
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

    @Override
    public int getAgility() {
        return agility;
    }

    @Override
    public void setAgility(int agility) {
        this.agility = agility;
    }

    @Override
    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int getDefence() {
        defence = 0;
        for (Map.Entry<EquipmentItems, Item> entry :
                equipmentItems.entrySet()) {
            if (!entry.getValue().EQUIPMENT_ITEMS().equals(EquipmentItems.HANDS)) defence += ((Armor)entry.getValue()).getDefence();
        }
        return defence;
    }

    @Override
    public int getDamage() {
        if (equipmentItems.containsKey(EquipmentItems.HANDS)) return damage + weapons.getDamage();
        else return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int applyDamage(int damage) {
        return damage - getDefence();
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
    public ArrayList<Items> getInventory() {
        return inventory;
    }

    @Override
    public boolean add(Items item) {
        return inventory.add(item);
    }

    @Override
    public void use(Items item) {
        if (item.equals(Items.SmallHPBottle)) {
            setHitPoint(getHitPoint()+30);
            getInventory().remove(item);
        }
        if (item.equals(Items.MiddleHPBottle)) {
            setHitPoint(getHitPoint()+50);
            getInventory().remove(item);
        }
        if (item.equals(Items.BigHPBottle)) {
            setHitPoint(getPower()*11);
            getInventory().remove(item);
        }
    }

    @Override
    public void equip(Item item) {
        if (item.EQUIPMENT_ITEMS().equals(EquipmentItems.HANDS)){
            weapons = (Weapons) item;
            System.err.println(weapons.getName() + " equipped");
            equipmentItems.put(weapons.EQUIPMENT_ITEMS(), weapons);
        } else {
            armor = (Armor) item;
            System.err.println(armor.getName() + " equipped");
            equipmentItems.put(armor.EQUIPMENT_ITEMS(), armor);
        }
    }

    @Override
    public void unEquip() {

    }

    public String toString(){
        return "Class: " + Archer.class.getSimpleName() +
                "; HP " + String.valueOf(getHitPoint()) +
                "; Lvl: " + String.valueOf(getLevel()) +
                "; Exp to next level: " + expToNextLevel() +
                "; DMG: " + getDamage() +
                "; DEF: " + getDefence();
    }
}
