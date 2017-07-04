package Training;

import java.util.Scanner;

/**
 * Created by pikachu on 04.07.17.
 */
public class Instruments {

    private static Instrument instrument;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Pls, choose your instrument....");
        String s = scanner.nextLine();
        switch (s){
            case "Percussion": {
                System.out.println("Pls, enter song....");
                String song = scanner.nextLine();
                instrument = new Percussion(song);
                sing();
                break;
            }
            case "Wind": {
                System.out.println("Pls, enter sing....");
                String song = scanner.nextLine();
                instrument = new Wind(song);
                sing();
                break;
            }
        }
    }

    private static void sing(){
        instrument.play();
        instrument.adjust();
        System.out.println(instrument.song());
    }
}

abstract class Instrument {

    abstract void play();

    abstract void adjust();

    abstract String song();
}

class Percussion extends Instrument {

    private String song;

    Percussion(String song){
        this.song = song;
    }

    @Override
    void play() {
        System.out.println("Drum bass!");
    }

    @Override
    void adjust() {
        System.out.println("Drum thunder!");
    }

    @Override
    String song() {
        return song;
    }
}

class Wind extends Instrument {

    private String song;

    Wind(String song){
        this.song = song;
    }

    @Override
    void play() {
        System.out.println("Play the trumpet!");
    }

    @Override
    void adjust() {
        System.out.println("Trumpet breeze!");
    }

    @Override
    String song() {
        return song;
    }
}


