package Training;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by pikachu on 07.07.17.
 */
public class A {
    private static Random random = new Random();
    static U getInterface(){
        return new U() {
            @Override
            public int first() {
                return random.nextInt();
            }

            @Override
            public double second() {
                return random.nextDouble()*100;
            }

            @Override
            public float third() {
                return random.nextFloat()*100;
            }
        };
    }
}

class B{
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<U> arrayOfInterfaces;

    private static void addReference(U u){
        arrayOfInterfaces.add(u);
    }

    private static void removeReference(int position){
        arrayOfInterfaces.set(position, null);
    }

    private static void iterateReferences(){
        for (U arrayOfInterface : arrayOfInterfaces) {
            if (!(arrayOfInterface == null)) {
                System.out.println(arrayOfInterface.first());
                System.out.println(arrayOfInterface.second());
                System.out.println(arrayOfInterface.third());
            }
        }
    }

    public static void main(String[] args) {
        arrayOfInterfaces = new ArrayList<>();
        int k;
        do{
            System.out.println("Pls, choose option: 1 - add, 2 - remove, 3 - show, 0 - exit....");
            k = scanner.nextInt();
            switch (k){
                case 1: {
                    B.addReference(A.getInterface());
                    break;
                }
                case 2: {
                    System.out.println("Choose position....");
                    B.removeReference(scanner.nextInt());
                    break;
                }
                case 3: {
                    B.iterateReferences();
                    break;
                }
                default: break;
            }
        }while (k!=0);
    }

}

interface U{
    int first();
    double second();
    float third();
}
