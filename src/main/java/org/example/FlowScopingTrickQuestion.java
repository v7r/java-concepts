package org.example;

public class FlowScopingTrickQuestion {
    static class A { public void mA() {System.out.println("Apple");}}
    static class B extends A { public void mB() {System.out.println("Banana");}}
    static class C extends B { public void mC() {System.out.println("Cherry");}}

    /**
     * What will be printed ?
     *
     * @param args
     */
    public static void main(String[] args) {
        A bobj = new B();
        A cobj = new C();
        if (cobj instanceof B v) {
            v.mB();
            if (v instanceof C v1) v1.mC();
        } else {
            cobj.mA();
        }
    }
}
