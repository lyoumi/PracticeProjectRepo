package Training.RPG.player;

import Training.RPG.classes.Characters.*;
import Training.RPG.classes.Monsters.Demon;
import Training.RPG.classes.Monsters.Monster;

import java.util.Scanner;

/**
 * Created by pikachu on 13.07.17.
 */
public class Player {

    private static Scanner scanner = new Scanner(System.in);

    private Human human;

    private void beginGame(Human human){
        System.out.println(human);
        setHuman(human);
        while (true){
            Monster monster = spawn();
            System.out.println("Battle begin with " + monster);
            while (true){
                monster.setHitPoint((monster.getHitPoint()-monster.applyDamage(human.damage())));
                human.setHitPoint((human.getHitPoint()-human.applyDamage(monster.damage())));
                System.out.println("You have " + human.getHitPoint() + "HP last, would you like to run away? Press 1....");
                if (scanner.nextInt()==1)break;
                if ((human.getHitPoint()<0)||(monster.getHitPoint()<0)) break;
            }

            if (human.getHitPoint()<0){
                System.err.println("YOU ARE DEAD");
                break;
            } else if (monster.getHitPoint() <= 0){
                ((Archer)human).drop(human, monster);
            }

            System.out.println("Use your items? Press 1 to use...." + human.inventory());
            int choiceInt = scanner.nextInt();
            if (choiceInt==1){
                System.out.println("Pls, select index....");
                int position = scanner.nextInt();
                if (human.inventory().contains(human.inventory().get(position))) ((Archer) human).use(human.inventory().get(position));
            }

            System.out.println("Next turn?... Press 0 to exit....");
            int i = scanner.nextInt();
            if (i == 0) break;

        }
    }


    public void setHuman(Human human) {
        this.human = human;
    }

    private Monster spawn(){
        return new Demon(human.getLevel());
    }

    public static void main(String[] args) {
        Player player = new Player();
        System.out.println("Hello in Middle-Earth....");
        System.out.println("Choose your class: archer, berserk, wizard....");
        switch (scanner.nextLine()){
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
