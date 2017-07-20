package RPG.classes.Characters;

import RPG.classes.Items.Equipment;
import RPG.classes.Items.EquipmentItems;
import RPG.classes.Items.Items;
import RPG.classes.Items.UsingItems;
import RPG.classes.Items.items.Item;
import RPG.classes.Items.items.armors.Armor;
import RPG.classes.Items.items.weapons.Weapons;
import RPG.classes.abilities.Magic;
import RPG.classes.abilities.MagicClasses;
import RPG.classes.abilities.buffs.BuffMagic;
import RPG.classes.abilities.instants.instants.FireBall;
import RPG.classes.abilities.instants.instants.InstantMagic;

import java.util.*;

/**
 * Created by pikachu on 13.07.17.
 */
public class Archer implements Human, UsingItems, Equipment{

    private int agility = 22;
    private int intelligence = 13;
    private int power = 11;
    private double experience = 0;
    private int level = 0;
    private int damage = getAgility()*getMultiplierAgility();
    private int hitPoint = getPower()*getMultiplierPower();
    private int mana = getIntelligence()*getMultiplierIntelligence();
    private ArrayList<Items> inventory = new ArrayList<>();
    private Map<EquipmentItems, Item> equipmentItems = new HashMap<>();
    private Weapons weapons;
    private Armor armor;
    private int defence;
    private Magic magic;

    private int getMultiplierAgility() {
        return 2;
    }

    private int getMultiplierPower() {
        return 11;
    }

    private int getMultiplierIntelligence() {
        return 6;
    }

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
        changeLevel();
    }

    @Override
    public double expToNextLevel() {
        return (((getLevel()+1)*1500) - getExperience());
    }

    private boolean changeLevel(){
        if (expToNextLevelReady()) {
            level++;
            System.out.println("Congratulation with level: " + level);
            System.out.println();
            setAgility(getAgility()+3);
            setIntelligence(getIntelligence()+2);
            setPower(getPower()+2);
            updateStats();
            System.out.println(this);
            return true;
        } else return false;
    }

    @Override
    public int getAgility() {
        return agility + getSummaryAdditionAgility();
    }

    private int getSummaryAdditionAgility(){
        int additionAgility = 0;
        if(!Objects.equals(equipmentItems, null)){
            for (Map.Entry<EquipmentItems, Item> entry :
                    equipmentItems.entrySet()) {
                if (!Objects.equals(entry.getValue().getMagic(), null)){
                    if (entry.getValue().getMagic().getMagicClass().equals(MagicClasses.BUFF)){
                        magic = entry.getValue().getMagic();
                        BuffMagic magic = (BuffMagic) this.magic;
                        additionAgility += magic.getAgility();
                    }
                }
            }
        }
        return additionAgility;
    }

    @Override
    public void setAgility(int agility) {
        this.agility = agility;
    }

    @Override
    public int getIntelligence() {
        return intelligence + getSummaryAdditionIntelligence();
    }

    private int getSummaryAdditionIntelligence(){
        int additionIntelligence = 0;
        if (!Objects.equals(equipmentItems, null)){
            for (Map.Entry<EquipmentItems, Item> entry :
                    equipmentItems.entrySet()) {
                if (!Objects.equals(entry.getValue().getMagic(), null)){
                    if (entry.getValue().getMagic().getMagicClass().equals(MagicClasses.BUFF)){
                        magic = entry.getValue().getMagic();
                        BuffMagic magic = (BuffMagic) this.magic;
                        additionIntelligence += magic.getIntelligence();
                    }
                }
            }
        }
        return additionIntelligence;
    }

    @Override
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public int getManaPoint() {
        return mana;
    }

    @Override
    public boolean setMana(int mana) {
        this.mana = mana;
        return true;
    }

    @Override
    public int getMagic(Magic magic) {
        if (Objects.equals(magic.getClass().getSimpleName(), InstantMagic.FireBall.toString())) {
            FireBall fireBall = (FireBall) magic;
            if(getManaPoint() >= fireBall.getManaCost()){
                setMana(getManaPoint()-fireBall.getManaCost());
                return fireBall.getDamage();
            } else {
                System.out.println("Not enough mana!");
                return 0;
            }
        }else return 0;
    }

    @Override
    public int getPower() {
        return power + getSummaryAdditionPower();
    }

    private int getSummaryAdditionPower(){
        int additionPower = 0;
        if(!Objects.equals(equipmentItems, null)){
            for (Map.Entry<EquipmentItems, Item> entry : equipmentItems.entrySet()) {
                if (!Objects.equals(entry.getValue().getMagic(), null)){
                    if (entry.getValue().getMagic().getMagicClass().equals(MagicClasses.BUFF)){
                        magic = entry.getValue().getMagic();
                        BuffMagic magic = (BuffMagic) this.magic;
                        additionPower += magic.getPower();
                    }
                }
            }
        }
        return additionPower;
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
            if (!entry.getValue().EQUIPMENT_ITEMS().equals(EquipmentItems.HANDS)) {
                defence += ((Armor) entry.getValue()).getDefence();
            }
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
    public int applyDamage(int damage)  {
        int applyingDamage = damage - getDefence();
        if (applyingDamage < 0) return 0;
        else return applyingDamage;
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
            setHitPoint(getHitPoint()+((getPower()*getPower())/2));
            System.out.println(item + " used");
            getInventory().remove(item);
        }
        if (item.equals(Items.MiddleHPBottle)) {
            setHitPoint(getHitPoint() + ((getPower()*getPower())*3/4));
            System.out.println(item + " used");
            getInventory().remove(item);
        }
        if (item.equals(Items.BigHPBottle)) {
            setHitPoint(getPower()*getMultiplierPower());
            System.out.println(item + " used");
            getInventory().remove(item);
        }
        if (item.equals(Items.SmallFlower)){
            setMana(getManaPoint() + (getIntelligence()*getMultiplierIntelligence()));
            System.out.println(item + " used");
            getInventory().remove(item);
        }
        if (item.equals(Items.MiddleFlower)){
            setMana(getManaPoint() + (getIntelligence()*getMultiplierIntelligence()*3/4));
            System.out.println(item + " used");
            getInventory().remove(item);
        }
        if (item.equals(Items.BigFlower)){
            setMana(getIntelligence()*getMultiplierIntelligence());
            System.out.println(item + " used");
            getInventory().remove(item);
        }
    }

    @Override
    public void equip(Item item) {
        if (item.EQUIPMENT_ITEMS().equals(EquipmentItems.HANDS)){
            weapons = (Weapons) item;
            System.err.println(weapons.getName() + " equipped");
            equipmentItems.put(weapons.EQUIPMENT_ITEMS(), weapons);
            updateStats();
        } else {
            armor = (Armor) item;
            System.err.println(armor.getName() + " equipped");
            equipmentItems.put(armor.EQUIPMENT_ITEMS(), armor);
            updateStats();
        }
    }

    private void updateStats(){
        setHitPoint(getPower()*10);
        setDamage(getAgility()*getMultiplierAgility());
        setMana(getAgility()*getMultiplierIntelligence());
    }

    @Override
    public void unEquip() {

    }

    public String toString(){
        return "Class: " + Archer.class.getSimpleName() +
                "; HP " + String.valueOf(getHitPoint()) +
                "; MP " + getManaPoint() +
                "; Lvl: " + String.valueOf(getLevel()) +
                "; Exp to next level: " + expToNextLevel() +
                "; DMG: " + getDamage() +
                "; DEF: " + getDefence();
    }
}
