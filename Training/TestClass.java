/**
 * Created by pikachu on 20.06.17.
 */

class FirstClass {

    char doh (char c){
        System.out.println("Char parent");
        return c;
    }

    float doh(float x){
        System.out.println("Float parent");
        return x;
    }
}

class MiddleClass{
    String s;

    MiddleClass(){
        s = "Hello from Middle-Earth";
    }

    public String toString(){
        return s;
    }
}

class ExtendsTestClass extends FirstClass {

    void doh (MiddleClass m){
        System.out.println(m);
    }
}

public class TestClass{
    public static void main(String[] args) {
        ExtendsTestClass e = new ExtendsTestClass();
        System.out.println(e.doh('x'));
        System.out.println(e.doh(1));
        e.doh(new MiddleClass());

    }
}




