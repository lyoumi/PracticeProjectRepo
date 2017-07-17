package RPG.player;

import RPG.classes.Characters.*;
import RPG.classes.Items.Equipment;
import RPG.classes.Items.Items;
import RPG.classes.Items.UsingItems;
import RPG.classes.Items.items.Item;
import RPG.classes.Monsters.Demon;
import RPG.classes.Monsters.Monster;

import java.io.IOException;
import java.util.*;

/**
 * Created by pikachu on 13.07.17.
 */
public class Player {

    private static Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final List<Items> itemsList = new ArrayList<>(Arrays.asList(Items.values()));
    private static final int sizeOfItems = itemsList.size();

    /**
     * Основно метод контроля персонажа
     * В этом методе проверяется количество здоровья персонажа и, в случае его смерти, останавливает игру
     * В конце каждого хода пользователю предлагается на выбор использовать имеющиеся предметы,
     * отправить героя добывать ресурсы и опыт, продолжить приключение или же остановить игру.
     *
     * @param human
     * @throws IOException
     */
    private synchronized void beginGame(Human human) throws IOException {

        System.out.println(human);

        adventure:
        while (true) {

            Monster monster = spawn(human);
            System.out.println("Battle began with " + monster);

            String resultOfBattle = battle(human, monster);

            if (human.getHitPoint() < 0) {
                System.err.println("YOU ARE DEAD");
                break;
            } else if (monster.getHitPoint() <= 0) {
                drop(human, monster, false);
            }
            System.out.println(resultOfBattle);
            System.out.println("What's next? 1 - use items, 2 - walking, 3 - break adventures, 4 - go to auto-battle, default to continue....");
            switch (scanner.nextInt()) {
                case 1: {
                    useItem(human);
                    break;
                }
                case 2: {
                    String endOfWalk = walking(human);
                    System.out.println(endOfWalk);
                    break;
                }
                case 3: break adventure;
                case 4: {
                    autoBattle(human);
                    break;
                }
                default: break;
            }
        }
    }

    /**
     * Данный метод описывает автоматизированное поведение героя
     * В данном методе герой в цикле while() получает 0.0000001 опыта и случайно выпадающие предметы
     * Остановка цикла происходит при вводе с клавиатуры 0
     *
     * @param human
     * @return
     * @throws IOException
     */
    private String walking(Human human) throws IOException{
        while (System.in.available()==0) {
            human.setExperience(0.0000001);
            human.changeLevel();
            if (random.nextInt(10000000) == 999999) {
                Items item = itemsList.get(random.nextInt(sizeOfItems));
                System.out.println("I found " + item);
                human.getInventory().add(item);
            }
            if (human.getInventory().size() > ((human.getLevel()+1)*10)) break;
        }
        return "The walk is over. Your stats: " + human;
    }

    private void autoBattle(Human human) throws IOException {
        while (System.in.available()==0){
//            System.err.println(human.getInventory().size());
            if (human.getInventory().size() < 10) {
                System.out.println("I need go walk.... Pls, wait some time, I will be back\n" + human);
                String walkingResults = walking(human);
                System.out.println(walkingResults);
            }

            Monster monster = new Demon(human.getLevel());
            while ((human.getHitPoint() > 0) && (monster.getHitPoint() > 0)){
                if ((human.getHitPoint() > 50) && (human.getHitPoint() < 80) && (human.getInventory().contains(Items.SmallHPBottle))) {
                    ((UsingItems)human).use(Items.SmallHPBottle);
                    System.out.println(human);
                }
                if ((human.getHitPoint() > 30) && (human.getHitPoint() < 50) && (human.getInventory().contains(Items.MiddleHPBottle))) {
                    ((UsingItems)human).use(Items.MiddleHPBottle);
                    System.out.println(human);
                }
                if ((human.getHitPoint() > 0) && (human.getHitPoint() < 30) && (human.getInventory().contains(Items.BigHPBottle))) {
                    ((UsingItems)human).use(Items.BigHPBottle);
                    System.out.println(human);
                }
                monster.setHitPoint((monster.getHitPoint() - monster.applyDamage(human.getDamage())));
                human.setHitPoint((human.getHitPoint() - human.applyDamage(monster.damage())));
            }
            human.getInventory().trimToSize();

            if (human.getHitPoint() <= 0) {
                System.err.println("YOU ARE DEAD");
                System.exit(0);
            } else if (monster.getHitPoint() <= 0) {
                drop(human, monster, true);
            }

        }
    }

    /**
     * Метод симулирующий бой между героем и монстром
     * В ходе боя игрок может покинуть бой для дальнейшего приключения, или же использовать имеющиеся у него веши
     * @param human
     * @param monster
     */
    private synchronized String battle(Human human, Monster monster){
        battle:
        while ((human.getHitPoint() > 0) && (monster.getHitPoint() > 0)) {
            monster.setHitPoint((monster.getHitPoint() - monster.applyDamage(human.getDamage())));
            human.setHitPoint((human.getHitPoint() - human.applyDamage(monster.damage())));
            System.err.println(human);

            System.out.println("Choose next turn: 1 - useItem, 2 - leave battle, default to continue....");
            switch (scanner.nextInt()) {
                case 1:
                    useItem(human);
                    break;
                case 2:
                    break battle;
            }
        }
        return "The battle is over. Your stats: " + human;
    }

    /**
     * Пользователю предлагается использовать один из имеющихся у него предметов,
     * предвартельно ознакомив его с содержимым инвентаря. Доступ к предмету осществляется по списку.
     * <p>
     * После ввода индекса осуществляется проверка на наличие этого предмета в инвентаре, после чего вызывается
     * метод use() из класса персонажа.
     */
    private void useItem(Human human) {
        System.out.println("Use your items? " + human.getInventory() + "\nPls, select by index....");
        int position = scanner.nextInt();
        if (human.getInventory().contains(human.getInventory().get(position)))
            ((UsingItems) human).use(human.getInventory().get(position));
    }

    /**
     * После смерти монстра выпадает случайный премет из списка Item.
     * У игрока есть возможность поднять этот предмет и переместить в свой инвентарь
     * <p>
     * Входные параметры:
     *
     * @param human
     * @param monster
     */
    private void drop(Human human, Monster monster, boolean autoDrop) {
        if (autoDrop){

            human.setExperience(monster.getExperience());
            human.changeLevel();
            ((UsingItems) human).add(monster.getInventory().pollLast());
            ((Equipment)human).equip(monster.getDroppedItems());

        } else{

            human.setExperience(monster.getExperience());
            System.out.println("You can add to your getInventory " + monster.getInventory());
            if (scanner.nextInt() == 1) ((UsingItems) human).add(monster.getInventory().pollLast());

            Item droppedItems = monster.getDroppedItems();
            System.out.println("Weapons " + droppedItems);
            if (scanner.nextInt() == 1) ((Equipment)human).equip(droppedItems);
        }
    }

    /**
     * Метод, отвечающий за генерацию монстра
     * @param human
     * @return
     */
    private Monster spawn(Human human) {
        return new Demon(human.getLevel());
    }

    public static void main(String[] args) throws IOException {
        Player player = new Player();
        System.out.println("Hello in Middle-Earth....");
        System.out.println("Choose your class: archer, berserk, wizard....");
        switch (scanner.nextLine()) {
            case "archer": {
                player.beginGame(new Archer());
                break;
            }
            case "berserk": {
                player.beginGame(new Berserk());
                break;
            }
            case "wizard": {
                player.beginGame(new Wizard());
                break;
            }
        }
    }
}
