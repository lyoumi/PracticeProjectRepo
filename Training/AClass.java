package Training;

import java.util.Random;

/**
 * Created by pikachu on 06.07.17.
 */
public class AClass {
    public static void main(String[] args) {
        System.out.println("Random inner int: " + TopInterface.InnerInterface.i);
        System.out.println("Random inner float: " + TopInterface.InnerInterface.f);
        System.out.println("Random inner double: " + TopInterface.InnerInterface.d);

        System.out.println("Random top-level int: " + TopInterface.i);
        System.out.println("Random top-level float: " + TopInterface.f);
        System.out.println("Random top-level double: " + TopInterface.d);
    }
}

interface TopInterface {
    interface InnerInterface{
        int i = random.nextInt(200)-100;
        float f = random.nextFloat()*100;
        double d = random.nextDouble()*100;
    }
    Random random = new Random();
    int i = random.nextInt(200)-100;
    float f = random.nextFloat();
    double d = random.nextDouble();
}
