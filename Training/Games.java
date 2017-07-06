package Training;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by pikachu on 06.07.17.
 */


interface Game{
    Object go();
}

interface GameFactory{
    Game getGame();
}

class DiceRoll implements Game{

    private static Random random = new Random();

    @Override
    public Integer go() {
        return random.nextInt(6);
    }
}

class DiceRollFactory implements GameFactory{

    @Override
    public Game getGame() {
        return new DiceRoll();
    }
}

class CoinToss implements Game{

    private Random random = new Random();

    @Override
    public String go() {
        String s = "";
        switch (random.nextInt(2) ){
            case 0: {
                s = "heads";
                break;
            }
            case 1: {
                s = "tails";
                break;
            }
        }
        return s;
    }
}

class CoinTossFactory implements GameFactory{
    @Override
    public Game getGame() {
        return new CoinToss();
    }
}

public class Games {

    private static Scanner scanner = new Scanner(System.in);

    private static void doIt(GameFactory gameFactory){
        Game game = gameFactory.getGame();
        System.out.println( gameFactory.getGame().getClass().getName() + ": " + game.go());
    }

    public static void main(String[] args) {
        System.out.println("Choose game: 1 - dice, 2 - coin....");
        int i;
        do{
            i = scanner.nextInt();
            if (i == 1) {
                Games.doIt(new DiceRollFactory());
                break;
            } else if (i == 2) {
                Games.doIt(new CoinTossFactory());
                break;
            } else {
                System.out.println("Pls, enter another value....");
            }
        }while (true);
    }
}