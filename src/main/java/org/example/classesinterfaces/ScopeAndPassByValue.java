package org.example.classesinterfaces;

public class ScopeAndPassByValue {
    public int x = 100;

    public void myMethod(int x) {
        x++;
        System.out.println(x);
        System.out.println(this.x);
    }

    public static void main(String[] args) {
        int x = 1000;
        ScopeAndPassByValue sv = new ScopeAndPassByValue();
        sv.myMethod(x);
        System.out.println(x);
    }
}
