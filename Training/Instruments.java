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
                System.out.println("Pls, enter sing....");
                String song = scanner.nextLine();
                instrument = new Percussion(song);
                sing();
                break;
            }
            case "Wind": {
                System.out.println("Pls, enter song....");
                String song = scanner.nextLine();
                System.out.println("Pls, enter kind of wind instruments....");
                String kind = scanner.nextLine();
                switch (kind){
                    case "Brass":{
                        instrument = new Brass(song);
                        break;
                    }
                    case "WoodWind":{
                        instrument = new WoodWind(song);
                        break;
                    }
                    default: instrument = new Wind(song);
                }
                sing();
                break;
            }
            default: break;
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
        return "Playing song: " + song + "....";
    }
}

class Wind extends Instrument {

    private String song;

    Wind(String song){
        this.song = song;
    }

    @Override
    void play() {
        System.out.println("Play the unknown wind instrument....!");
    }

    @Override
    void adjust() {
        System.out.println("Adjust unknown wind instrument....");
    }

    @Override
    String song() {
        return "Playing song: " + song + "....";
    }
}

class WoodWind extends Wind{

    WoodWind(String song) {
        super(song);
    }

    @Override
    void play() {
        System.out.println("You play WoodWind!");
    }

    @Override
    String song() {
        return super.song();
    }

    @Override
    void adjust() {
        System.out.println("Adjust WoodWind!");
    }
}

class Brass extends Wind{

    Brass(String song) {
        super(song);
    }

    @Override
    void play() {
        System.out.println("You play BrassWind!");
    }

    @Override
    String song() {
        return super.song();
    }

    @Override
    void adjust() {
        System.out.println("Adjust BrassWind!");
    }
}
