package RPG.classes.Monsters;

import RPG.classes.Characters.Human;
import RPG.classes.Items.Items;
import RPG.classes.Items.items.Item;
import RPG.classes.Items.items.weapons.weapons.Bow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Devil implements Monster{

    private static final List<Items> itemsList = Arrays.asList(Items.values());
    private static final int sizeOfItems = itemsList.size();
    private static final Random random = new Random();

    private int HERO_LEVEL;
    private Human human;

    private int damage;
    private int hitPoint;
    private int experience = 100;
    private LinkedList<Items> inventory = new LinkedList<>();

    public Devil(Human human){
        this.human = human;
        HERO_LEVEL = human.getLevel();
        hitPoint = (HERO_LEVEL+1)*500;
        damage = (HERO_LEVEL+1)*100;
    }

    @Override
    public int getExperience() {
        return 100000;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int applyDamage(int applyDamage) {
        return applyDamage;
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
    public LinkedList<Items> getInventory() {
        inventory.add(itemsList.get(random.nextInt(sizeOfItems)));
        return inventory;
    }

    @Override
    public Item getDroppedItems() {
        return new Bow(human);
    }

    public String toString(){
        return Devil.class.getSimpleName() +": HP " + getHitPoint() + "; ATK +" + getDamage();
    }
}
