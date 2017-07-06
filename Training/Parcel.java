package Training;

import java.util.Scanner;

/**
 * Created by pikachu on 06.07.17.
 */
public class Parcel {

    private static Scanner scanner = new Scanner(System.in);

    class Contents implements Training.Contents{
        private int range;
        Contents(int range){
            this.range = range;
        }
        public int getRange(){return range;}
    }

    class Destination implements Training.Destination{
        private String label;
        Destination(String s){
            label = s;
        }
        public String readLabel(){return label;}
    }

    private Destination to(String s){return new Destination(s);}

    private Contents contents(int range){return new Contents(range);}

    private void ship(String dest, int range){
        Contents contents = contents(range);
        Destination destination = to(dest);
        System.out.println("Destination: " + destination.readLabel() + "\nRange: " + contents.getRange());
    }

    public static void main(String[] args) {
        Parcel parcel = new Parcel();
        System.out.println("Enter destination....");
        String dest = scanner.nextLine();
        System.out.println("Pls, enter range....");
        int range = scanner.nextInt();
        parcel.ship(dest, range);
    }

}

interface Contents{
    int getRange();
}

interface Destination{
    String readLabel();
}