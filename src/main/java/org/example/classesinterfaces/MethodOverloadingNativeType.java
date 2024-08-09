package org.example.classesinterfaces;

public class MethodOverloadingNativeType {
    void print(int i) {
        System.out.println("Hello");
    }
    void print(long j) {
        System.out.println("there");
    }

    public static void main(String[] args) {
        MethodOverloadingNativeType m = new MethodOverloadingNativeType();
        m.print(0b1101_1010);
        m.print(2);
    }
}
