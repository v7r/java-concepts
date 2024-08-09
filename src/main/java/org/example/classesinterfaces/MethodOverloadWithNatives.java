package org.example.classesinterfaces;

public class MethodOverloadWithNatives {
    public void sum(int a, int b) {
        System.out.println(" A ");
    }
    public void sum(int a, float b) {
        System.out.println(" B ");
    }
    public void sum(float a, float b) {
        System.out.println(" C ");
    }
    public void sum(double... b) {
        System.out.println(" D ");
    }

    public static void main(String[] args) {
        MethodOverloadWithNatives n = new MethodOverloadWithNatives();
        n.sum(10, 15.25);
        n.sum(10, 24);
        n.sum(10.25, 10.25);
        n.sum(10, 10.25f);
    }
}
