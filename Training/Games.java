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

    static GameFactory gameFactory = DiceRoll::new;
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

    static GameFactory gameFactory = CoinToss::new;
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
        label:
        do {
            i = scanner.nextInt();
            switch (i) {
                case 1:
                    Games.doIt(DiceRoll.gameFactory);
                    break label;
                case 2:
                    Games.doIt(CoinToss.gameFactory);
                    break label;
                default:
                    System.out.println("Pls, enter another getRange....");
                    break;
            }
        } while (true);
    }
}
