package RPG.classes.Monsters;

import RPG.classes.Items.Items;
import RPG.classes.Items.items.Item;
import RPG.classes.Items.items.armors.armors.IronChest;
import RPG.classes.Items.items.armors.boots.IronBoots;
import RPG.classes.Items.items.armors.helmets.IronHelmet;
import RPG.classes.Items.items.weapons.weapons.Sword;

import java.util.*;

/**
 * Created by pikachu on 13.07.17.
 */
public class Demon implements Monster {

    private static final List<Items> itemsList = Arrays.asList(Items.values());
    private static final int sizeOfItems = itemsList.size();
    private static final Random random = new Random();

    private int HERO_LEVEL;

    private int damage;
    private int hitPoint;
    private int experience = 100;
    private LinkedList<Items> inventory = new LinkedList<>();

    public Demon(int HERO_LEVEL){
        hitPoint = (HERO_LEVEL+1)*50;
        damage = (HERO_LEVEL+1)*6;
        this.HERO_LEVEL = HERO_LEVEL;
    }

    public int getExperience(){
        return  experience;
    }

    @Override
    public int damage() {
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

    public Item getDroppedItems(){
        switch (random.nextInt(4)){
            case 0:{
                return new Sword(HERO_LEVEL);
            }
            case 1: {
                return new IronChest(HERO_LEVEL);
            }
            case 2: {
                return new IronHelmet(HERO_LEVEL);
            }
            case 3: {
                return new IronBoots(HERO_LEVEL);
            }
            default: {
                return null;
            }
        }
    }

    public String toString(){
        return "Name: " + Demon.class.getSimpleName() + "; Damage: " + damage() + "; HitPoint: " + getHitPoint();
    }
}
