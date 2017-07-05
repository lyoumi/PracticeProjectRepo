package Training;

/**
 * Created by pikachu on 05.07.17.
 */



class Characteristic{

    private String s;
    
    Characteristic(String s){
        this.s = s;
        System.out.println("Creating of characteristic " + s + "....");
    }

    protected void dispose(){
        System.out.println("Canceling characteristics " + s + "....");
    }
}

class Description{

    private String s;

    Description(String s){
        this.s = s;
        System.out.println("Creation descriptions of " + s + ".....");
    }

    protected void dispose(){
        System.out.println("Canceling descriptions of " + s + "....");
    }
}

class LivingCreature{

    private Characteristic characteristic = new Characteristic("Living creature");
    private Description description = new Description("Common living creature");

    LivingCreature(){
        System.out.println("Living creature");
    }

    protected void dispose(){
        System.out.println("Dispose in LivingCreature");
        characteristic.dispose();
        description.dispose();
    }
}

class Animal extends LivingCreature{

    private Characteristic characteristic = new Characteristic("Has heart");
    private Description description = new Description("Not plant");

    Animal(){
        System.out.println("Animal");
    }

    protected void dispose(){
        System.out.println("Dispose in Animal");
        characteristic.dispose();
        description.dispose();
        super.dispose();
    }
}


class Amphibian extends Animal{

    private Characteristic characteristic = new Characteristic("Can live in water");
    private Description description = new Description("Lingering, land");

    Amphibian(){
        System.out.println("Amphibian");
    }

    protected void dispose() {
        System.out.println("Dispose in amphibian");
        characteristic.dispose();
        description.dispose();
        super.dispose();
    }
}

public class Frog extends Amphibian{

    private Characteristic characteristic = new Characteristic("Croaks");
    private Description description = new Description("Eats bugs");

    public Frog(){
        System.out.println("Frog");
    }

    protected void dispose(){
        System.out.println("Canceling frogs....");
        characteristic.dispose();
        description.dispose();
        super.dispose();
    }

    public static void main(String[] args) {
        Frog frog = new Frog();
        System.out.println("Goodbye");
        frog.dispose();
    }
}
