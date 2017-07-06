package Training;

import java.util.Scanner;

/**
 * Created by pikachu on 06.07.17.
 */
public class AnonymousInnerClass {

    private static Scanner scanner = new Scanner(System.in);

    private Destination to(String s){
        return new Destination(){
            private String label;
            {
                label = s;
            }
            public String readLabel(){return label;}
        };
    }

    private Contents contents(int r){
        return new Contents(){
            private int range;
            {
                range = r;
            }
            public int getRange(){return range;}
        };
    }

    private void ship(String dest, int range){
        Contents contents = contents(range);
        Destination destination = to(dest);
        System.out.println("Destination: " + destination.readLabel() + "\nRange: " + contents.getRange());
    }

    public static void main(String[] args) {
        AnonymousInnerClass anonymousInerClass = new AnonymousInnerClass();
        System.out.println("Enter destination....");
        String dest = scanner.nextLine();
        System.out.println("Pls, enter range....");
        int range = scanner.nextInt();
        anonymousInnerClass.ship(dest, range);
    }

}

interface Contents{
    int getRange();
}

interface Destination{
    String readLabel();
}
