package org.example.classesinterfaces;


import java.io.Console;

interface IFace {
    public void m1();
    public default void m2() {
        System.out.println("m2");
    }
    public static void m3() {
        System.out.println("m3");
    }
    private void m4() {
        System.out.println("m4");
    }
}

class MyC implements IFace {
    public void m1() {
        System.out.println("Hello");
    }
}

class SuperA {
    public static void m1() {
        System.out.println("m1");
    }
    public void m2() {
        System.out.println("m2");
    }
}

class SubB extends SuperA {
    public void m3() {
        System.out.println("m3");
    }
    public static void m1() {
        System.out.println("Sub m1");
    }
}

public class ClassImplementsInterface {
    public static void main(String[] args) {
        IFace ifac = new MyC();
        // compilation error
        //ifac.m3();
        ifac.m2();

        new MyC().m2();

        // compilation error
        //IFace.m2();


        // private methods cannot be accessible from outside the interface but from implementors.
        //ifac.m4();
        SuperA supa = new SubB();

        // see the difference between interface and class static methods.
        supa.m1();
        ((SubB)supa).m1();

        //System.in;
        Console console = System.console();
    }
}
