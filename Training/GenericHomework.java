package Training.Generics;

import java.util.*;

/**
 * Created by pikachu on 09.07.17.
 */

interface Generic {
    Collection addNewObject(Collection<String> collection);
}

interface GenericFactory{
    Generic getGeneric();
}

class GenericImplementation1 implements GenericFactory{

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    @Override
    public  Generic getGeneric() {
        return collection -> {
            System.out.println("Enter count....");
            int count = scanner.nextInt();
            for (int i = 0; i < count; i++) {
                System.out.println("Generating new value....");
                collection.add(generateNewValue());
            }
            return collection;
        };
    }
    private String generateNewValue(){
        return String.valueOf(random.nextInt(6));
    }
}

class GenericImplementation2 implements Generic{

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    @Override
    public Collection addNewObject(Collection<String> collection) {
        System.out.println("Enter count....");
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.println("Generating new value....");
            collection.add(generateNewValue());
        }
        return collection;
    }

    private String generateNewValue(){
        return String.valueOf(random.nextInt(7));
    }

    static GenericFactory factory = GenericImplementation2::new;
}

public class GenericHomework {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Choose your collection....");
        Collection<String> collection;
        switch (scanner.nextLine()){
            case "ArrayList":{
                collection = new ArrayList<>();
                break;
            }
            case "LinkedList":{
                collection = new LinkedList<>();
                break;
            }
            case "TreeSet":{
                collection = new TreeSet<>();
                break;
            }
            case "LinkedHashSet":{
                collection = new LinkedHashSet<>();
                break;
            }
            case "HashSet":{
                collection = new HashSet<>();
                break;
            }
            default:{
                collection = new ArrayList<>();
                break;
            }
        }
        collection = GenericImplementation2.factory.getGeneric().addNewObject(collection);
        System.out.println("Your collection is: " + collection + "....");
        collection.clear();
        collection = new GenericImplementation1().getGeneric().addNewObject(collection);
        System.out.println("Your collection is: " + collection + "....");
    }
}
