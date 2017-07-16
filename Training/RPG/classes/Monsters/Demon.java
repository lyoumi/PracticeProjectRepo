package Training.RPG.classes.Monsters;

import Training.RPG.classes.Items.Items;

import java.util.*;

/**
 * Created by pikachu on 13.07.17.
 */
public class Demon implements Monster {

    private static final List<Items> itemsList = Arrays.asList(Items.values());
    private static final int sizeOfItems = itemsList.size();
    private static final Random random = new Random();

    private int damage;
    private int hitPoint;
    private int experience = 100;
    private LinkedList<Items> inventory = new LinkedList<>();

    public Demon(int level){
        hitPoint = (level+1)*50;
        damage = (level+1)*6;
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

    public String toString(){
        return "Name: " + Demon.class.getSimpleName() + "; Damage: " + damage() + "; HitPoint: " + getHitPoint();
    }
}
