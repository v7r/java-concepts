package org.example.varkey;

import java.util.ArrayList;


class Mango {
    public void m(int x) {
        // compilation error
        //var x = 10;
    }
}

public class VarList {

    static class A {}
    static class B extends A {}

    public static void main(String[] args) {
        var x = new ArrayList<>();
        x.add(10);
        x.add("10");
        System.out.println(x);

        var a = new A();
        a = new B();

        var b = 100;
        // compilation error
        //b = "mango";

        // compilation error
        //var orange = null;
    }
}
